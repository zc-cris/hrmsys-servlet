package cn.zc.hrmsys.pojo;

import java.util.ArrayList;
import java.util.List;

public  class PageBean<T> {
	
	private Integer allPageNum;
	private Integer currentPageNum;
	private Integer pageSize = 2;
	private long allCount;
	private List<T> lists = new ArrayList<>();
	
	public PageBean() {
		super();
		
	}
	public PageBean(Integer allPageNum, Integer currentPageNum, Integer pageSize, long allCount, List<T> lists) {
		super();
		this.allPageNum = allPageNum;
		this.currentPageNum = currentPageNum;
		this.pageSize = pageSize;
		this.allCount = allCount;
		this.lists = lists;
	}
	public Integer getAllPageNum() {
		return (int) (this.allCount % this.pageSize == 0?this.allCount/this.pageSize:this.allCount/this.pageSize+1);
	}
	public void setAllPageNum(Integer allPageNum) {
		this.allPageNum = allPageNum;
	}
	public Integer getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getAllCount() {
		return allCount;
	}
	public void setAllCount(long allCount) {
		this.allCount = allCount;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	@Override
	public String toString() {
		return "PageBean [allPageNum=" + allPageNum + ", currentPageNum=" + currentPageNum + ", pageSize=" + pageSize
				+ ", allCount=" + allCount + ", lists=" + lists + "]";
	}

}
