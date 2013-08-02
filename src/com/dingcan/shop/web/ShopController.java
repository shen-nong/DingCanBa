package com.dingcan.shop.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dingcan.base.BaseController;
import com.dingcan.cons.Cons;
import com.dingcan.goods.model.Goods;
import com.dingcan.goods.model.GoodsVoCount;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.message.model.Message;
import com.dingcan.message.service.MessageService;
import com.dingcan.shop.model.Shop;
import com.dingcan.shop.model.ShopVo;
import com.dingcan.shop.service.ShopService;
import com.dingcan.user.model.User;
import com.dingcan.util.FileUploadForm;
import com.dingcan.util.FileUploadUtil;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.ImageCut;
import com.dingcan.util.PageModel;
import com.dingcan.util.ResizeImage;

/**
 * We have annotated the argument Contact with annotation @ModelAttribute. 
 * This will binds the data from request to the object Contact.
 * @author LiHJam
 *
 */
@Controller
public class ShopController extends BaseController{
	@Autowired
	private ShopService shopService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/addgoods")
	public ModelAndView addgoods(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("saddgoods");
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		mav.addObject("goodsList", shopService.getGoodsListByShopId(shop.getShopId()));	
		mav.addObject("shop", shop);
		String[] pcate = ConfigReader.getValue("product_category").split(",");
		mav.addObject("pcate", pcate);
		return mav;
	}
	
	@RequestMapping("/managegoods")
	public ModelAndView manage(HttpServletRequest request) {
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		ModelAndView mav = new ModelAndView("smanagegoods");
		mav.addObject("goodsList", shopService.getGoodsListByShopId(shop.getShopId()));
		mav.addObject("shop", shop);
		return mav;
	}
	
	/**
	 * 移动app上,商家登陆的验证
	 * @param request
	 * @return
	 * Green Lei
	 * 2012-12-16 下午8:54:52 2012
	 */
	@RequestMapping(value = "/valudateShopApp")
	public @ResponseBody int valudateShopApp(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String pasw = request.getParameter("pasw");
		int returnNum = 0;           //返回0,表示出现未知错误
		
		Shop shopAim = shopService.getShopByAccount(account);
		if(shopAim != null && shopAim.getShopPassword().equals(pasw)){
			setSessionShop(request, shopAim);
			returnNum = 3;
		}else if(shopAim != null && !shopAim.getShopPassword().equals(pasw)){
			returnNum = 2;
		}else if(shopAim == null){      //账号不存在
			returnNum = 1;
		}
		return returnNum;
	}
	
	@RequestMapping("/getGoodsShop")
	public ModelAndView getGoodsShop(HttpServletRequest request) {
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		ModelAndView mav = new ModelAndView("sgoods");
		mav.addObject("GoodsListMap", shopService.getGoodsByShopId(shop.getShopId()));
		return mav;
	}
	
	@RequestMapping("/getHelpShop")
	public ModelAndView getHelpShop() {
		return new ModelAndView("shelp");
	}
	
	@RequestMapping("/getHomeShop")
	public ModelAndView getHomeShop() {
		return new ModelAndView("shome");
	}
	
	@RequestMapping("/getShopShop")
	public ModelAndView getShopShop() {
		return new ModelAndView("sshop");
	}
	
	@RequestMapping("/addGoodsShop")
	public String addGoodsShop(@ModelAttribute("goodsForm") FileUploadForm goodsForm,
			HttpServletRequest request) {
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		Goods goods = new Goods();
		goods.setGoodsDescription(request.getParameter("goodsDescription"));
		goods.setGoodsName(request.getParameter("goodsName"));
		goods.setPriceDiscount(Double.parseDouble(request.getParameter("goodsPrice")));
		goods.setPriceOrganal(Double.parseDouble(request.getParameter("price")));
		goods.setShopId(shop.getShopId());
		goods.setCate(Integer.parseInt(request.getParameter("cate")));
		
		//文件上传
		List<MultipartFile> files = goodsForm.getFiles();
		String imagePath = FileUploadUtil.generateImageURL(request);
		String imageUrl = null;    //数据库中保存的url
		if(null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				//扩展名
				String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
				String fileName = System.currentTimeMillis() + "." + fileExt;
				imageUrl = "upload"+imagePath.split("upload")[1]+"200w"+fileName;
				try{
					FileUploadUtil.inputstreamtofile(multipartFile.getInputStream(), imagePath, fileName);
					//放缩图片
					ImageCut.scale(imagePath+fileName, imagePath+"200w"+fileName,210,240);
					ImageCut.scale(imagePath+fileName, imagePath+"360w"+fileName,360,480);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		
		//把商品信息保存到数据库
		goods.setGoodsUrl(imageUrl);
		int goodsId = shopService.addGoods(goods);
		
		//发一条系统消息
		Message message = new Message();
		message.setContentText(shop.getShopName()+":添加新商品["+goods.getGoodsName()+"]");
		message.setFromId(shop.getShopId());
		message.setPictureUrl(imageUrl);
		message.setToId(goodsId);
		message.setType(Cons.ADD_GOODS_MESSAGE);
		messageService.addMessage(message);
		return "redirect:addgoods.html";
	}
	
	/**
	 * 移动app上传照片
	 * @param request
	 * Green Lei
	 * 2012-12-17 下午2:49:53 2012
	 */
	@RequestMapping("/addGoodsShopApp")
	public void addGoodsShopApp(HttpServletRequest request) {
		String imageUrl = null;    //数据库中保存的url
		Goods goods = new Goods();
		Shop shop = getSessionShop(request);
		goods.setShopId(shop.getShopId());
		
		try{
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items = upload.parseRequest(request);
			String dir = FileUploadUtil.generateImageURL(request);
			File dirFile = new File(dir);
			for(FileItem item : items){
				if(item.isFormField()) {//如果文本类型参数
					if("goodsName".equals(item.getFieldName())){
						goods.setGoodsName(request.getParameter("goodsName"));
					}
					if("goodsPrice".equals(item.getFieldName())){
						goods.setPriceDiscount(Double.parseDouble(request.getParameter("goodsPrice")));
					}
				}else{//如果文件类型参数
					String nameItem = item.getName().substring(item.getName().indexOf("\\")+1, item.getName().length());
					imageUrl = "upload"+dir.split("upload")[1]+nameItem;
					File saveFile = new File(dirFile, nameItem);//item.getName());
					item.write(saveFile);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//把商品信息保存到数据库
		goods.setGoodsUrl(imageUrl);
		int goodsId = shopService.addGoods(goods);
		
		//发一条系统消息
		Message message = new Message();
		message.setContentText(shop.getShopName()+":添加新商品["+goods.getGoodsName()+"]");
		message.setFromId(shop.getShopId());
		message.setPictureUrl(imageUrl);
		message.setToId(goodsId);
		message.setType(Cons.ADD_GOODS_MESSAGE);
		messageService.addMessage(message);
	}
	
	@RequestMapping("/getGoodsById")
	public ModelAndView getGoodsById(HttpServletRequest request){
		String goodsId = request.getParameter("id");
		ModelAndView mav = new ModelAndView("sgoodsView");
		mav.addObject("goods", shopService.getGoodsByGoodsId(Integer.parseInt(goodsId)));
		return mav;
	}
	
	
	/**
	 * 查询商家列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopList")
	public ModelAndView shopList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("shoplist");
		String sort = request.getParameter("sort");
		if("sale".equals(sort)){
			mav.addObject("shopList", shopService.getShopVoListBySales(1, 12));
			mav.addObject("sort", sort);
		}else if("name".equals(sort)){
			mav.addObject("shopList", shopService.getShopVoListByShoptypeAndSort(1, 12, 1, "ShopName"));
			mav.addObject("sort", sort);
		}else if("sum".equals(sort)){
			mav.addObject("shopList", shopService.getShopVoListBySum(1, 12));
			mav.addObject("sort", sort);
		}else{
			mav.addObject("shopList", shopService.getShopVoListByShoptypeAndSort(1, 12, 1, "ShopAccount"));
			mav.addObject("sort", sort);
		}
		return mav;
	}
	
	/**
	 * Ajax获取商铺
	 * @param id
	 * @param response
	 * @throws IOException
	 * 2013-6-5 下午2:18:07 2013
	 */
	@RequestMapping("/shopList/{id}")
	public void shopList(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<ShopVo> list;
		String sort = request.getParameter("sort");
		if("sale".equals(sort)){
			list = shopService.getShopVoListBySales(id, 12);
		}else if("name".equals(sort)){
			list = shopService.getShopVoListByShoptypeAndSort(id, 12, 1, "ShopName");
		}else if("sum".equals(sort)){
			list = shopService.getShopVoListBySum(id, 12);
		}else{
			list = shopService.getShopVoListByShoptypeAndSort(id, 12, 1, "ShopId");
		}
		StringBuffer htmlStr = new StringBuffer();
		for(ShopVo shopVo:list){
			htmlStr.append("<div class=\"item masonry_brick\">");
			htmlStr.append("	<div class=\"item_t\">");
			htmlStr.append("		<div class=\"img\">");
			htmlStr.append("			<a href=\"shop.html?id="+shopVo.getShop().getShopId()+"\"><img width=\"210\" height=\"240\" alt=\""+shopVo.getShop().getShopName()+"\" src=\""+shopVo.getShop().getSignboardUrl()+"\" /></a>");
			htmlStr.append("			<span class=\"price\">营业额 ￥"+shopVo.getTurnover()+"</span>");
			htmlStr.append("			<div class=\"btns\">");
			htmlStr.append("				<a onclick=\"addCollection("+shopVo.getShop().getShopId()+")\" class=\"img_album_btn\">加入收藏</a>");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"title\"><span>"+shopVo.getShop().getShopName()+"</span></div>");
			htmlStr.append("	</div>");
			htmlStr.append("	<div class=\"item_b clearfix\">");
			htmlStr.append("		<div class=\"items_likes fl\">");
			htmlStr.append("			<a class=\"like_btn\"  onclick=\"addLike("+shopVo.getShop().getShopId()+")\"></a>");
			htmlStr.append("			<em class=\"bold\" id=\"like"+shopVo.getShop().getShopId()+"\">"+shopVo.getLike()+"</em>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"items_comment fr\"><a  href=\"shop.html?id="+shopVo.getShop().getShopId()+"\">去看看</a><em class=\"bold\">("+shopVo.getView()+")</em></div>");
			htmlStr.append("	</div>");
			htmlStr.append("</div>");
		}
		out.print(htmlStr);
	}
	
	/**
	 * 商铺装修
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopSet")
	public ModelAndView shopSet(HttpServletRequest request){
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		ModelAndView mav = new ModelAndView("sshopset");
		mav.addObject("shop", shopService.getShopByShopId(shop.getShopId()));
		return mav;
	}
	
	@RequestMapping(value = "/setShopInfo", method = RequestMethod.POST)
	public @ResponseBody boolean setInfo(HttpServletRequest request) {
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		shop.setFeixin(request.getParameter("feixin"));
		shop.setShopName(request.getParameter("shopName"));
		shop.setShopDescription(request.getParameter("description"));
		return shopService.setShopInfo(shop);
	}
	
	@RequestMapping(value = "/setLogo", method = RequestMethod.POST)
	public String setLogo(@ModelAttribute("goodsForm") FileUploadForm goodsForm,
			HttpServletRequest request){
		//文件上传
		List<MultipartFile> files = goodsForm.getFiles();
		String imagePath = FileUploadUtil.genetateShopimgPath(request);
		String imageUrl = null;    //数据库中保存的url
		if(null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
				//扩展名
				String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
				String fileName = System.currentTimeMillis() + "." + fileExt;
				imageUrl = "shoplogo"+imagePath.split("shoplogo")[1]+"200w"+fileName;
				try{
					FileUploadUtil.inputstreamtofile(multipartFile.getInputStream(), imagePath, fileName);
					ImageCut.scale(imagePath+fileName, imagePath+"200w"+fileName,200,160);
					ImageCut.scale(imagePath+fileName, imagePath+"70"+fileName,70,60);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
		//更新用户头像
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		shop.setSignboardUrl(imageUrl);
		shopService.setShopLogo(shop);
		return "redirect:shopSet.html";
	}
	
	/**
	 * 卖家账单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sbill", method = RequestMethod.GET)
	public ModelAndView	sbill(HttpServletRequest request){
		Shop shop = shopService.getShopByUserId(getSessionUser(request).getUserId());
		ModelAndView mav = new ModelAndView("sbill");
		mav.addObject("recommend", shopService.getRecommend(shop.getShopId()));
		//TODO从用户获得商家id时,转了好几次,需要改进,在用户表中加入一个商家的id
		mav.addObject("bill", goodsService.getSalesRecByShopId(shopService.getShopByUserId(getSessionUser(request).getUserId()).getShopId()));
		mav.addObject("goodsList", shopService.getGoodsListByShopId(shop.getShopId()));	
		mav.addObject("shop", shop);
		return mav;
	}
	
	/**
	 * 删除商品
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delGoods", method = RequestMethod.GET)
	public ModelAndView	delGoods(HttpServletRequest request){
		String id = request.getParameter("id");
		shopService.delGoodsById(id);
		return new ModelAndView("redirect:managegoods.html");
	}
	
	/**
	 * 推荐的商品,也会加入产品分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addRecommend", method = RequestMethod.GET)
	public @ResponseBody boolean addRecommend(HttpServletRequest request){
		int goodsId = Integer.parseInt(request.getParameter("id"));
		int shopId = shopService.getShopByUserId(getSessionUser(request).getUserId()).getShopId();
		return shopService.addRecommend(shopId, goodsId);
	}
	
	/**
	 * 我的商铺
	 * @param request
	 * @return
	 * 2013-4-29 下午12:01:41 2013
	 */
	@RequestMapping(value = "/myshop", method = RequestMethod.GET)
	public ModelAndView myshop(HttpServletRequest request) {
		User user = getSessionUser(request);
		String message = "您还未登陆,请先登陆或注册!";
		if(request.getParameter("message")!=null){
			try {
				message = new String(request.getParameter("message").getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(user == null){
			ModelAndView mav = new ModelAndView("login", "command", new User());
			mav.addObject("message", message);
			mav.addObject("home", "myshop.html"); //要转到的页面
			return mav;
		}
		ModelAndView mav = new ModelAndView("shome");
		Shop shop = shopService.getShopByUserId(user.getUserId());
		mav.addObject("goodsList", shopService.getGoodsListByShopId(shop.getShopId()));	
		mav.addObject("shop", shop);
		return mav;
	}
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public ModelAndView shop(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		User user = getSessionUser(request);
		int userId = user == null ? 0: getSessionUser(request).getUserId();
		shopService.addViewShop(userId, id);
		ModelAndView mav = new ModelAndView("shop");
		mav.addObject("goodsList", shopService.getGoodsVoCountByShopId(1, 6, id));	
		mav.addObject("shop", shopService.getShopByShopId(id));
		return mav;
	}
	
	@RequestMapping("/shop/{id}")
	public void goodsList(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		StringBuffer htmlStr = new StringBuffer();
		for(GoodsVoCount goodsVoCount:shopService.getGoodsVoCountByShopId(id, 6, shopId)){
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
	
	/**
	 * 旅店详情
	 * @param request
	 * @return
	 * 2013-6-6 下午7:51:36 2013
	 */
	@RequestMapping(value = "/hotel", method = RequestMethod.GET)
	public ModelAndView hotel(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		User user = getSessionUser(request);
		int userId = user == null ? 0: getSessionUser(request).getUserId();
		shopService.addViewShop(userId, id);
		ModelAndView mav = new ModelAndView("hotel");
		mav.addObject("goodsList", shopService.getGoodsVoCountByShopId(1, 20, id));	
		mav.addObject("shop", shopService.getShopByShopId(id));
		return mav;
	}
	
	/**
	 * 喜欢商家
	 * @param request
	 * @return
	 * 2013-6-5 上午10:49:47 2013
	 */
	@RequestMapping(value = "/addLikeShop", method = RequestMethod.GET)
	public @ResponseBody boolean addLikeShop(HttpServletRequest request){
		int shopId = Integer.parseInt(request.getParameter("id"));
		User user = getSessionUser(request);
		int userId = user == null ? 0: getSessionUser(request).getUserId();
		return shopService.addLikeShop(userId, shopId);
	}
	
	
	/**
	 * 获取宾馆
	 * @param request
	 * @return
	 * 2013-6-6 下午6:25:03 2013
	 */
	@RequestMapping("/hotelList")
	public ModelAndView hotelList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("hotellist");
		mav.addObject("shopList", shopService.getShopVoListByShoptypeAndSort(1, 12, 2, "ShopId"));
		return mav;
	}
	
	/**
	 * Ajax获取商铺
	 * @param id
	 * @param response
	 * @throws IOException
	 * 2013-6-5 下午2:18:07 2013
	 */
	@RequestMapping("/hotelList/{id}")
	public void hotelList(@PathVariable int id, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<ShopVo> list = shopService.getShopVoListByShoptypeAndSort(1, 12, 2, "ShopId");
		StringBuffer htmlStr = new StringBuffer();
		for(ShopVo shopVo:list){
			htmlStr.append("<div class=\"item masonry_brick\">");
			htmlStr.append("	<div class=\"item_t\">");
			htmlStr.append("		<div class=\"img\">");
			htmlStr.append("			<a href=\"shop.html?id="+shopVo.getShop().getShopId()+"\"><img width=\"210\" height=\"240\" alt=\""+shopVo.getShop().getShopName()+"\" src=\""+shopVo.getShop().getSignboardUrl()+"\" /></a>");
			htmlStr.append("			<span class=\"price\">营业额 ￥"+shopVo.getTurnover()+"</span>");
			htmlStr.append("			<div class=\"btns\">");
			htmlStr.append("				<a onclick=\"addCollection("+shopVo.getShop().getShopId()+")\" class=\"img_album_btn\">加入收藏</a>");
			htmlStr.append("			</div>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"title\"><span>"+shopVo.getShop().getShopName()+"</span></div>");
			htmlStr.append("	</div>");
			htmlStr.append("	<div class=\"item_b clearfix\">");
			htmlStr.append("		<div class=\"items_likes fl\">");
			htmlStr.append("			<a class=\"like_btn\"  onclick=\"addLike("+shopVo.getShop().getShopId()+")\"></a>");
			htmlStr.append("			<em class=\"bold\" id=\"like"+shopVo.getShop().getShopId()+"\">"+shopVo.getLike()+"</em>");
			htmlStr.append("		</div>");
			htmlStr.append("		<div class=\"items_comment fr\"><a  href=\"shop.html?id="+shopVo.getShop().getShopId()+"\">去看看</a><em class=\"bold\">("+shopVo.getView()+")</em></div>");
			htmlStr.append("	</div>");
			htmlStr.append("</div>");
		}
		out.print(htmlStr);
	}
} 
