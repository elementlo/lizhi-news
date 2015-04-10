package com.example.android20_lzhxw.beans;

import java.util.ArrayList;
/**
 * News�����data���Զ�Ӧ��
 * ���getters  setters 
 * ���췽��
 * ��дtoString
 * @author cj
 */
public class NewsData {
	private String subject,summary,cover,format,changed;
	private ArrayList<Pic> pics;
	
	public NewsData() {
		super();
	}
	public NewsData(String subject, String summary, String cover,
			String format, String changed, ArrayList<Pic> pics) {
		super();
		this.subject = subject;
		this.summary = summary;
		this.cover = cover;
		this.format = format;
		this.changed = changed;
		this.pics = pics;
	}	
	
	@Override
	public String toString() {
		return "NewsData [subject=" + subject + ", summary=" + summary
				+ ", cover=" + cover + ", format=" + format + ", changed="
				+ changed + ", pics=" + pics + "]";
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getChanged() {
		return changed;
	}
	public void setChanged(String changed) {
		this.changed = changed;
	}
	public ArrayList<Pic> getPics() {
		return pics;
	}
	public void setPics(ArrayList<Pic> pics) {
		this.pics = pics;
	}
	
//	 "subject": "���������ѧչʾ���ޡ�ս��Ʒ��",
//     "summary": "�ݴ����΢��11��5�ձ��������죬���������ѧУ���ѽ��ڡ�����ս��Ʒ���ν�ʾ���ˣ�������Щ�緹�Һ��ȵÿ졭��̫�����ˣ������������սɹ�ʲô������ͼƬ��Դ�������΢��",
//     "cover": "/Attachs/Map/4247/6c44136651e4414bbab014d48c95a80a_cover_padmini.JPG",
//     "format": "map",
//     "changed": "2014-11-05 15:56:54",
//     "pics": 
	
}
