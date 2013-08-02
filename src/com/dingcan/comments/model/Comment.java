package com.dingcan.comments.model;

/**
 * …Ã∆∑∆¿¬€
 * @author Administrator
 *
 */
public class Comment {
	private int CommentsId;
	private int ReplyId;
	private int GoodsId;
	private String Content;
	private int CommenterId;
	private String CreateDate;
	
	public int getCommentsId() {
		return CommentsId;
	}
	public void setCommentsId(int commentsId) {
		CommentsId = commentsId;
	}
	public int getReplyId() {
		return ReplyId;
	}
	public void setReplyId(int replyId) {
		ReplyId = replyId;
	}
	public int getGoodsId() {
		return GoodsId;
	}
	public void setGoodsId(int goodsId) {
		GoodsId = goodsId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getCommenterId() {
		return CommenterId;
	}
	public void setCommenterId(int commenterId) {
		CommenterId = commenterId;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
}
