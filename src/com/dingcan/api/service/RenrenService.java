package com.dingcan.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.dingcan.goods.model.Goods;
import com.dingcan.goods.service.GoodsService;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CustomDate;
import com.dingcan.util.RandomInt;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.RenrenApiConfig;
import com.renren.api.client.param.impl.AccessToken;
import com.renren.api.client.utils.HttpURLUtils;
import com.renren.api.client.utils.Md5Utils;

@Service
public class RenrenService extends ApiService {
	/**
	 * Green Lei
	 * 2012-12-7 下午1:22:14 2012
	 */
	private static final long serialVersionUID = 1L;
	
    private static final String CONTENT_TYPE_BMP  = "image/bmp";

    private static final String CONTENT_TYPE_PNG  = "image/png";

    private static final String CONTENT_TYPE_GIF  = "image/gif";

    private static final String CONTENT_TYPE_JPEG = "image/jpeg";

    private static final String CONTENT_TYPE_JPG  = "image/jpg";	
    
    public static final String FORMAT_JSON="JSON";

	public JSONObject getTokenAndUidByCode(String code){
		String rrOAuthTokenEndpoint = ConfigReader.getValue("renren_accessTokenURL");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", ConfigReader.getValue("renrenAppID"));
        parameters.put("client_secret", ConfigReader.getValue("renrenApiSecret"));
        parameters.put("redirect_uri", ConfigReader.getValue("renren_redirect_URI"));//这个redirect_uri要和之前传给authorization endpoint的值一样
        parameters.put("grant_type", "authorization_code");
        parameters.put("code", code);
        String tokenResult = HttpURLUtils.doPost(rrOAuthTokenEndpoint, parameters);
        JSONObject tokenJson = (JSONObject) JSONValue.parse(tokenResult);
        return tokenJson;
	}
	
	public JSONArray showUser(String accessToken){
		RenrenApiClient apiClient = RenrenApiClient.getInstance();
		int rrUid = apiClient.getUserService().getLoggedInUser(new AccessToken(accessToken));
		JSONArray userInfo = apiClient.getUserService().getInfo(String.valueOf(rrUid),
                "name,headurl,email,sex,birthday", new AccessToken(accessToken)); 
		return userInfo;
	}
	
	public String updateFeed(String accessToken){
		String url = "https://api.renren.com/restserver.do";
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token",accessToken);
        params.put("method", "feed.publishFeed");
        params.put("name", "新鲜事标题4");
        params.put("description", "新鲜事主体内容");
        params.put("url", "http://www.xn--4qrz40kyoi.com/");
        params.put("image", "http://www.订餐吧.com/images/logo.png");
        params.put("caption", "新鲜事副标题 ");
        params.put("action_name", "新鲜事动作模块文案");
        params.put("action_link", "http://www.xn--4qrz40kyoi.com/product.html?id=111");
        params.put("message", "用户输入的自定义内容");
        params.put("page_id", "601601395");
        
		return HttpURLUtils.doPost(url, params);
	}
	
	/**
	 * 管理员更新状态
	 * @param accessToken
	 * @return
	 * 2013-5-12 下午6:53:04 2013
	 */
	public String updateStatus(String accessToken){
		String url = "https://api.renren.com/restserver.do";
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token",accessToken);
        params.put("method", "status.set");
        params.put("status", "status");
        params.put("page_id", "601601395");
        
		return HttpURLUtils.doPost(url, params);
	}
	
	/**
	 * 管理员每餐提醒
	 * @param accessToken
	 * @return
	 * @throws UnsupportedEncodingException
	 * 2013-5-12 下午6:53:27 2013
	 */
	public String  uploadImg(String accessToken){
        TreeMap<String, String> params = new TreeMap<String, String>();
        Goods goods = new GoodsService().getGoodsByGoodsId(RandomInt.getRandomInt(50, 50));
        params.put("access_token", accessToken);
        params.put("method", "photos.upload");
        params.put("page_id", "601601395");
        params.put("aid", "842019033");
        params.put("caption","订餐吧提醒您:吃饭时间到了,妈妈说订餐才是王道，今日我们为您推荐["+goods.getGoodsName()+"],客官点这里:http://www.xn--4qrz40kyoi.com/product.html?id="+goods.getGoodsId()+" 不仅吃省点,还要吃好点,就来订餐吧.");

        String url = "http://www.xn--4qrz40kyoi.com/"+goods.getBigImage();
        String contentType = parseContentType(url);
        String resposeContent = HttpURLUtils.doUploadFile("https://api.renren.com/restserver.do", params,
                "upload", url, contentType, HttpURLUtils.getBytes(url));
        
        return resposeContent;
	}
	
    private String parseContentType(String fileName) {
        String contentType = "image/jpg";
        fileName = fileName.toLowerCase();
        if (fileName.endsWith(".jpg"))
            contentType = CONTENT_TYPE_JPG;
        else if (fileName.endsWith(".png"))
            contentType = CONTENT_TYPE_PNG;
        else if (fileName.endsWith(".jpeg"))
            contentType = CONTENT_TYPE_JPEG;
        else if (fileName.endsWith(".gif"))
            contentType = CONTENT_TYPE_GIF;
        else if (fileName.endsWith(".bmp"))
            contentType = CONTENT_TYPE_BMP;
        else
            throw new RuntimeException("不支持的文件类型'" + fileName + "'(或没有文件扩展名)");
        return contentType;
    }	

    public static void main(String[] args) throws UnsupportedEncodingException{
		RenrenService RenrenService = new RenrenService();
//		System.out.println(RenrenService.getTokenAndUidByCode("218349|6.d9c9dc1b782f191ab74109812d336c79.2592000.1370365200-359591740"));
		System.out.println(RenrenService.uploadImg("218349|6.7aea84d2272e41aafd2b7a24d6bfac93.2592000.1370937600-359591740"));
//		System.out.println(RenrenService.updateStatus("218349|6.7aea84d2272e41aafd2b7a24d6bfac93.2592000.1370937600-359591740"));
//		System.out.println(RenrenService.uploadImage("218349|6.7aea84d2272e41aafd2b7a24d6bfac93.2592000.1370937600-359591740"));
	}
}
