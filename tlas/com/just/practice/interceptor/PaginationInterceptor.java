package com.just.practice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.just.practice.common.BaseObject;
import com.just.practice.common.PaginationContext;

/**
 * 
 * @Description TODO 分页拦截器
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-4 下午4:33:02
 * 
 */
public class PaginationInterceptor extends BaseObject implements
		HandlerInterceptor {

	private final static String REQUEST_PAGE_START = "start";
	private final static String REQUEST_RAGE_LIMIT = "limit";

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		PaginationContext.removeAll();
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String s = request.getParameter(REQUEST_PAGE_START);
		String l = request.getParameter(REQUEST_RAGE_LIMIT);
		PaginationContext.setPageStart(s != null ? Integer.valueOf(s) : 0);
		PaginationContext.setPageSize(l != null ? Integer.valueOf(l)
				: PaginationContext.DEFAULT_PAGE_SIZE);
		return true;
	}

}
