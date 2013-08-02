package com.dingcan.api.service;

import org.springframework.stereotype.Service;

import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

import com.dingcan.goods.model.Goods;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CustomDate;
import com.dingcan.util.RandomInt;

@Service
public class QqService extends ApiService {
	/**
	 * Green Lei
	 * 2012-12-7 下午1:22:32 2012
	 */
	private static final long serialVersionUID = 1L;

	public String getOpenId(String accessToken) throws WeiboException{
		Response res = client.post(
				ConfigReader.getValue("qq_baseURL")+"me",
				new PostParameter[] {
						new PostParameter("access_token", accessToken)}, false);
		String ret = res.toString();
		String openid = ret.substring(ret.indexOf("openid")+9, ret.length()-6);
		return openid;
	}
	
	public String getAccessToken(String code) throws WeiboException{
		Response res = client.post(
				ConfigReader.getValue("qq_accessTokenURL"),
				new PostParameter[] {
						new PostParameter("client_id", ConfigReader.getValue("qq_client_ID")),
						new PostParameter("client_secret", ConfigReader.getValue("qq_client_KEY")),
						new PostParameter("grant_type", "authorization_code"),
						new PostParameter("code", code),
						new PostParameter("state", "qq"),
						new PostParameter("redirect_uri", ConfigReader.getValue("qq_redirect_URI_mobile"))}, false);
		return res.toString().substring(13,res.toString().indexOf("&expires_in="));
	}
	
	public JSONObject getUserInfo(String accessToken, String openId) throws WeiboException{
		Response resUserInfo = client.post(
				ConfigReader.getValue("qq_GET_USER_INFO"),
				new PostParameter[] {
						new PostParameter("oauth_consumer_key", ConfigReader.getValue("qq_client_ID")),
						new PostParameter("access_token", accessToken),
						new PostParameter("openid", openId)}, false);
		String strUserInfo = resUserInfo.toString();
		JSONObject json =resUserInfo.asJSONObject();
		return json;
	}
	
	public boolean updateStatus(String access_token,String openid,String title,String url,
			String comment,String summary,String fromurl,String images) throws WeiboException{
		boolean flag = false;
		Response resUpdateStatus = client.post(
				ConfigReader.getValue("qq_ADD_SHARE"),
				new PostParameter[] {
						new PostParameter("oauth_consumer_key", ConfigReader.getValue("qq_client_ID")),
						new PostParameter("access_token", access_token),
						new PostParameter("openid", openid),
						new PostParameter("format", "xml"),
						new PostParameter("title", title),
						new PostParameter("url", url),
						new PostParameter("comment", comment),
						new PostParameter("summary", summary),
						new PostParameter("fromurl", fromurl),
						new PostParameter("images", images)}, false);
		System.out.println(resUpdateStatus);
		return flag;
	}
	
	/**
	 * 添加qq空间提醒
	 * @param access_token
	 * @param openid
	 * @param title
	 * @param url
	 * @param comment
	 * @param summary
	 * @param fromurl
	 * @param images
	 * @return
	 * @throws WeiboException
	 * 2013-5-14 下午8:16:02 2013
	 */
	public boolean addTip(String token, String openid) throws WeiboException{
		Goods goods = new GoodsService().getGoodsByGoodsId(RandomInt.getRandomInt(50, 50));
		boolean flag = false;
		Response resUpdateStatus = client.post(
				ConfigReader.getValue("qq_ADD_SHARE"),
				new PostParameter[] {
						new PostParameter("oauth_consumer_key", ConfigReader.getValue("qq_client_ID")),
						new PostParameter("access_token", token),
						new PostParameter("openid", openid),
						new PostParameter("format", "xml"),
						new PostParameter("title", "今日为您推荐："+goods.getGoodsName()),
						new PostParameter("url", "http://www.xn--4qrz40kyoi.com/product.html?id="+goods.getGoodsId()),
						new PostParameter("comment", "订餐吧提醒您:吃饭时间到了，妈妈说订餐才是王道, 不仅吃省点,还要吃好点,就来订餐吧."),
						new PostParameter("fromurl", "http://www.xn--4qrz40kyoi.com/product.html?id="+goods.getGoodsId()),
						new PostParameter("images", "http://www.xn--4qrz40kyoi.com/"+goods.getGoodsUrl())}, false);
		System.out.println(resUpdateStatus);
		return flag;
	}
}
