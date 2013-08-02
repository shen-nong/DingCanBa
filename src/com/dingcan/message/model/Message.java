package com.dingcan.message.model;

/**
 * 短信以及站内信等的相关信息
 * @author Green Lei
 * 2012-12-2 上午9:47:58 2012
 */
public class Message {
	private int MessagesId;
	private int FromId;
	private int ToId;
	private String ContentText;
	private int Type;      //1发送了一条短信,2:评论, 3:添加了一件商品,4:添加了一条分享到第三方
	private String CreateDate;
	private String PictureUrl; //头像或者商品的照片
	private int CommentNum;    //该消息被评论的次数
	private int ShareNum;		//该消息被分享到第三方的次数	
	
	public int getMessagesId() {
		return MessagesId;
	}
	public void setMessagesId(int messagesId) {
		MessagesId = messagesId;
	}
	public int getFromId() {
		return FromId;
	}
	public void setFromId(int fromId) {
		FromId = fromId;
	}
	public int getToId() {
		return ToId;
	}
	public void setToId(int toId) {
		ToId = toId;
	}
	public String getContentText() {
		return ContentText;
	}
	public void setContentText(String contentText) {
		ContentText = contentText;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getPictureUrl() {
		return PictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		PictureUrl = pictureUrl;
	}
	public int getCommentNum() {
		return CommentNum;
	}
	public void setCommentNum(int commentNum) {
		CommentNum = commentNum;
	}
	public int getShareNum() {
		return ShareNum;
	}
	public void setShareNum(int shareNum) {
		ShareNum = shareNum;
	}
}
