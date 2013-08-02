package com.dingcan.user.model;

/**
 * 保存自动登录的信息
 * @author Administrator
 *
 */
public class AutoLogin {
	private int id;
	private String UserId;
	private String SessionId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
}
