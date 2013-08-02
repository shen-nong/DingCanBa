package com.dingcan.util;

import java.util.List;

/**
 * 封装分页信息和操作
 * @author Degree
 * 2012-8-12  下午10:29:38
 * @param <E>
 */
public class PageModel<E> {

	//结果集
	private List<E> list;
	
	//查询记录数
	private int totalRecords;
	
	//每页多少条数据
	private int pageSize;
	
	//第几页
	private int pageNo;
	
	//共几页
	private int totalPage;
	
	//首页
	private int topPageNo;
	
	//下一页
	private int nextPageNo;
	
	//上一页
	private int previousPageNo;
	
	//末页
	private int lastPageNo;
	
	//查询的关键字
	private String queryString;
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	
	/**
	 * 取得首页
	 * @return
	 */
	public int getTopPageNo() {
		return 1;
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo - 1;
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int getNextPageNo() {
		if (pageNo >= getBottomPageNo()) {
			return getBottomPageNo();
		}
		return pageNo + 1;	
	}
	
	/**
	 * 取得尾页
	 * @return
	 */
	public int getBottomPageNo() {
		return getTotalPages();
	}
	
	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	//在设置总记录时,把分页同时设置了
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		
		topPageNo = getTopPageNo();
		nextPageNo = getNextPageNo();
		previousPageNo = getPreviousPageNo();
		lastPageNo = getBottomPageNo();
		totalPage = getTotalPages();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public void setTopPageNo(int topPageNo) {
		this.topPageNo = topPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public void setPreviousPageNo(int previousPageNo) {
		this.previousPageNo = previousPageNo;
	}
	
}