package com.dingcan.tradition.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.model.WeiboException;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.dingcan.api.service.FeixinService;
import com.dingcan.api.service.QqService;
import com.dingcan.base.BaseController;
import com.dingcan.comments.model.Comment;
import com.dingcan.comments.service.CommentsService;
import com.dingcan.cons.Cons;
import com.dingcan.goods.model.Goods;
import com.dingcan.goods.model.GoodsVo;
import com.dingcan.goods.model.GoodsVoCount;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.message.model.Message;
import com.dingcan.message.model.MessageVo;
import com.dingcan.message.service.MessageService;
import com.dingcan.shop.model.Shop;
import com.dingcan.shop.model.ShopVo;
import com.dingcan.shop.service.ShopService;
import com.dingcan.tag.model.Tag;
import com.dingcan.tag.service.TagService;
import com.dingcan.user.model.AutoLogin;
import com.dingcan.user.model.User;
import com.dingcan.user.model.UserThird;
import com.dingcan.user.service.UserService;
import com.dingcan.util.CartManager;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CookieManager;
import com.dingcan.util.CustomDate;
import com.dingcan.util.PageModel;

@Controller
public class ProductController extends BaseController{
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private TagService tagService;
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private FeixinService feixinService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Autowired
	private QqService qqService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("userList", userService.getNewUserList(5));
//		mav.addObject("shopList", shopService.getNewShopList(5));
//		mav.addObject("goodsList", goodsService.getNewGoodsList(5));
		mav.addObject("stroll", messageService.getMessageVoList(1, 12));
		return mav;
	}
	
	@RequestMapping("/index/{id}")
	public void index(@PathVariable int id, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<MessageVo> list = messageService.getMessageVoList(id, 12);
		StringBuffer htmlStr = new StringBuffer();
		for(MessageVo messageVo:list){
			htmlStr.append("<div class=\"item masonry_brick\">");
			htmlStr.append("	<div class=\"item_t\">");
			htmlStr.append("		<div class=\"img\">");
			htmlStr.append("			<a href=\"product.html?id="+messageVo.getGoods().getGoodsId()+"\"><img width=\"210\" height=\"240\" alt=\""+messageVo.getGoods().getGoodsName()+"\" src=\""+messageVo.getGoods().getGoodsUrl()+"\" /></a>");
			htmlStr.append("			<span class=\"price\">￥ "+messageVo.getGoods().getPriceDiscount()+"</span>");
			htmlStr.append("			<div class=\"btns\">");
			htmlStr.append("				<a onclick=\"addCart("+messageVo.getGoods().getGoodsId()+")\" class=\"img_album_btn\">加入餐车</a>");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("	<div class=\"title\">");
			htmlStr.append("		<div class=\"title1\"> ");
			htmlStr.append("		<div class=\"tiimg pull-left\">");
			htmlStr.append("			<a href=\"user.html?id="+messageVo.getUser().getUserId()+"\">");
			htmlStr.append("				<img alt=\""+messageVo.getUser().getUserName()+"\" src=\""+messageVo.getUser().getPhoto50()+"\" />");
			htmlStr.append("			</a>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"titext pull-left\">");
			htmlStr.append("			<div class=\"titext_1\">");
			htmlStr.append("				<div class=\"tname pull-left\"><a href=\"user.html?id="+messageVo.getUser().getUserId()+"\">"+messageVo.getUser().getUserName()+"</a></div>");
			htmlStr.append("				<div class=\"add_follow pull-left\" onclick=\"addFriends("+messageVo.getUser().getUserId()+")\"></div> ");
			htmlStr.append("				<div class=\"clear\"></div>");
			htmlStr.append("			</div>");
			htmlStr.append("			<div class=\"titext_2\">");
			htmlStr.append("				"+messageVo.getDate()+" 买了");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"clear\"></div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"title2\">");
			htmlStr.append("			<h2>"+messageVo.getGoods().getGoodsName()+"</h2>");
			htmlStr.append("		</div>");
			htmlStr.append("	</div>");
			htmlStr.append("	</div>");
			htmlStr.append("	<div class=\"item_b clearfix\">");
			htmlStr.append("		<div class=\"items_likes fl\">");
			htmlStr.append("			<a class=\"like_btn\"  onclick=\"addLike("+messageVo.getGoods().getGoodsId()+")\"></a>");
			htmlStr.append("			<em class=\"bold\" id=\"like"+messageVo.getGoods().getGoodsId()+"\">"+messageVo.getLike()+"</em>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"items_comment fr\"><a onclick=\"buyNow("+messageVo.getGoods().getGoodsId()+")\">立即购买</a><em class=\"bold\">("+messageVo.getSale()+")</em></div>");
			htmlStr.append("	</div>");
			htmlStr.append("</div>");
		}
		out.print(htmlStr);
	}
	
	
//	@RequestMapping(value="/getProductAjax")
//	public @ResponseBody PageModel<GoodsVo> getProductAjax(HttpServletRequest request) throws NumberFormatException, TimeoutException, InterruptedException{
//		String currentPage = request.getParameter("p");	
//		PageModel<GoodsVo> pageModel = goodsService.getNewGoodsVoPage(currentPage);
//		return pageModel;
//	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView getProductPage(HttpServletRequest request) throws Exception{
//		String productId = request.getParameter("id");
//		GoodsVo goods =  goodsService.getGoodsVoByGoodsId(productId);
//		ModelAndView mav = new ModelAndView("detail");
//		mav.addObject("product", goods);
//		mav.addObject("comments", commentsService.getCommentsVoByGoodsId(goods.getGoodsId()));
//		mav.addObject("order", messageService.getOrderListByGoodsId(goods.getGoodsId()));
//		mav.addObject("recommends", goodsService.getGoodsListByShopId(goods.getShopId()));
		int goodsId = Integer.parseInt(request.getParameter("id"));
		GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
		ModelAndView mav = new ModelAndView("detail");
		mav.addObject("product", goodsVo);
		mav.addObject("recommend", shopService.getRecommend(goodsVo.getShopId()));
		mav.addObject("newBuyer", goodsService.getBuyerByGoodsId(goodsId, 6));
		return mav;
	}
	
	/**
	 * 根据类别找商品,get提交过来的,根据类别id找
	 * @param request
	 * @return
	 * @throws Exception
	 * 2013-4-27 上午10:44:29 2013
	 */
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ModelAndView toFind(HttpServletRequest request) throws Exception{
		int cateId = Integer.parseInt(request.getParameter("id"));
		String sort = request.getParameter("sort")==null?"GoodsId":request.getParameter("sort");
		int pageNo = request.getParameter("pageNo")==null?1:Integer.parseInt(request.getParameter("pageNo"));
		ModelAndView mav = new ModelAndView("goodslist");
		mav.addObject("goodsPage", goodsService.getGoodsPageBySort(12, pageNo, sort, cateId));
		mav.addObject("cateId", cateId);
		mav.addObject("cateName", ConfigReader.getValue("product_category").split(",")[cateId]);
		mav.addObject("sort", sort);
		return mav;
	}
	
	/**
	 * 商品页
	 * @param request
	 * @return
	 * @throws Exception
	 * 2013-6-5 下午7:14:33 2013
	 */
	@RequestMapping(value="/goodsList", method = RequestMethod.GET)
	public ModelAndView goodsList(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("goodslist");
		//如果参数没有种类，则查询所以商品
		String cate = request.getParameter("cate");  
		if(cate == null){
			mav.addObject("goodsList", goodsService.getGoodsVoCountListByCate(1, 12, 0));
		}else{
			mav.addObject("goodsList", goodsService.getGoodsVoCountListByCate(1, 12, Integer.parseInt(cate)));
			mav.addObject("cate", cate);
		}
		return mav;
	}
	
	/**
	 * 瀑布流获取商品
	 * @param id
	 * @param response
	 * @throws IOException
	 * 2013-6-5 下午7:57:42 2013
	 */
	@RequestMapping("/goodsList/{id}")
	public void goodsList(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String cate = request.getParameter("cate");
		List<GoodsVoCount> list;
		if("".equals(cate) || cate == null){
			list = goodsService.getGoodsVoCountListByCate(1, 12, id-1);
		}else{
			list = goodsService.getGoodsVoCountListByCate(id, 12, Integer.parseInt(cate));
		} 
		StringBuffer htmlStr = new StringBuffer();
		for(GoodsVoCount goodsVoCount:list){
			htmlStr.append("<div class=\"item masonry_brick\">");
			htmlStr.append("	<div class=\"item_t\">");
			htmlStr.append("		<div class=\"img\">");
			htmlStr.append("			<a href=\"product.html?id="+goodsVoCount.getGoods().getGoodsId()+"\"><img width=\"210\" height=\"240\" alt=\""+goodsVoCount.getGoods().getGoodsName()+"\" src=\""+goodsVoCount.getGoods().getGoodsUrl()+"\" /></a>");
			htmlStr.append("			<span class=\"price\">￥ "+goodsVoCount.getGoods().getPriceDiscount()+"</span>");
			htmlStr.append("			<div class=\"btns\">");
			htmlStr.append("				<a onclick=\"addCart("+goodsVoCount.getGoods().getGoodsId()+")\" class=\"img_album_btn\">加入餐车</a>");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"title\"><span>"+goodsVoCount.getGoods().getGoodsName()+"</span></div>");
			htmlStr.append("	</div>");
			htmlStr.append("	<div class=\"item_b clearfix\">");
			htmlStr.append("		<div class=\"items_likes fl\">");
			htmlStr.append("			<a class=\"like_btn\"  onclick=\"addLike("+goodsVoCount.getGoods().getGoodsId()+")\"></a>");
			htmlStr.append("			<em class=\"bold\" id=\"like"+goodsVoCount.getGoods().getGoodsId()+"\">"+goodsVoCount.getLike()+"</em>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"items_comment fr\"><a onclick=\"buyNow("+goodsVoCount.getGoods().getGoodsId()+")\">立即购买</a><em class=\"bold\">("+goodsVoCount.getSale()+")</em></div>");
			htmlStr.append("	</div>");
			htmlStr.append("</div>");
		}
		out.print(htmlStr);
	}
	
	@RequestMapping(value = "/addLikeGoods", method = RequestMethod.GET)
	public @ResponseBody boolean addLikeShop(HttpServletRequest request){
		int goodsId = Integer.parseInt(request.getParameter("id"));
		User user = getSessionUser(request);
		int userId = user == null ? 0: getSessionUser(request).getUserId();
		return goodsService.addLikeGoods(userId, goodsId);
	}
	
	/**
	 * 逛逛ajax获取商品
	 * @param request
	 * @return
	 * @throws Exception
	 * 2013-5-24 下午3:26:23 2013
	 */
	@RequestMapping("/page/{id}")
	public void getProductPaginationByTagAjax(@PathVariable long id, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<Message> list = messageService.getMessagePageByPageno(id+"").getList();
		StringBuffer htmlStr = new StringBuffer();
		for(Message message:list){
			htmlStr.append("<div class=\"item masonry_brick\">");
			htmlStr.append("	<div class=\"item_t\">");
			htmlStr.append("		<div class=\"img\">");
			htmlStr.append("			<a href=\"product.html?id="+message.getToId()+"\"><img width=\"210\" height=\"240\" alt=\""+message.getContentText()+"\" src=\""+message.getPictureUrl()+"\" /></a>");
			htmlStr.append("			<span class=\"price\">￥195.00</span>");
			htmlStr.append("			<div class=\"btns\">");
			htmlStr.append("				<a onclick=\"addCart("+message.getToId()+")\" class=\"img_album_btn\">加入餐车</a>");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"title\"><span>"+message.getContentText()+"</span></div>");
			htmlStr.append("	</div>");
			htmlStr.append("	<div class=\"item_b clearfix\">");
			htmlStr.append("		<div class=\"items_likes fl\">");
			htmlStr.append("			<a class=\"like_btn\"></a>");
			htmlStr.append("			<em class=\"bold\">916</em>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"items_comment fr\"><a onclick=\"buyNow("+message.getToId()+")\">立即购买</a><em class=\"bold\">(0)</em></div>");
			htmlStr.append("	</div>");
			htmlStr.append("</div>");
		}
		out.print(htmlStr);
	}
	
	@RequestMapping("/dianpu")
	public String toDianPu(){
		return "dianpu";
	}
	
	@RequestMapping("/getLatestShopsAjax")
	public @ResponseBody List<Shop> getLatestShopsAjax(){
		return shopService.getNewShopList();
	}
	
	@RequestMapping("/getLatestTagAjax")
	public @ResponseBody List<Tag> getLatestTagAjax(){
		return tagService.getNewTagList();
	}
	
//	@RequestMapping("/getMostFavoriteProductAjax")
//	public @ResponseBody List<GoodsVo> getMostFavoriteProductAjax(){
//		// TODO 这里实现广告
//		List<GoodsVo> goodsList = new ArrayList<GoodsVo>();
//		goodsList.add(goodsService.getGoodsVoByGoodsId(43+""));
//		goodsList.add(goodsService.getGoodsVoByGoodsId(42+""));
//		return goodsList;
//	}
	
	/**
	 * 随便逛逛
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/stroll", method = RequestMethod.GET)
	public ModelAndView	stroll(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("stroll", messageService.getMessagePageByPageno("1").getList());
		return mav;
	}
	
	/**
	 * 随便逛逛
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json_stroll", method = RequestMethod.GET)
	public @ResponseBody List<Message> json_stroll(HttpServletRequest request){
		return messageService.getMessagePageByPageno(request.getParameter("p")).getList();
	}
	
	/**
	 * 发飞信
	 * @param request
	 * @return
	 * @throws WeiboException
	 * Green Lei
	 * 2012-12-8 下午2:00:05 2012
	 * @throws IOException 
	 */
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public void sendMessage(HttpServletRequest request,HttpServletResponse response) throws WeiboException, IOException {
		request.setCharacterEncoding("utf-8");
		String goodsId = request.getParameter("goodsId");
		String goodsCount = request.getParameter("goodsCount");
		response.setContentType("text/html;charset=UTF-8");
		int goodsIdInt = 0;
		if(goodsId != null){
			goodsIdInt = Integer.parseInt(goodsId);
		}
		Goods goods = goodsService.getGoodsByGoodsId(goodsIdInt);
		Shop shop = shopService.getShopByShopId(goods.getShopId());
		User user = getSessionUser(request);
		StringBuffer message = new StringBuffer();
		message.append(user.getLocation())
				.append("("+user.getPhone()+"):")
				.append(goods.getGoodsName()+"/"+goodsCount+"份");
		if(user == null){
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('抱歉,您还未登陆,请先用选课的学号登陆吧!'); location='index.html';</script>");
		}else if(user.getLocation() == null){
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('抱歉,您还未填写送餐地址,请完善一下吧!'); location='index.html';</script>");
		}else if(user.getPhone() == null){
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('抱歉,您还未填写联系电话,请完善一下吧!'); location='index.html';</script>");
		}else if(feixinService.sendMessage(shop.getFeixin()+"", message.toString())){
			Message messageObj = new Message();
			messageObj.setContentText(user.getUserName()+"订了"+goods.getGoodsName());
			messageObj.setPictureUrl(goods.getGoodsUrl());
			messageObj.setFromId(user.getUserId());
			messageObj.setToId(goods.getGoodsId());
			messageObj.setType(Cons.ADD_MESSAGE_MESSAGE);
			messageService.addMessage(messageObj);
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('短信发送成功,一会就能送到!!'); location='index.html';</script>");
		}else{
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>alert('未知错误,短信发送失败,请重新订吧!!'); location='index.html';</script>");
		}
	}
	
	/**
	 * 未登录下单
	 * @param request
	 * @param response
	 * @return
	 * @throws WeiboException
	 * @throws IOException
	 * 2013-4-30 上午9:51:05 2013
	 */
	@RequestMapping(value = "/orderNoLogin", method = RequestMethod.POST)
	public @ResponseBody boolean orderNoLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, IOException {
		String location = request.getParameter("location");
		String phone = request.getParameter("phone");
		//把信息加入cookie
		userService.addBuyerInfoToCookie(phone, location, response);
		
		List<Goods> goodsList = CartManager.getGoodsList(request);
		
		StringBuffer message = new StringBuffer("["+phone+","+location+"]   ");
		//TODO 这里在发送短信之前就把交易记录插入数据库了,应该在发送短信之后发送
		for(Goods goods:goodsList){
			message.append("["+goods.getGoodsName()+goods.getCount()+"份-"+shopService.getShopByShopId(goods.getShopId()).getShopName()+"]");
			//未登录使用0代替userId
			goodsService.addSale(goods.getShopId(), 0, goods.getGoodsId(), goods.getCount());
		}
		message.append("【订餐吧祝您生意兴隆】");
		//清空购物车
		CartManager.clearGoods(request);
		return feixinService.sendMessage("13898176737", message.toString());
	}
	
	/**
	 * 登录并下单
	 * @param request
	 * @param response
	 * @return
	 * @throws WeiboException
	 * @throws IOException
	 * 2013-4-30 上午9:51:54 2013
	 */
	@RequestMapping(value = "/orderLogin", method = RequestMethod.POST)
	public @ResponseBody int orderLogin(HttpServletRequest request, HttpServletResponse response) throws WeiboException, IOException {
		String phone = request.getParameter("UserMail");
		String userPwd = request.getParameter("UserPwd");
		User userAim = userService.getUserByPhone(phone);
		if(userAim != null && userAim.getUserPwd().equals(userPwd)){
			setSessionUser(request, userAim);
			if("".equals(userAim.getPhone()) || "".equals(userAim.getLocation())){
				return 0;   //用户的电话或者地址为填写
			}
			
			//添加到自动登录
			AutoLogin autoLogin = new AutoLogin();
			autoLogin.setSessionId(request.getSession().getId());
			autoLogin.setUserId(userAim.getUserId()+"");
			userService.addAutoLogin(autoLogin, response); 
			
			//下单
			List<Goods> goodsList = CartManager.getGoodsList(request);
			
			StringBuffer message = new StringBuffer("["+userAim.getPhone()+","+userAim.getLocation()+"]   ");
			for(Goods goods:goodsList){
				message.append("["+goods.getGoodsName()+goods.getCount()+"份-"+shopService.getShopByShopId(goods.getShopId()).getShopName()+"]");
				goodsService.addSale(goods.getShopId(), userAim.getUserId(), goods.getGoodsId(), goods.getCount());
			}
			message.append("【订餐吧祝您生意兴隆】");
			//清空购物车
			CartManager.clearGoods(request);
			feixinService.sendMessage("13898176737", message.toString());
			return 1;        //下单成功
		}else if(userAim != null && !userAim.getUserPwd().equals(userPwd)){
			ModelAndView model = new ModelAndView("redirect:myhome.html");
			model.addObject("message", "密码错误!");
			return 2;
		}else if(userAim == null){
			ModelAndView model = new ModelAndView("redirect:myhome.html");
			model.addObject("message", "账号不存在!");
			return 3;
		}else{
			ModelAndView model = new ModelAndView("redirect:myhome.html");
			model.addObject("message", "登录失败,未知错误!");
			return -1;
		}
	}
	
	/**
	 * 已登录后下单
	 * @param request
	 * @return
	 * @throws WeiboException
	 * @throws IOException
	 * 2013-4-30 上午10:46:43 2013
	 */
	@RequestMapping(value = "/orderLogined", method = RequestMethod.GET)
	public @ResponseBody boolean orderLogined(HttpServletRequest request) throws WeiboException, IOException {
		User user = getSessionUser(request);
		String location = user.getLocation();
		String phone = user.getPhone();
		
		List<Goods> goodsList = CartManager.getGoodsList(request);
		
		StringBuffer message = new StringBuffer("["+phone+","+location+"]");
		for(Goods goods:goodsList){
			message.append("["+goods.getGoodsName()+goods.getCount()+"份-"+shopService.getShopByShopId(goods.getShopId()).getShopName()+"]");
			goodsService.addSale(goods.getShopId(), user.getUserId(), goods.getGoodsId(), goods.getCount());
			
			//发一条新鲜事
			Message newMessage = new Message();
			newMessage.setContentText(":购买了{"+goods.getGoodsName()+"]");
			newMessage.setFromId(user.getUserId());
			newMessage.setPictureUrl(goods.getGoodsUrl());
			newMessage.setToId(goods.getGoodsId());
			newMessage.setType(Cons.ADD_GOODS_MESSAGE);
			messageService.addMessage(newMessage);
		}
		message.append("【订餐吧祝您生意兴隆】");
		//清空购物车
		CartManager.clearGoods(request);
//		//发qq状态
//		UserThird userThird= userService.getUserThirdByUserIdAndLoginType(getSessionUser(request).getUserId()+"", 3);	
//		if(userThird != null){
//			String openid = userThird.getThirdUserId();
//	        String access_token = userThird.getAccessToken();
//			qqService.updateStatus(access_token, openid, "不仅吃省点,还有吃好点,就来订餐吧.", "http://www.xn--4qrz40kyoi.com/product.html?id="+goodsList.get(0).getGoodsId(), "刚刚在#订餐吧#订了一份饭,如果注册了点两下鼠标就能订餐,挺方便的,送的还快,建议以后都网上订餐吧", 
//					goodsList.get(0).getGoodsName(),"http://www.xn--4qrz40kyoi.com/product.html?id="+goodsList.get(0).getGoodsId(), "http://www.xn--4qrz40kyoi.com/"+goodsList.get(0).getGoodsUrl());
//		}

		return feixinService.sendMessage("13898176737", message.toString());
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ModelAndView addComment(@ModelAttribute("commentForm") Comment comment,
			HttpServletRequest request) {
		ModelAndView mav = null;
		User user = getSessionUser(request);
		Goods goods = goodsService.getGoodsByGoodsId(comment.getGoodsId());
		if(comment.getCommenterId() == 0){    //未登陆,跳转到登陆页面
			mav = new ModelAndView("mprompt");
			mav.addObject("prompt", ConfigReader.getValue("message_comment_tip1"));
			return mav;
		}else if(comment.getContent() == null || "".equals(comment.getContent())){    //评论为空
			mav = new ModelAndView("msingle");
			mav.addObject("Goods", goodsService.getGoodsByGoodsId(comment.getGoodsId()));
			mav.addObject("comments", commentsService.getCommentsByGoodsId(comment.getGoodsId()));
			mav.addObject("message", ConfigReader.getValue("message_comment_tip2"));
			return mav;
		}else{
			commentsService.addComment(comment);
			Message message = new Message();
			message.setContentText(user.getUserName()+":"+comment.getContent());
			message.setFromId(comment.getCommenterId());
			message.setPictureUrl(goods.getGoodsUrl());
			message.setToId(comment.getGoodsId());
			message.setType(Cons.ADD_COMMENT_MESSAGE);
			messageService.addMessage(message);
			
			return new ModelAndView("redirect:product.html?productId="+comment.getGoodsId());
		}
	}
	
	/**
	 * 产品排行榜
	 * @return
	 */
	@RequestMapping(value = "/goodsBillboard", method = RequestMethod.GET)
	public ModelAndView	goodsBillboard(){
		ModelAndView mav = new ModelAndView("side_nav_goods");
		mav.addObject("newgoods", goodsService.getNewGoodsList(5));
		return mav;
	}
	
	/**
	 * 商家排行榜
	 * @return
	 */
	@RequestMapping(value = "/shopBillboard", method = RequestMethod.GET)
	public ModelAndView	shopBillboard(){
		ModelAndView mav = new ModelAndView("side_nav_shop");
		mav.addObject("newshop", shopService.getNewShopList(5));
		return mav;
	}
	
	/**
	 * 产品分类展示
	 * @return
	 */
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView	category(){
		ModelAndView mav = new ModelAndView("goodscate");
		String[] pcate = ConfigReader.getValue("product_category").split(",");
		for(int n =0; n < pcate.length; n++){
			mav.addObject("goodslist"+n, goodsService.getGoodsListByCate(n+""));
		}
		return mav;
	}
	
	/**
	 * 转到购物车
	 * @return
	 * 2013-4-27 下午1:05:46 2013
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView	cart(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("cart","GoodsList",CartManager.getGoodsList(request));
		return mav;
	}
	
	/**
	 * 结账
	 * @return
	 * 2013-4-27 下午1:45:17 2013
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView	checkout(HttpServletRequest request){
		 Cookie[] cookies = request.getCookies();
		 String buyerLocation = "";
		 String buyerPhone = "";
	     if(cookies != null){
	    	 buyerLocation = CookieManager.getCookieValue(cookies, Cons.BUYER_LOCATION);
	    	 buyerPhone = CookieManager.getCookieValue(cookies, Cons.BUYER_PHONE);
	     }
		ModelAndView mav = new ModelAndView("checkout");
		mav.addObject("location", buyerLocation);
		mav.addObject("phone", buyerPhone);
		mav.addObject("user", getSessionUser(request));
		return mav;
	}
	
	/**
	 * 购物车提交过来的
	 * @param request
	 * @return
	 * 2013-5-6 下午5:52:51 2013
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String	checkoutPost(HttpServletRequest request){
		//先清除session中原有的物品
		CartManager.clearGoods(request);
		String[] goodsId = request.getParameterValues("GoodsId");
		String[] goodsCount = request.getParameterValues("GoodsCount");
		Goods goods = null;
		for(int n = 0; n < goodsId.length; n++){
			goods = goodsService.getGoodsByGoodsId(Integer.parseInt(goodsId[n]));
			goods.setCount(Integer.parseInt(goodsCount[n]));
			CartManager.addGoods(goods, request);
		}
		return "redirect:checkout.html";
	}
	
	/**
	 * 往购物车加入
	 * @param request
	 * @param response
	 * @return
	 * @throws WeiboException
	 * @throws IOException
	 * 2013-4-29 下午12:32:30 2013
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.GET)
	public @ResponseBody boolean addCart(HttpServletRequest request) {
		int goodsId = Integer.parseInt(request.getParameter("id"));
		Goods goods = goodsService.getGoodsByGoodsId(goodsId);
		if(request.getParameter("count") != null){
			goods.setCount(Integer.parseInt(request.getParameter("count")));
		}else{
			goods.setCount(1);
		}
		boolean flag = false;
		try{
			CartManager.addGoods(goods, request);
			flag = true;
		}catch(Exception e){
			
		}
		return flag;
	}
	
	/**
	 * 购物车中删除商品
	 * @param request
	 * @return
	 * 2013-4-29 下午2:11:31 2013
	 */
	@RequestMapping(value = "/delCart", method = RequestMethod.GET)
	public String delCart(HttpServletRequest request) {
		int goodsId = Integer.parseInt(request.getParameter("id"));
		Goods goods = goodsService.getGoodsByGoodsId(goodsId);
		CartManager.delGoods(goods, request);
		return "redirect:cart.html";
	}
	
	/**
	 * 查询商品或商家
	 * @param request
	 * @return
	 * 2013-5-6 下午7:46:58 2013
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("goodslist", goodsService.getGoodsListByQuery(keyword));
		mav.addObject("shoplist", goodsService.getShopListByQuery(keyword));
		return mav;
	}
	
	/**
	 * 积分商城
	 * @param request
	 * @return
	 * 2013-5-10 上午3:48:16 2013
	 */
	@RequestMapping(value = "/credits", method = RequestMethod.GET)
	public ModelAndView credits(HttpServletRequest request) {
		return new ModelAndView("credits");
	}
	
	/**
	 * 限时抢购
	 * @param request
	 * @return
	 * 2013-5-10 上午3:49:08 2013
	 */
	@RequestMapping(value = "/snapUp", method = RequestMethod.GET)
	public ModelAndView snapUp(HttpServletRequest request) {
		return new ModelAndView("snap");
	}
	
	/**
	 * 在线支付
	 * @param request
	 * @return
	 * 2013-5-23 上午11:25:50 2013
	 */
	
	@RequestMapping(value = "/aliPay", method = RequestMethod.GET)
	public ModelAndView aliPay(HttpServletRequest request) {
		User user = getSessionUser(request);
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "trade_create_by_buyer");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", "http://www.xn--4qrz40kyoi.com/alipay_notify.html");
		sParaTemp.put("return_url", "http://www.xn--4qrz40kyoi.com/alipay_return.html");
		sParaTemp.put("seller_email", "13898176737");
		sParaTemp.put("out_trade_no", CustomDate.getOrderNum());
		sParaTemp.put("subject", "来自订餐吧的订单");
		sParaTemp.put("price", Double.toString(CartManager.getTotalPrice(request)));
		sParaTemp.put("quantity", "1");
		sParaTemp.put("logistics_fee", "0.00");
		sParaTemp.put("logistics_type", "EXPRESS");
		sParaTemp.put("logistics_payment", "SELLER_PAY");
		sParaTemp.put("body", "来自订餐吧的订单");
		sParaTemp.put("show_url", "http://www.xn--4qrz40kyoi.com/");
		sParaTemp.put("receive_name", user == null?"未登录":user.getUserName());
		sParaTemp.put("receive_address", user == null?"未登录":user.getLocation());
		sParaTemp.put("receive_zip", "110000");
		sParaTemp.put("receive_phone", user == null?"未登录":user.getPhone());
		sParaTemp.put("receive_mobile", user == null?"未登录":user.getPhone());		
		System.out.println( AlipaySubmit.buildRequest(sParaTemp,"post","确认"));
		return new ModelAndView("alipay", "sHtmlText",  AlipaySubmit.buildRequest(sParaTemp,"post","确认"));
	}

	@RequestMapping(value = "/alipay_notify")
	public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws WeiboException, IOException {
		PrintWriter out = response.getWriter();

		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					out.println("success");	//请不要修改或删除
				} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
					
					out.println("success");	//请不要修改或删除
				} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
				//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
				
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					out.println("success");	//请不要修改或删除
				} else if(trade_status.equals("TRADE_FINISHED")){
				//该判断表示买家已经确认收货，这笔交易完成
				
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					out.println("success");	//请不要修改或删除
				}
				else {
					out.println("success");	//请不要修改或删除
				}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			out.println("fail");
		}		
		
	}
	
	@RequestMapping(value = "/alipay_return")
	public ModelAndView alipay_return(HttpServletRequest request) throws UnsupportedEncodingException, WeiboException {
		ModelAndView mav = new ModelAndView("alipay_return");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
		
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
			
			//该页面可做页面美工编辑
			mav.addObject("message", "客官您好，已经成功为您下单，请坐等美食，网上订餐就是这么方便！");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			mav.addObject("message", "客官抱歉，下单失败，请联系客服！");
		}		
		return mav;
	}
}
