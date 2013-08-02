package com.dingcan.comments.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dingcan.comments.dao.CommentsDao;
import com.dingcan.comments.model.Comment;
import com.dingcan.comments.model.CommentVo;
import com.dingcan.util.CustomDate;

@Service
public class CommentsService {
	ApplicationContext context = 
	 		new ClassPathXmlApplicationContext("Spring-Module.xml");

	CommentsDao commentsDao = (CommentsDao) context.getBean("CommentsDao");
	
	public List<Comment> getCommentsByGoodsId(int goodsId){
 		return commentsDao.getCommentListByGoodsId(goodsId);
 	}
	
	public boolean addComment(Comment comment){
		comment.setCreateDate(CustomDate.getStringDate());
		return commentsDao.addComment(comment)>0;
	}
	
	public List<CommentVo> getCommentsVoByGoodsId(int goodsId){
		return commentsDao.getCommentVoListByGoodsId(goodsId);
	}
}
