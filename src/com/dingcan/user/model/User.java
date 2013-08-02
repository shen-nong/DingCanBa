package com.dingcan.user.model;

@SuppressWarnings("serial")
public class User implements java.io.Serializable{

	private int UserId;
	private String UserMail;
	private String UserPwd;
	private String UserName;
	private String UserSex ;  //m:男, f:女
	private String Birthday;
	private String Location;
	private String Photo;
	private String RegisterTime;
	private String phone;   //联系电话
	private String intro;   //简介
	private String photo50;  //宽50
	private String photo65;  //宽65
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserMail() {
		return UserMail;
	}
	public void setUserMail(String userMail) {
		UserMail = userMail;
	}
	public String getUserPwd() {
		return UserPwd;
	}
	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserSex() {
		return UserSex;
	}
	public void setUserSex(String userSex) {
		UserSex = userSex;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getRegisterTime() {
		return RegisterTime;
	}
	public void setRegisterTime(String registerTime) {
		RegisterTime = registerTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPhoto50() {
		return this.Photo.replace("200w", "50w");
	}
	public String getPhoto65() {
		return this.Photo.replace("200w", "65w");
	}
}
