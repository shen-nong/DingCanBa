package com.dingcan.quartz;

import weibo4j.model.WeiboException;

import com.dingcan.api.service.QqService;
import com.dingcan.api.service.RenrenService;
import com.dingcan.util.ConfigReader;

public class RunMeTask {
	public void work() {
		RenrenService renrenService = new RenrenService();
		String[] renren_tokens = ConfigReader.getValue("renren_token").split(",");
		String[] qq_tokens = ConfigReader.getValue("qq_token").split(",");
		String[] qq_openid = ConfigReader.getValue("qq_openid").split(",");
		for(int n=0; n<renren_tokens.length; n++){
			renrenService.uploadImg(renren_tokens[n]);
		}
		QqService qqService = new QqService();
		try {
			for(int n=0; n<qq_tokens.length; n++){
				qqService.addTip(qq_tokens[n], qq_openid[n]);
			}
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}