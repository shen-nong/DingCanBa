package com.dingcan.api.service;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Service;

import com.dingcan.user.model.User;
import com.dingcan.util.ResizeImage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@Service
public class VpnService{

	public User getUserInfo(String userName, String userPasw, String savePath) throws Exception{
		final WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(true);  

        // Get the first page
        final HtmlPage page1 = webClient.getPage("http://210.47.163.27:9004/");

        // Get the form that we are dealing with and within that form, 
        // find the submit button and the field that we want to change.
        final HtmlForm form = page1.getFormByName("loginForm");

        final HtmlElement button = page1.getElementById("btnSure");
        final HtmlTextInput textField = form.getInputByName("zjh");
        final HtmlPasswordInput passWord = form.getInputByName("mm");
//        final HtmlCheckBoxInput checkBox = form.getInputByName("fruit");
//        // Change the value of the text field
        textField.setValueAttribute(userName);
        passWord.setValueAttribute(userPasw);
//        checkBox.setDefaultValue("juzi");
//        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
        submittingForm(page2);//提交表单,登陆系统,并评教
        User user = new User();
        final HtmlPage pageXueji =  webClient.getPage("http://210.47.163.27:9004/xjInfoAction.do?oper=xjxx");
        String intro = "学院:"+pageXueji.getElementsByTagName("td").get(70).asText()+"</br>"+
        				"班级:"+pageXueji.getElementsByTagName("td").get(78).asText()+"</br>"+
        				"中学:"+pageXueji.getElementsByTagName("td").get(52).asText()+"";
        user.setIntro(intro);
        final HtmlPage packageListPage = webClient.getPage("http://210.47.163.27:9004/userInfo.jsp");
        final HtmlForm infoForm = packageListPage.getFormByName("frm");
        String imgName = packageListPage.getElementsByTagName("b").get(0).asText()+".jpg";
        user.setLocation(infoForm.getInputByName("txdz").getDefaultValue());
        user.setPhone(infoForm.getInputByName("dh").getDefaultValue());
        user.setUserMail(infoForm.getInputByName("email").getDefaultValue());
        user.setUserName(packageListPage.getElementsByTagName("b").get(0).asText());
        user.setPhoto("userimg"+savePath.split("userimg")[1]+imgName);
    	try{
           DataInputStream in = new DataInputStream(webClient.getPage("http://210.47.163.27:9004/xjInfoAction.do?oper=img").getWebResponse().getContentAsStream());
           /*此处也可用BufferedInputStream与BufferedOutputStream*/
           DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath+imgName));
           /*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
           byte[] buffer = new byte[4096];
           int count = 0;
           while ((count = in.read(buffer)) > 0)/*将输入流以字节的形式读取并写入buffer中*/
           {
               out.write(buffer, 0, count);
           }
           
           //放缩图片
           ResizeImage r = new ResizeImage(); 
           BufferedImage bi = javax.imageio.ImageIO.read(new File(savePath+imgName));
           String outputFolder = savePath+"/";
           r.writeHighQualityByName(r.changeImage(bi,200,260),outputFolder, "200w"+imgName);
           
           out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
           in.close();
       }catch (Exception e) {
            throw new Exception("文件保存失败!");
       }finally{
    	   webClient.closeAllWindows();
       }
       return user;
	}
	
	
	/**
	 * 评教
	 * @param user
	 * @param pasw
	 * @throws Exception
	 * Green Lei
	 * 2012-12-12 下午7:54:58 2012
	 */
	public void submittingForm(final HtmlPage page2) throws Exception {
		HtmlPage packageListPage = null;
		try{
			packageListPage = (HtmlPage) page2.getFrameByName("topFrame").getEnclosedPage();
		}catch(Exception e){
			e.printStackTrace();
		}
	  
//      System.out.println("helle123");
      //获得评教的页面
      final HtmlPage page3 = packageListPage.getAnchors().get(4).click();
      
      //输出网页内容
      //System.out.println(page3.getWebResponse().getContentAsString());
      //输出标题
      //System.out.println(page3.getTitleText());
//     final HtmlTable table = page3.getHtmlElementById("user");
//      //标记列表页面中的img标签
//      int n = 0;
//      HtmlPage page4;
//      for (final HtmlTableRow row : table.getRows()) {
//          System.out.println("Found row");           
//          for (final HtmlTableCell cell : row.getCells()) {
//              //System.out.println("   Found cell: " + cell.asText());
//          	//System.out.println("   Found cell: " + row.getCell(3).asText());
//          	if("是".equals(row.getCell(3).asText())){
//          		System.out.println("已评价");
//          		System.out.println(page3.getTitleText());
//          		//page4 = page3.getElementsByTagName("img").get(n).click();
//          		System.out.println(page3.getElementsByTagName("img").get(n));
//          		n++;
//          	}
//          }
//          
//          
//      }
      HtmlSelect htmlSelect = page3.getElementByName("pageSize");

      HtmlOption htmlOption = htmlSelect.getOption(7);
      final HtmlPage pagePiao = (HtmlPage) htmlSelect.setSelectedAttribute(htmlOption, true);;
      
      final List<HtmlImage> imgList = (List<HtmlImage>) pagePiao.getByXPath("//img[@title='评估']");
      HtmlPage htmlPage4;
//      System.out.println("将要进入问卷!");
      for(HtmlImage htmlImage:imgList){
//      	System.out.println("进入for循环!");
      	htmlPage4 = (HtmlPage) htmlImage.click();
//      	System.out.println("123");
      	
	        final List<HtmlRadioButtonInput> radioList = (List<HtmlRadioButtonInput>) htmlPage4.getByXPath("//input[@type='radio']");
	        int radioNum = 5;
	        for(Iterator iteratorRadio = radioList.iterator(); iteratorRadio.hasNext();radioNum++){
	        	HtmlRadioButtonInput radio = (HtmlRadioButtonInput) iteratorRadio.next();
//	        	System.out.println("开始评教");
	        	if(radioNum==5){
		        	radio.setChecked(true);	  
//		        	System.out.println(radioNum);
		        	radioNum = 0;
//		        	System.out.println("评完一题");
//		        	System.out.println(radio.getValueAttribute());
		        	
//		        	return;
	        	}
	        }
	        final HtmlTextArea pingjiaoText = htmlPage4.getElementByName("zgpj");
	        pingjiaoText.setText("教师上课好,讲课认真,备课踏实,我喜欢.");
	       
	        htmlPage4.executeJavaScript("window.document.StDaForm.submit()");    }
	}
}
