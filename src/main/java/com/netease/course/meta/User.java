package com.netease.course.meta;

import java.io.Serializable;
import java.util.List;

import com.netease.course.meta.BuyList;

public class User implements Serializable{
	
	private static final long serialVersionUID = -6969195671935482609L;	
	private int personid;
	private int usertype;
	private String userName;
	private String password;
	private String nickName;	
	private List<BuyList> buyList;
	
	public User(Integer personid,Integer usertype,String userName,String password,String nickName){
		this.personid=personid;
		this.usertype=usertype;
		this.userName=userName;
		this.password=password;
		this.nickName=nickName;
	}




	public List<BuyList> getBuyList() {
		return buyList;
	}




	public void setBuyList(List<BuyList> buyList) {
		this.buyList = buyList;
	}




	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

}
