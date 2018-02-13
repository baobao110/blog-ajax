package com.AccountFlow;
import java.util.Date;

public class Account {
	private int id;
	private int number;
	private String title;
	private String context;
	private Date createtime;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", title=" + title + ", context=" + context
				+ ", createtime=" + createtime + ", description=" + description + "]";
	}
	
}
