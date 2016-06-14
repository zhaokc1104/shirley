package cn.com.mv.tds.utils;

public class Pageable {
	
	private Integer pageNum = 1;
	
	private Integer start = 0;
	
	private Integer length;	
	
	public Pageable(){}
	
	public Pageable(Integer start, Integer length) {
		super();
		this.start = start;
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
