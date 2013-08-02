package com.dingcan.tag.model;

public class Tag{
	
	private int TagId;
	private int TagType;   //标签类别,默认是0,0:商品搜索产生的标签
	private String TagName;
	private String CreateDate;
	
	public int getTagId() {
		return TagId;
	}
	public void setTagId(int tagId) {
		TagId = tagId;
	}
	public int getTagType() {
		return TagType;
	}
	public void setTagType(int tagType) {
		TagType = tagType;
	}
	public String getTagName() {
		return TagName;
	}
	public void setTagName(String tagName) {
		TagName = tagName;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
}
