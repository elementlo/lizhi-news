package com.example.android20_lzhxw.beans;

/**
 * 有三张的item的实体类： 添加getters setters 、构造方法  、 重写toString（）
 * 
 * @author cj
 * 
 */
public class Pic {
	private String id, photo, subject;

	// "id": 39284,
	// "photo":
	// "/Attachs/Map/4247/591b5988aa52435da45722d31897caf3_cover_padmini.JPG",
	// "subject": "


	public Pic() {
		super();
	}

	public Pic(String id, String photo, String subject) {
		super();
		this.id = id;
		this.photo = photo;
		this.subject = subject;
	}
	
	
	@Override
	public String toString() {
		return "Pic [id=" + id + ", photo=" + photo + ", subject=" + subject
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
