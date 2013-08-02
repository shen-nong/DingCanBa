package com.dingcan.message.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dingcan.cons.Cons;
import com.dingcan.goods.dao.GoodsDao;
import com.dingcan.goods.model.Goods;
import com.dingcan.goods.model.GoodsVoCount;
import com.dingcan.goods.model.Sales;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.message.dao.MessageDao;
import com.dingcan.message.model.Message;
import com.dingcan.message.model.MessageVo;
import com.dingcan.message.model.Order;
import com.dingcan.user.service.UserService;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CustomDate;
import com.dingcan.util.PageModel;

@Service
public class MessageService {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	
	ApplicationContext context = 
	 		new ClassPathXmlApplicationContext("Spring-Module.xml");
	MessageDao messageDao = (MessageDao) context.getBean("messageDao");
	GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
	
	public boolean addMessage(Message message){
		message.setCreateDate(CustomDate.getStringDate());
		return messageDao.addMessage(message)>0;
	}
	
	/**
	 * 移动首页,查询商家
	 * @param pageNumStr
	 * @return
	 * Green Lei
	 * 2012-12-2 下午12:19:56 2012
	 */
	public PageModel<Message> findShopMessage(String pageNumStr){
		int pageNo = 1;
		int pageSize = Integer.parseInt(ConfigReader.getValue("pagesize_mobile_home_shop"));
		if(pageNumStr != null && !"".equals(pageNumStr)){
			pageNo = Integer.parseInt(pageNumStr);
		}
		return messageDao.findShopMessage(pageSize, pageNo);
	}
	
	/**
	 * 
	 * @param pageNumStr
	 * @return
	 * Green Lei
	 * 2012-12-2 下午1:03:03 2012
	 */
	public PageModel<Message> findUserMessage(String pageNumStr){
		int pageNo = 1;
		int pageSize = Integer.parseInt(ConfigReader.getValue("pagesize_mobile_user_shop"));
		if(pageNumStr != null && !"".equals(pageNumStr)){
			pageNo = Integer.parseInt(pageNumStr);
		}
		return messageDao.findUserMessage(pageSize, pageNo);
	}
	
	public int getOrderCountByGoodsId(int goodsId){
		return messageDao.getMessageCountByGoodsIdAndType(goodsId, Cons.ADD_MESSAGE_MESSAGE);
	}
	
	public List<Order> getOrderListByGoodsId(int goodsId){
		return messageDao.getOrderListByGoodsIdAndType(goodsId, Cons.ADD_MESSAGE_MESSAGE);
	}
	
	public PageModel<Message> getMessagePageByPageno(String pageNumStr){
		int pageNo = 1;
		int pageSize = 40;
		if(pageNumStr != null && !"".equals(pageNumStr)){
			pageNo = Integer.parseInt(pageNumStr);
		}
		return messageDao.getMessagePage(pageNo, pageSize);
	}
	
	/**
	 * 首页的新鲜事
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * 2013-6-6 下午1:33:53 2013
	 */
	public List<MessageVo> getMessageVoList(int pageNo, int pageSize){
 		List<MessageVo> messageVoList = new ArrayList<MessageVo>();
 		MessageVo messageVo;
 		int saleCount = 0;
 		for(Message message : messageDao.getMessageLimited(pageNo, pageSize)){
 			messageVo = new MessageVo();
 			messageVo.setGoods(goodsService.getGoodsByGoodsId(message.getToId()));
 			messageVo.setUser(userService.getUserByUserId(message.getFromId()+""));
 			messageVo.setLike(goodsDao.getLikeCount(message.getToId()));
 			messageVo.setDate(message.getCreateDate());
 			for(Sales sales:goodsDao.getSalesByGoodsId(message.getToId())){
 				saleCount = saleCount + sales.getQuantity();
 			}
 			messageVo.setSale(saleCount);
 			messageVoList.add(messageVo);
 			saleCount = 0;
 		}
 		return messageVoList;
 	}
}
