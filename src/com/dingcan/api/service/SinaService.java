package com.dingcan.api.service;

import org.springframework.stereotype.Service;

import com.dingcan.util.ConfigReader;

import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

@Service
public class SinaService extends ApiService{
	/**
	 * Green Lei
	 * 2012-12-7 ÏÂÎç1:22:03 2012
	 */
	private static final long serialVersionUID = 1L;

	public JSONObject showUser(String uid, String token) throws WeiboException {
		return client.get(
				ConfigReader.getValue("sina_baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("uid", uid),new PostParameter("access_token", token) })
				.asJSONObject();
	}
	
	public JSONObject getTokenAndUidByCode(String code) throws WeiboException{
		return client.post(
				ConfigReader.getValue("sina_accessTokenURL"),
				new PostParameter[] {
						new PostParameter("client_id", ConfigReader
								.getValue("sina_client_ID")),
						new PostParameter("client_secret", ConfigReader
								.getValue("sina_client_SERCRET")),
						new PostParameter("grant_type", "authorization_code"),
						new PostParameter("code", code),
						new PostParameter("redirect_uri", ConfigReader
								.getValue("sina_redirect_URI_mobile")) }, false).asJSONObject();
	}
	
	public static void main(String[] args) throws WeiboException{
		SinaService sinaService = new SinaService();
//		System.out.println(sinaService.showUserById("2782384485", "2.00H_bSCDYYrNvD78a6117ad10dr9k1"));
//		System.out.println(sinaService.getTokenAndUidByCode("4b39ad2e73d8e78b45fcb13dac178b06"));
	}
}
