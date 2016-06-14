package cn.com.mv.tds.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class PageUtils {
	
	public static String pageable(Pageable page,String sql) {
		
		if (page == null || page.getLength() == null) {
			return sql;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (")
			.append(sql).append(") PAG limit ")
			.append(page.getStart()).append(",").append(page.getLength());
		return sb.toString();
	}
	
	
	public static Pageable getPageable(HttpServletRequest request) {
		String paramPageNum = request.getParameter("pageNum");
		String paramLength = request.getParameter("length");
		if (StringUtils.isEmpty(paramLength) || StringUtils.isEmpty(paramPageNum)) {
			return null;
		}
		Integer pageNum = Integer.valueOf(paramPageNum);
		pageNum = pageNum <= 0 ? 1 : pageNum;
		Pageable page = new Pageable();
		page.setLength(Integer.valueOf(paramLength));
		page.setPageNum(pageNum);
		page.setStart(Integer.valueOf(page.getLength()*(page.getPageNum()-1)));
		return page;
	}
	
	public static void main(String[] args) {
		//System.out.println(Long.valueOf(null));
	}
}
