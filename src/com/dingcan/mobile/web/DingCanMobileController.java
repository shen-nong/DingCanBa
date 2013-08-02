package com.dingcan.mobile.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.model.WeiboException;

import com.dingcan.user.model.User;
import com.dingcan.user.service.UserService;
import com.dingcan.util.ConfigReader;
import com.dingcan.api.service.FeixinService;
import com.dingcan.api.service.QqService;
import com.dingcan.base.BaseController;
import com.dingcan.comments.model.Comment;
import com.dingcan.comments.service.CommentsService;
import com.dingcan.cons.Cons;
import com.dingcan.goods.model.Goods;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.message.model.Message;
import com.dingcan.message.service.MessageService;
import com.dingcan.shop.model.Shop;
import com.dingcan.shop.service.ShopService;

@Controller
public class DingCanMobileController extends BaseController{
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private FeixinService feixinService; 
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private QqService qqService;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(DingCanMobileController.class);
	
	/**
	 * 修改成分页显示
	 * @return
	 * Green Lei
	 * 2012-11-30 上午11:21:19 2012
	 */
	@RequestMapping(value = "/getGoodsPageMobile", method = RequestMethod.GET)
	public ModelAndView getGoodsListMobile(HttpServletRequest request) {
		String pageNoStr = request.getParameter("pageNo");
		ModelAndView mav = new ModelAndView("mhome");
		mav.addObject("GoodsPage", goodsService.getGoodsPage(pageNoStr));
		return mav;
	}
	
	@RequestMapping(value = "/getGoodsMobile", method = RequestMethod.GET)
	public ModelAndView getGoodsMobile(HttpServletRequest request) {
		String goodsId = request.getParameter("id");
		int goodsIdInt = 0;
		if(goodsId != null){
			goodsIdInt = Integer.parseInt(goodsId);
		}
		ModelAndView mav = new ModelAndView("msingle");
		mav.addObject("Goods", goodsService.getGoodsByGoodsId(goodsIdInt));
		mav.addObject("comments", commentsService.getCommentsByGoodsId(goodsIdInt));
		return mav;
	}
	
	@RequestMapping(value = "/addCommentMobile", method = RequestMethod.POST)
	public String addCommentMobile(@ModelAttribute("commentForm") Comment comment,
			HttpServletRequest request) {
		ModelAndView mav = null;
		User user = getSessionUser(request);
		Goods goods = goodsService.getGoodsByGoodsId(comment.getGoodsId());
		if(comment.getCommenterId() == 0){    //未登陆,跳转到登陆页面
			return "";
		}else if(comment.getContent() == null || "".equals(comment.getContent())){    //评论为空
			return "";
		}else{
			commentsService.addComment(comment);
			Message message = new Message();
			message.setContentText(user.getUserName()+":"+comment.getContent()+"[来自手机用户]");
			message.setFromId(comment.getCommenterId());
			message.setPictureUrl(goods.getGoodsUrl());
			message.setToId(comment.getGoodsId());
			message.setType(Cons.ADD_COMMENT_MESSAGE);
			messageService.addMessage(message);
			
			return "redirect:goodsMobile.html?id="+comment.getGoodsId();
		}
	}
	
	/**
	 * 发送飞信时,同时保存一条message记录
	 * @param request
	 * @return
	 * @throws WeiboException
	 * Green Lei
	 * 2012-12-2 上午10:30:01 2012
	 */
	@RequestMapping(value = "/sendMessageMobile", method = RequestMethod.GET)
	public ModelAndView sendMessageMobile(HttpServletRequest request) throws WeiboException {
		String goodsId = request.getParameter("id");
		String count = request.getParameter("count");
		int goodsIdInt = 0;
		if(goodsId != null){
			goodsIdInt = Integer.parseInt(goodsId);
		}
		
		User user = getSessionUser(request);
		
		ModelAndView mav = new ModelAndView("mprompt");
		
		if(user == null){
			mav.addObject("prompt", ConfigReader.getValue("message_tip2"));
		}else if(user.getLocation() == null){
			mav.addObject("prompt", ConfigReader.getValue("message_tip3"));
		}else if(user.getPhone() == null){
			mav.addObject("prompt", ConfigReader.getValue("message_tip4"));
		}else{
			Goods goods = goodsService.getGoodsByGoodsId(goodsIdInt);
			Shop shop = shopService.getShopByShopId(goods.getShopId());
			
			StringBuffer message = new StringBuffer();
			message.append(user.getLocation())
					.append("("+user.getPhone()+"):")
					.append(goods.getGoodsName()+"/"+count+"份");
			feixinService.sendMessage(shop.getFeixin()+"", message.toString());
			
			Message messageObj = new Message();
			messageObj.setContentText(message.toString()+"[来自手机用户]");
			messageObj.setFromId(user.getUserId());
			messageObj.setToId(goods.getGoodsId());
			messageObj.setPictureUrl(goods.getGoodsUrl());
			messageObj.setType(Cons.ADD_MESSAGE_MESSAGE);
			messageService.addMessage(messageObj);
			mav.addObject("prompt", ConfigReader.getValue("message_tip1"));
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/getGoodListJson", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getGoodListJson(HttpServletRequest request){
		System.out.println(request.getParameter("q"));
		System.out.println(request.getParameter("page"));
		System.out.println(request.getParameter("rpp"));
		System.out.println(request.getParameter("since_id"));
		logger.info("列表");
		List<Goods> list = new ArrayList<Goods>();
		Goods goods = new Goods();
		goods.setGoodsId(1);
		goods.setGoodsName("465456432123274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136274703729637339136132546");
		for(int n=0; n<10; n++){
			list.add(goods);
		}
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("completed_in", "0.04");
		modelMap.put("max_id", "274703729637339136");
		modelMap.put("max_id_str", "274703729637339136");
		modelMap.put("next_page", "?page=2&max_id=274703729637339136&q=javascript");
		modelMap.put("page", 1);
		modelMap.put("query", "javascript");
		modelMap.put("refresh_url", "?since_id=274703729637339136&q=javascript");
		modelMap.put("results", list);
		return modelMap;
	}
	
	@RequestMapping(value = "/getMenuMobile", method = RequestMethod.GET)
	public ModelAndView getMenuMobile(){
		return new ModelAndView("mmenu");
	}
	
	/**
	 * 在首页看商家动态
	 * @param request
	 * @return
	 * Green Lei
	 * 2012-12-2 下午12:32:48 2012
	 */
	@RequestMapping(value = "/getShopPageMobile", method = RequestMethod.GET)
	public ModelAndView getShopMobile(HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		ModelAndView mav = new ModelAndView("mshop");
		mav.addObject("Page", messageService.findShopMessage(pageNo));
		return mav;
	}
	
	/**
	 * 在首页查看用户动态
	 * @param request
	 * @return
	 * Green Lei
	 * 2012-12-2 下午1:03:30 2012
	 */
	@RequestMapping(value = "/getUserPageMobile", method = RequestMethod.GET)
	public ModelAndView getUserPageMobile(HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		ModelAndView mav = new ModelAndView("muser");
		mav.addObject("Page", messageService.findUserMessage(pageNo));
		return mav;
	}
	
	@RequestMapping(value = "/queryMobile", method = RequestMethod.POST)
	public ModelAndView queryMobile(HttpServletRequest request){
		String queryStr = request.getParameter("search");
		ModelAndView mav = new ModelAndView("msearchresult");
		mav.addObject("UserList", goodsService.getUserListByQuery(queryStr));
		mav.addObject("GoodsList", goodsService.getGoodsListByQuery(queryStr));
		mav.addObject("ShopList", goodsService.getShopListByQuery(queryStr));
		return mav;
	}
	
	/**
	 * 根据商家id,获取商家的商品列表
	 * @return
	 * Green Lei
	 * 2012-12-10 下午1:57:28 2012
	 */
	@RequestMapping(value = "/shopMobile", method = RequestMethod.GET)
	public ModelAndView shopMobile(HttpServletRequest request){
		String id = request.getParameter("id");
		int shop = id==null?1:Integer.parseInt(id);
		ModelAndView mav = new ModelAndView("mshop");
		mav.addObject("GoodsList", goodsService.getGoodsListByShopId(shop));
		return mav;
	}
	
	
	@RequestMapping(value = "/goodsMobile", method = RequestMethod.GET)
	public ModelAndView goodsMobile(HttpServletRequest request){
		String id = request.getParameter("id");
		int goodsId = id==null?1:Integer.parseInt(id);
		ModelAndView mav = new ModelAndView("mgoods");
		Goods goods = goodsService.getGoodsByGoodsId(goodsId);
		mav.addObject("Goods", goods);
		mav.addObject("Shop", shopService.getShopByShopId(goods.getShopId()));
		mav.addObject("CommentsList", commentsService.getCommentsVoByGoodsId(goodsId));
		return mav;
	}
}
