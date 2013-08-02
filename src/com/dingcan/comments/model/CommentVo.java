package com.dingcan.comments.model;

public class CommentVo extends Comment {

	private String userName;
	private String userPhoto;  // ÓÃ»§Í·Ïñ
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
}
