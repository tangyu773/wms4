package com.just.practice.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.just.practice.common.BaseObject;
import com.just.practice.common.SecurityContext;
import com.just.practice.constants.Character;
import com.just.practice.constants.HttpStatusCode;
import com.just.practice.constants.SessionCode;
import com.just.practice.utils.MessageSourceHolder;
import com.just.practice.utils.ModuleHolder;

/**
 * 认证拦截器
 * 
 * @author zouhaoadmin
 * @date 2016年3月28日
 * 
 */
public class SecurityFilter extends BaseObject implements Filter {

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) session
				.getAttribute(SessionCode.LOGIN_USER_ROLE);
		if (user == null) {
			forwardLogin(req, res);
			return;
		}

		int userStatus = (Integer) user.get("status");
		int roleid = (Integer) user.get("roleid");
		if (userStatus != 1 || roleid == 0) {
			writeJson(res,
					MessageSourceHolder
							.getMessage("exception.security.userCannot"),
					HttpStatusCode._403);
			return;
		}
		String path = getServletPath(req);
		if (StringUtils.isNotBlank(path)) {
			try {
				int moduleid;
				SecurityContext.setRoleId(roleid);
				moduleid = ModuleHolder.getModuleIdByPath(path, null);
				SecurityContext.setModuleid(moduleid);
			} catch (Exception e) {
				logger.error("====即时认证失败," + e.getMessage(), e);
				writeJson(res, "即时认证失败", HttpStatusCode._403);
				return;
			}
		}
		chain.doFilter(req, res);
		SecurityContext.removeLocalRoleid();
		SecurityContext.removeLocalModuleid();
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 */
	private void forwardLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login.html");
	}

	/**
	 * 分解请求URL中的是哪一个模块的action模块以获取moduleid
	 * 
	 * @param request
	 * @return
	 */
	private String getServletPath(HttpServletRequest request) {
		String path = request.getServletPath();
		if (!StringUtils.endsWith(path, ".action")) {
			return "";
		}
		String pre = StringUtils.substring(path,
				StringUtils.lastIndexOf(path, "/") + 1);
		if ("login.action".equals(pre)) {
			return "";
		}
		path = StringUtils.substring(path, 0,
				StringUtils.lastIndexOf(path, "/"));
		logger.info("====请求url地址:" + path);
		path = StringUtils.trim(path);
		if (!StringUtils.equals("login.action", path)) {
			return path;
		}
		return "";
	}

	/**
	 * 输出json数据 有状态码
	 * 
	 * @param o
	 * @param statusCode
	 */
	public void writeJson(HttpServletResponse response, Object o, int statusCode) {
		PrintWriter writer = null;
		try {
			if (statusCode != 0) {
				response.setStatus(statusCode);
			}
			response.setCharacterEncoding(Character.UTF_8);
			response.setContentType("text/html;charset=utf-8");
			String out = "";
			if (o != null) {
				if (o instanceof String) {
					out = o.toString();
				} else {
					out = JSON.toJSONStringWithDateFormat(o,
							"yyyy-MM-dd HH:mm:ss");
				}
			}
			logger.info("====返回前台json：" + out);
			writer = response.getWriter();
			writer.print(out);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
