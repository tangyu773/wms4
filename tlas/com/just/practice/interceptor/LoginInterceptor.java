package com.just.practice.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.just.practice.common.BaseObject;
import com.just.practice.constants.SessionCode;
import com.just.practice.exception.SecurityException;
import com.just.practice.utils.MessageSourceHolder;

/**
 * 登录认证
 * 
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年8月27日 下午12:02:49
 * @update_describle <pre>
 * 无
 * </pre>
 */
public class LoginInterceptor extends BaseObject implements HandlerInterceptor {
	private List<String> urls = new ArrayList<String>();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String path = getServletPath(request);

		logger.info(String.format("来自【%s:%d】请求url:%s", request.getRemoteHost(),
				request.getRemotePort(), request.getServletPath()));
		if (StringUtils.isBlank(path)) {
			return true;
		}

		if (urls.contains(path)) {
			return true;
		}

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) session
				.getAttribute(SessionCode.LOGIN_USER_ROLE);
		if (user == null) {
			throw new SecurityException(
					MessageSourceHolder
							.getMessage("exception.security.notLogin"));
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object hanler, Exception error)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	/**
	 * 分解请求URL中的是哪一个模块的action模块以获取moduleid
	 * 
	 * @param request
	 * @return
	 */
	private String getServletPath(HttpServletRequest request) {
		String path = request.getServletPath();
		// log.info("====请求全路径:" + path);
		path = StringUtils.substring(path, 0,
				StringUtils.lastIndexOf(path, "/"));
		logger.info("请求url地址:" + path);
		path = StringUtils.trim(path);
		if (!StringUtils.equals("login.action", path)) {
			return path;
		}
		return "";
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
