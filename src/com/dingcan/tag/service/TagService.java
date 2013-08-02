package com.dingcan.tag.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dingcan.tag.dao.TagDao;
import com.dingcan.tag.model.Tag;
import com.dingcan.util.ConfigReader;
import com.dingcan.util.CustomDate;

@Service
public class TagService {
	ApplicationContext context = 
	 		new ClassPathXmlApplicationContext("Spring-Module.xml");
	TagDao tagDao = (TagDao) context.getBean("tagDao");
	
	/**
	 * 新增一个标签,如果没有新增,返回false,如果新增返回true
	 * @param tagName
	 * @return
	 * Green Lei
	 * 2012-12-5 下午6:06:08 2012
	 */
	public boolean addTag(String tagName){
		if(tagDao.getTagByKey(tagName) != null){
			return false;
		}else{
			Tag tag = new Tag();
			tag.setCreateDate(CustomDate.getStringDate());
			tag.setTagType(0);
			tag.setTagName(tagName);
			tagDao.addTag(tag);
			return true;
		}
	}
	
	public List<Tag> getNewTagList(){
		int count = Integer.parseInt(ConfigReader.getValue("tag_new_size"));
		return tagDao.getTagByCount(count);
	}
}
