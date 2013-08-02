package com.dingcan.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dingcan.cons.Cons;
import com.dingcan.shop.model.Shop;
import com.dingcan.user.model.User;

public class FileUploadUtil {
	public static void inputstreamtofile(InputStream in,String filePath,String fileName) throws IOException{
		FileOutputStream fs = new FileOutputStream(filePath+fileName);  
        byte[] buffer = new byte[1024 * 1024];  
        int bytesum = 0;  
        int byteread = 0;  
        while ((byteread = in.read(buffer)) != -1) {  
            bytesum += byteread;  
            fs.write(buffer, 0, byteread);  
            fs.flush();  
        }  
        
        fs.close();  
        in.close();  
	}
	
	/**
	 * 生成目录
	 * @param request
	 * @return
	 */
	public static String generateImageURL(HttpServletRequest request){
		String savePath = null;
		savePath = request.getRealPath("upload")+"/";
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			return "上传目录不存在.";
		}
		
		//检查目录写权限
		if(!uploadDir.canWrite()){
			return "上传目录没有写权限.";
		}
		
		//创建文件夹
		savePath += ((User)request.getSession().getAttribute(Cons.USER_CONTEXT)).getUserId() + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return savePath;
	}
	
	/**
	 * 用户头像目录
	 * @param request
	 * @return
	 * Green Lei
	 * 2012-12-7 下午1:48:10 2012
	 */
	public static String genetateUserimgPath(HttpServletRequest request){
		String savePath = null;
		savePath = request.getRealPath("userimg")+"/";
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			return "上传目录不存在.";
		}
		
		//检查目录写权限
		if(!uploadDir.canWrite()){
			return "上传目录没有写权限.";
		}
		
		//创建文件夹
		savePath +=  "user/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		//创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return savePath;
	}
	
	/**
	 * 商家logo
	 * @param request
	 * @return
	 * 2013-5-1 上午11:38:12 2013
	 */
	public static String genetateShopimgPath(HttpServletRequest request){
		String savePath = null;
		savePath = request.getRealPath("shoplogo")+"/";
		
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			return "上传目录不存在.";
		}
		
		//检查目录写权限
		if(!uploadDir.canWrite()){
			return "上传目录没有写权限.";
		}
		
		//创建文件夹
		savePath +=  "shop/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		//创建文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return savePath;
	}
}
