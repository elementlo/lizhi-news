package com.example.android20_lzhxw.beans;
/**
 * 导航上面的数据的实体类
 * 要展示出去：图片photo（url）、subject
 * @author cj
 *
 */
public class TopPagerInfo {
	private String id,oid,category,photo,subject;
	
	

	public TopPagerInfo() {
		super();
	}

	public TopPagerInfo(String id, String oid, String category, String photo,
			String subject) {
		super();
		this.id = id;
		this.oid = oid;
		this.category = category;
		this.photo = photo;
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "TopPager [id=" + id + ", oid=" + oid + ", category=" + category
				+ ", photo=" + photo + ", subject=" + subject + "]";
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
//	id": 7002,
//    "oid": 4262,
//    "category": "map",
//    "photo": "/Attachs/Top/7002/5d7f3805d54d4da6a37355a1b084e5dc.jpg",
//    "subject":
}
