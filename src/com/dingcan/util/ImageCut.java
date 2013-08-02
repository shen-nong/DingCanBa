package com.dingcan.util;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import javax.imageio.ImageIO;

public class ImageCut {
	
	/** 
     * 图像切割（改）     * 
     * @param srcImageFile            源图像地址
     * @param dirImageFile            新图像地址
     * @param x                       目标切片起点x坐标
     * @param y                      目标切片起点y坐标
     * @param destWidth              目标切片宽度
     * @param destHeight             目标切片高度
     */
    public static void abscut(String srcImageFile,String dirImageFile, int x, int y, int destWidth,
            int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度          
            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                // 改进的想法:是否可用多线程加快切割速度
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(dirImageFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
	/**
	 * 缩放图像
	 * 
	 * @param srcImageFile       源图像文件地址
	 * @param result             缩放后的图像地址
	 * @param scale              缩放比例
	 * @param flag               缩放选择:true 放大; false 缩小;
	 */
	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {
				// 放大
				width = width * scale;
				height = height * scale;
			} else {
				// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重新生成按指定宽度和高度的图像
	 * @param srcImageFile       源图像文件地址
	 * @param result             新的图像地址
	 * @param _width             设置新的图像宽度
	 * @param _height            设置新的图像高度
	 * @throws IOException 
	 */
	public static void scale(String srcImageFile, String result, int _width,int _height) throws IOException {	
		BufferedImage bi = javax.imageio.ImageIO.read(new File(srcImageFile));
		BufferedImage im = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);  
		im.getGraphics().drawImage(bi.getScaledInstance(_width, _height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);  
		try {  
        	File saveFile=new File(result);  
        	ImageIO.write(im,"JPEG",saveFile); 
        } catch (Exception e) {  
        }  
	}
	
	public static void scale(String srcImageFile, String result, int _width,int _height,int x,int y) {
		try {
			
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			
			if (width > _width) {
				 width = _width;
			}
			if (height > _height) {
				height = _height;
			}			
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, x, y, null); // 绘制缩小后的图
			g.dispose();			
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
	 */
	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param source
	 * @param result
	 */
	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 返回文件的文件后缀名 
     * 
     * @param fileName 
     * @return 
     */  
    public static String getExtension(String fileName) {  
        try {  
            return fileName.split("\\.")[fileName.split("\\.").length - 1];  
        } catch (Exception e) {  
            return null;  
        }  
    } 

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//晕。。。搞成多个了...
		//cut("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg", 200, 150);
		//ok
		//gray("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg");
		//convert("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.gif");
		//scale("c:/images/5105049910001020110718648723.jpg", "c:/images/t/5105049910001020110718648725.jpg",154,166,157,208);
		//scale("c:/images/rose1.jpg", "c:/images/t/rose1.jpg",154,166,157,208);
//		scale("c:/images/rose1.jpg", "c:/images/t/rose2.jpg",154,166,10,10);
		
		
//		File[] fileList = new File("D:/workspace_dingcanba20130418/DingCanBa5.3/WebContent/default/avatar").listFiles();  
//		System.out.println(fileList.length);
//	    Map<String,Boolean> map = new HashMap<String, Boolean>();  
//        for(String s : new String[]{"jpg","png"}) {  
//            map.put(s,true);   
//        }  
//        for (File f : fileList) {  
//            if(f.length() == 0)  
//                continue;  
//            if(map.get(getExtension(f.getName())) == null)  
//                continue;  
//	    	System.out.println("F:/image_user/default/avatar/"+f.getName());
//	    	System.out.println("F:/image_user/default/avatar/"+f.getName().replace("w200", "w50"));
//	    	ImageCut.scale("F:/image_user/userimg/user/20130612/"+f.getName(), "F:/image_user/userimg/user/20130612/w50/"+f.getName().replace("200w", "50w"),50,50);
//        }
		File[] fileList = new File("C:/Documents and Settings/Administrator/桌面/user/122/defaultavatar/").listFiles();  
		for (File f : fileList) {  
			File oldFile = f;
			  if(!oldFile.exists())
			  {
			   oldFile.createNewFile();
			  }
			  System.out.println("修改前文件名称是："+oldFile.getName());
			  String rootPath = oldFile.getParent();
			  System.out.println("根路径是："+rootPath);
			  File newFile = new File(rootPath +"/"+ oldFile.getName().replace("_", "w_"));
			  System.out.println("修改后文件名称是："+newFile.getName());
			  if (oldFile.renameTo(newFile)) 
			  {
			   System.out.println("修改成功!");
			  } 
			  else 
			  {
			   System.out.println("修改失败");
			  }
		}
		
		
	}
}