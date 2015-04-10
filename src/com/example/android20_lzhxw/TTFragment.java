package com.example.android20_lzhxw;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.android20_lzhxw.adapters.LVBaseAdapter;
import com.example.android20_lzhxw.adapters.TopPagerAdapter;
import com.example.android20_lzhxw.beans.News;
import com.example.android20_lzhxw.beans.TopPagerInfo;
import com.example.android20_lzhxw.mviews.TopViewPager;
import com.example.android20_lzhxw.utils.AppURLFinal;
import com.example.android20_lzhxw.utils.HttpUtils;
import com.example.android20_lzhxw.utils.HttpUtils.OnNetWorkResponse;
import com.example.android20_lzhxw.utils.ImageLoader;

/**
 * 1、请求网络数据:借助于帮助类：HttpUtils(权限) 2、解析 3、展示：Adapter
 * 
 * 4、item中图片填充：Adapter。。。getView（）中 图片存储 5、下拉刷新 6、上拉加载：分页加载
 * 
 * 
 * @author cj
 * 
 */
public class TTFragment extends ListFragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// MVC m：数据 v：Pager C：Adapter
		// 拿导航网络数据
		HttpUtils.RequestNetWork(AppURLFinal.URL_NAVIGATION,
				new OnNetWorkResponse() {

					@Override
					public void ok(String response) {
						// 数据解析
						// System.out.println("==="+response);
						JSONObject jsonObject = JSONObject
								.parseObject(response);
						// status对应的值如果是ok，接下来去取paramz里面的值
						String status = jsonObject.getString("status");
						if ("ok".equals(status)) {
							JSONObject jsonObject2 = jsonObject
									.getJSONObject("paramz");
							String tops = jsonObject2.getString("tops");
							List<TopPagerInfo> list = JSONArray.parseArray(
									tops, TopPagerInfo.class);
							// for (TopPagerInfo topPagerInfo : list) {
							// System.out.println("==="+topPagerInfo);
							// }
							// 填充在ViewPager的item中
							List<View> views = new ArrayList<View>();
							for (int i = 0; i < list.size(); i++) {
								TopPagerInfo topPagerInfo = list.get(i);
								View view = View.inflate(getActivity(),
										R.layout.item_pager, null);
								ImageView imgv = (ImageView) view
										.findViewById(R.id.imgv_pagerItem);
								imgv.setImageResource(R.drawable.ic_launcher);
								ImageLoader.loadImg(AppURLFinal.URL_IMGBASE
										+ topPagerInfo.getPhoto(), imgv,
										getActivity());
								TextView txtv = (TextView) view
										.findViewById(R.id.txtv_pagerItem);
								txtv.setText(topPagerInfo.getSubject());
								views.add(view);
							}

							// 获取ListFragment的ListVIew
							ListView listView = getListView();
							View header = View.inflate(getActivity(),
									R.layout.header, null);
							TopViewPager pager = (TopViewPager) header
									.findViewById(R.id.pager_header);

							TopPagerAdapter pagerAdapter = new TopPagerAdapter(
									views);
							pager.setAdapter(pagerAdapter);
							
							//textView的显示内容，标示Pager页码
							final TextView txv_page=(TextView) header.findViewById(R.id.txtv_header);
							final int size=views.size();
							txv_page.setText("1/"+size);
							pager.setOnPageChangeListener(new OnPageChangeListener() {
								
								@Override
								public void onPageSelected(int arg0) {
									txv_page.setText(arg0+1+"/"+size);
								}
								
								@Override
								public void onPageScrolled(int arg0, float arg1, int arg2) {
									
								}
								
								@Override
								public void onPageScrollStateChanged(int arg0) {
									
								}
							});
							// 添加头部视图的时候，必须在setAdapter之前。
							listView.addHeaderView(header);

						}
					}

					@Override
					public void error(String error) {
					}
				});

		HttpUtils.RequestNetWork(AppURLFinal.URL_TOUTIAO,
				new OnNetWorkResponse() {

					@Override
					public void ok(String response) {// 网络请求成功的数据
						System.out.println("==" + response);
						// 如果status是ok，去取接下来的数据
						JSONObject jsonObject = JSONObject
								.parseObject(response);
						String status = jsonObject.getString("status");
						if ("ok".equals(status)) {
							// paramz键对应的值JSONObject
							JSONObject jsonObject2 = JSONObject
									.parseObject(jsonObject.getString("paramz"));
							// JSONArray jsonArray =
							// jsonObject2.getJSONArray("feeds");
							// 得到feeds键对应的JSONArray字符串
							String strJSONArray = jsonObject2
									.getString("feeds");
							// 用FastJson解析得到ListView中的数据源List<News>
							List<News> list = JSONArray.parseArray(
									strJSONArray, News.class);
							// 通过遍历输出，来检查解析结果
							// for (News news : list) {
							// System.out.println("=news=="+news);
							// }
							LVBaseAdapter adapter = new LVBaseAdapter(
									getActivity(), list);
							setListAdapter(adapter);
						}
					}

					@Override
					public void error(String error) {

					}
				});
	}
}
