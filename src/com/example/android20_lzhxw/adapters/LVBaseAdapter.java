package com.example.android20_lzhxw.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android20_lzhxw.R;
import com.example.android20_lzhxw.beans.News;
import com.example.android20_lzhxw.utils.AppURLFinal;
import com.example.android20_lzhxw.utils.ImageLoader;

public class LVBaseAdapter extends BaseAdapter {
	private Context context;
	private List<News> datas;
	private final int VIEWTYPE_ARTICAL = 0;
	private final int VIEWTYPE_MAP = 1;

	public LVBaseAdapter(Context context, List<News> datas) {
		super();
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/** 返回列表中item视图的样式的个数 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	/** 指定将要被创建的item的View的type */
	@Override
	public int getItemViewType(int position) {
		News news = datas.get(position);
		if ("article".equals(news.getCategory())) {
			return VIEWTYPE_ARTICAL;
		} else if ("map".equals(news.getCategory())) {
			return VIEWTYPE_MAP;
		}
		return super.getItemViewType(position);
	}

	/***
	 * 返回视图View有两种：需要定义两种布局来生成相应的item 如果View的复用：得判断View的type，决定Holder的使用
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		News news = datas.get(position);
		switch (getItemViewType(position)) {
		case 0://Artical布局
			ArticalHolder articalHolder;
			if(convertView==null){
				convertView=View.inflate(context, R.layout.item_artical_lv, null);
				articalHolder = new ArticalHolder();
				articalHolder.imgv_artical=(ImageView) convertView.findViewById(R.id.imgv_ArticalItem);
				articalHolder.txtvSubject_artical=(TextView) convertView.findViewById(R.id.txtvSubject_ArticalItem);
				articalHolder.txtvSummary_artical=(TextView) convertView.findViewById(R.id.txtvSummary_ArticalItem);
				convertView.setTag(articalHolder);
			}else{
				articalHolder=(ArticalHolder) convertView.getTag();
			}
			articalHolder.imgv_artical.setImageResource(R.drawable.ic_launcher);
			articalHolder.txtvSubject_artical.setText(news.getData().getSubject());
			articalHolder.txtvSummary_artical.setText(news.getData().getSummary());
			ImageLoader.loadImg(AppURLFinal.URL_IMGBASE+news.getData().getCover(), articalHolder.imgv_artical, context);
			break;
		case 1://Map布局
			MapHolder mapHolder;
			if(convertView==null){
				convertView=View.inflate(context, R.layout.item_map_lv, null);
				mapHolder=new MapHolder();
				mapHolder.txtv_map=(TextView) convertView.findViewById(R.id.txtvSubject_mapItem);
				mapHolder.imgv1_map=(ImageView) convertView.findViewById(R.id.imgv1_mapItem);
				mapHolder.imgv2_map=(ImageView) convertView.findViewById(R.id.imgv2_mapItem);
				mapHolder.imgv3_map=(ImageView) convertView.findViewById(R.id.imgv3_mapItem);
				convertView.setTag(mapHolder);
			}else{
				mapHolder=(MapHolder) convertView.getTag();
			}
			mapHolder.txtv_map.setText(news.getData().getSubject());

			mapHolder.imgv1_map.setImageResource(R.drawable.ic_launcher);
			ImageLoader.loadImg(AppURLFinal.URL_IMGBASE+news.getData().getPics().get(0).getPhoto(), mapHolder.imgv1_map, context);
			
			mapHolder.imgv2_map.setImageResource(R.drawable.ic_launcher);
			ImageLoader.loadImg(AppURLFinal.URL_IMGBASE+news.getData().getPics().get(1).getPhoto(), mapHolder.imgv2_map, context);

			mapHolder.imgv3_map.setImageResource(R.drawable.ic_launcher);
			ImageLoader.loadImg(AppURLFinal.URL_IMGBASE+news.getData().getPics().get(2).getPhoto(), mapHolder.imgv3_map, context);

			break;
		default:
			break;
		}
		return convertView;
	}

	class ArticalHolder {
		ImageView imgv_artical;
		TextView txtvSubject_artical, txtvSummary_artical;
	}

	class MapHolder {
		TextView txtv_map;
		ImageView imgv1_map, imgv2_map, imgv3_map;
	}

}
