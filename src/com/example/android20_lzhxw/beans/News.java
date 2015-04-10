package com.example.android20_lzhxw.beans;
/**
 * 新闻实体类：将来会被用在List的item上的数据
 * 添加getters  setters
 * 添加构造方法：有（全部）参数构造方法、无参构造
 * 重写toString()：类名和所有属性的值
 * @author cj
 */
public class News {
	private String id,oid,category;
	private NewsData data;
//	 "id": 168553,
//     "oid": 166138,
//     "category": "article",
//     "data":
	
	public News(String id, String oid, String category, NewsData data) {
		super();
		this.id = id;
		this.oid = oid;
		this.category = category;
		this.data = data;
	}
	
	public News() {
		super();
	}
	

	@Override
	public String toString() {
		return "News [id=" + id + ", oid=" + oid + ", category=" + category
				+ ", data=" + data + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public NewsData getData() {
		return data;
	}
	public void setData(NewsData data) {
		this.data = data;
	}
	
}
