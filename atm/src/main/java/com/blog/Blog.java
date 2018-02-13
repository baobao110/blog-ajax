package com.blog;
import java.util.Date;//这里为什么用util不是sql因为util在 流水界面前端时可以显示时分秒但是sql默认0

public class Blog {
	private int id;
	private int number;
	private String password;
	private int total;
	private Date createtime;
	private Date modifytime;
	private String username;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", number=" + number + ", password=" + password + ", total=" + total + ", createtime="
				+ createtime + ", modifytime=" + modifytime + ", username=" + username + "]";
	}
	
	
}
