package com.primeton.order.vo;

import java.util.List;

public class PageBean<T> {

	private Integer currentPage;
	private Integer totalPage;
	private Integer size = 4;
	private Integer count = 0;
	private List<T> pageInfo;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<T> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(List<T> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
	
	
	
	
	
}
