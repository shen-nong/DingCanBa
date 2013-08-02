package com.dingcan.util;

  
import javax.imageio.ImageIO;  

import java.awt.image.BufferedImage;  
import java.util.HashMap;  
import java.util.List;  
import java.util.ArrayList;  
import java.io.File;  
import java.io.IOException;  
import java.util.Map;  
  
/**
 * 商家上传图片后,调整图片的大小,
 * 否则图片在首页太大,浏览时会卡
 * @author Degree
 * 2012-6-13  下午3:41:18
 */
public class ResizeImage {  
  
    /** 
     * @param im            原始图像 
     * @param resizeTimes   需要缩小的倍数，缩小2倍为原来的1/2 ，这个数值越大，返回的图片越小 
     * @return              返回处理后的图像 
     */  
    public BufferedImage resizeImage(BufferedImage im, float resizeTimes) {  
        /*原始图像的宽度和高度*/  
        int width = im.getWidth();  
        int height = im.getHeight();  
  
        /*调整后的图片的宽度和高度*/  
        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / resizeTimes);  
        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / resizeTimes);  
        
        /*新生成结果图片*/  
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);  
  
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);  
        return result;  
    }  
  
    /** 
     * @param im            原始图像 
     * @param resizeTimes   倍数,比如0.5就是缩小一半,0.98等等double类型 
     * @return              返回处理后的图像 
     */  
    public BufferedImage zoomImage(BufferedImage im, float resizeTimes) {  
        /*原始图像的宽度和高度*/  
        int width = im.getWidth();  
        int height = im.getHeight();  
  
        /*调整后的图片的宽度和高度*/  
        int toWidth = (int) (Float.parseFloat(String.valueOf(width)) * resizeTimes);  
        int toHeight = (int) (Float.parseFloat(String.valueOf(height)) * resizeTimes);  
        
        /*新生成结果图片*/  
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);  
  
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);  
        return result;  
    }  
    
    /** 
     * 把图片的变为277*277
     * @param im            原始图像 
     * @return              返回处理后的图像 
     */  
    public BufferedImage changeImage(BufferedImage im, int width, int height) {  
        /*调整后的图片的宽度和高度*/  
        int toWidth = width;  
        int toHeight = height;  
        
        /*新生成结果图片*/  
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);  
  
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);  
        return result;  
    }
  
    /** 
     * 
     * @param path  要转化的图像的文件夹,就是存放图像的文件夹路径 
     * @param type  图片的后缀名组成的数组 
     * @return 
     */  
    public List<BufferedImage> getImageList(String path, String[] type) throws IOException{  
        Map<String,Boolean> map = new HashMap<String, Boolean>();  
        for(String s : type) {  
            map.put(s,true);   
        }  
        List<BufferedImage> result = new ArrayList<BufferedImage>();  
        File[] fileList = new File(path).listFiles();  
        for (File f : fileList) {  
            if(f.length() == 0)  
                continue;  
            if(map.get(getExtension(f.getName())) == null)  
                continue;  
            result.add(javax.imageio.ImageIO.read(f));  
        }  
        return result;  
    }  
    
    /** 
     * 
     * @param path  要转化的图像的地址 
     * @return 
     */  
    public BufferedImage getImage(String path) throws IOException{  

        File f = new File(path);   
        return javax.imageio.ImageIO.read(f);  
    }
  
    /** 
     * 把图片写到磁盘上 
     * 
     * @param im 
     * @param path     eg: C://home// 图片写入的文件夹地址 
     * @param fileName DCM1987.jpg  写入图片的名字 
     * @return 
     */  
    public boolean writeToDisk(BufferedImage im, String path, String fileName) {  
        File f = new File(path + fileName);  
        String fileType = getExtension(fileName);  
        if (fileType == null)  
            return false;  
        try {  
            ImageIO.write(im, fileType, f);  
            im.flush();  
            return true;  
        } catch (IOException e) {  
            return false;  
        }  
    }  
  
  
    public boolean writeHighQuality(BufferedImage im, String fileFullPath) {  
        try {  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
    
    /**
     * 根据文件名输出
     * @param im
     * @param fileFullPath
     * @return
     * 2012-6-13  下午5:05:35 
     */
    public boolean writeHighQualityByName(BufferedImage im, String fileFullPath, String fileName) {  
        try {  
        	File saveFile=new File(fileFullPath+fileName);  
        	ImageIO.write(im,"JPEG",saveFile); 
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
  
    /** 
     * 返回文件的文件后缀名 
     * 
     * @param fileName 
     * @return 
     */  
    public String getExtension(String fileName) {  
        try {  
            return fileName.split("\\.")[fileName.split("\\.").length - 1];  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
    public static void main(String[] args) throws Exception{  
  
    	  /**
    	   * 遍历一个文件夹
    	   */
        String inputFoler = "F:/image_user/default/avatar" ; /*这儿填写你存放要缩小图片的文件夹全地址*/  
        String outputFolder = "F:/image_user/default/avatar/w50/";  /*这儿填写你转化后的图片存放的文件夹*/  
        float times = 0.5f; /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/  
  
  
        ResizeImage r = new ResizeImage();  
        List<BufferedImage> imageList = r.getImageList(inputFoler,new String[]{"jpg"});  
        //增强for循环
        int n = 0;
        for(BufferedImage i : imageList) {  
        	n++;
            r.writeHighQualityByName(r.changeImage(i, 50, 50),outputFolder, "w50_"+n+".jpg");  
        }  
    	
    	/**
    	 * 给定一个图片
    	 */
//    	ResizeImage r = new ResizeImage(); 
//    	String imageURL = "C:/Documents and Settings/Administrator/桌面/12/17_1203909601296.jpg";
//    	String outputFolder = "C:/Documents and Settings/Administrator/桌面/13/";
//    	r.writeHighQualityByName(r.changeImage(r.getImage(imageURL), 200, 250),outputFolder, "hello.jpg");
    }  
}  
