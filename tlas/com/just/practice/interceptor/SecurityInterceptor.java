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
import com.just.practice.common.SecurityContext;
import com.just.practice.constants.SessionCode;
import com.just.practice.exception.SecurityException;
import com.just.practice.utils.MessageSourceHolder;
import com.just.practice.utils.ModuleHolder;

/**
 * 
 * @Description TODO 认证拦截器
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-10 下午2:29:42
 * 
 */
public class SecurityInterceptor extends BaseObject implements
		HandlerInterceptor {
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

		int status = (Integer) user.get("status");
		int roleid = (Integer) user.get("roleid");
		if (status != 1 || roleid == 0) {
			throw new SecurityException(
					MessageSourceHolder
							.getMessage("exception.security.userCannot"));
		}

		SecurityContext.setRoleId(roleid);
		String param = request.getParameter("_param");
		int moduleid = ModuleHolder.getModuleIdByPath(path, param);
		SecurityContext.setModuleid(moduleid);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object hanler, Exception error)
			throws Exception {
		SecurityContext.removeLocalRoleid();
		SecurityContext.removeLocalModuleid();
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
		path = StringUtils.substring(path, 0,
				StringUtils.lastIndexOf(path, "/"));
		logger.info("====请求url地址:" + path);
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