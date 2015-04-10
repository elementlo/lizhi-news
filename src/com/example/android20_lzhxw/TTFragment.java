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
 * 1��������������:�����ڰ����ࣺHttpUtils(Ȩ��) 2������ 3��չʾ��Adapter
 * 
 * 4��item��ͼƬ��䣺Adapter������getView������ ͼƬ�洢 5������ˢ�� 6���������أ���ҳ����
 * 
 * 
 * @author cj
 * 
 */
public class TTFragment extends ListFragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// MVC m������ v��Pager C��Adapter
		// �õ�����������
		HttpUtils.RequestNetWork(AppURLFinal.URL_NAVIGATION,
				new OnNetWorkResponse() {

					@Override
					public void ok(String response) {
						// ���ݽ���
						// System.out.println("==="+response);
						JSONObject jsonObject = JSONObject
								.parseObject(response);
						// status��Ӧ��ֵ�����ok��������ȥȡparamz�����ֵ
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
							// �����ViewPager��item��
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

							// ��ȡListFragment��ListVIew
							ListView listView = getListView();
							View header = View.inflate(getActivity(),
									R.layout.header, null);
							TopViewPager pager = (TopViewPager) header
									.findViewById(R.id.pager_header);

							TopPagerAdapter pagerAdapter = new TopPagerAdapter(
									views);
							pager.setAdapter(pagerAdapter);
							
							//textView����ʾ���ݣ���ʾPagerҳ��
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
							// ���ͷ����ͼ��ʱ�򣬱�����setAdapter֮ǰ��
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
					public void ok(String response) {// ��������ɹ�������
						System.out.println("==" + response);
						// ���status��ok��ȥȡ������������
						JSONObject jsonObject = JSONObject
								.parseObject(response);
						String status = jsonObject.getString("status");
						if ("ok".equals(status)) {
							// paramz����Ӧ��ֵJSONObject
							JSONObject jsonObject2 = JSONObject
									.parseObject(jsonObject.getString("paramz"));
							// JSONArray jsonArray =
							// jsonObject2.getJSONArray("feeds");
							// �õ�feeds����Ӧ��JSONArray�ַ���
							String strJSONArray = jsonObject2
									.getString("feeds");
							// ��FastJson�����õ�ListView�е�����ԴList<News>
							List<News> list = JSONArray.parseArray(
									strJSONArray, News.class);
							// ͨ����������������������
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
