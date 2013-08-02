package com.dingcan.user.model;

/**
 * 好友
 * @author Administrator
 *
 */
public class Friends {

	private int id;
	private int myId;
	private int friendId;
	private int state;     //0为假,1为真
	public int getMyId() {
		return myId;
	}
	public void setMyId(int myId) {
		this.myId = myId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
