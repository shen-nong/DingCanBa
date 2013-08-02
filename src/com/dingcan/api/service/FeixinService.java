package com.dingcan.api.service;

import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.dingcan.util.ConfigReader;

import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;

@Service
public class FeixinService extends ApiService {

	/**
	 * Green Lei
	 * 2012-12-7 œ¬ŒÁ1:22:21 2012
	 */
	private static final long serialVersionUID = 1L;

	public boolean sendMessage(String to, String message) throws WeiboException{
		message = URLEncoder.encode(message);   
		String result = client.post("http://quanapi.sinaapp.com/fetion.php?u="+ConfigReader.getValue("phone")+
				"&p="+ConfigReader.getValue("password")+
				"&to="+to+
				"&m="+message,
				new PostParameter[] { new PostParameter("","") }).asString();
		return result.contains("0");
	}
	
	public static void main(String[] args) throws WeiboException{
		FeixinService feixinService = new FeixinService();
		System.out.println(feixinService.sendMessage("13898176737", "≤‚ ‘∂Ã–≈"));
	}
}
