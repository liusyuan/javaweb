package com.netease.course.meta;

import java.sql.Blob;

public class Content {
	private int id;
	private int price;
	private String title;
	private Blob icon;
	private String abstracts;
	private Blob text;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Blob getIcon() {
		return icon;
	}
	public void setIcon(Blob icon) {
		this.icon = icon;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public Blob getText() {
		return text;
	}
	public void setText(Blob text) {
		this.text = text;
	}
}
