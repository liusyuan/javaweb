package com.netease.course.meta;

public class Transaction {
	private int id;
	private int contentId;
	private int personId;
	private int price;
	private long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public void setTime(long time) {
		this.time = time;
	}
	public long getTime(){
		return time;
	}
	
}
