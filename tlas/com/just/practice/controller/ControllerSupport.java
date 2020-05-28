package com.just.practice.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.alibaba.fastjson.JSON;
import com.just.practice.common.BaseObject;
import com.just.practice.constants.Character;

/**
 * 
 * @Description TODO spring mvc 抽象控制器
 * @author CWJ
 * @category www.just-tech.com.cn
 * @date 2013-12-3 上午10:36:29
 * 
 */
public class ControllerSupport extends BaseObject {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.registerCustomEditor(int.class, new
		// CustomNumberEditor(int.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(
				Long.class, true));
		binder.registerCustomEditor(int.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() {
				return getValue().toString();
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue((text == null || "".equals(text)) ? 0 : NumberUtils
						.createInteger(text));
			}
		});
	}

	/**
	 * 输出json数据 有状态码
	 * 
	 * @param o
	 * @param statusCode
	 */
	public void writeJson(HttpServletResponse response, Object o, int statusCode) {
		if (response == null) {
			response = getResponse();
		}
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

	/**
	 * 输出json 数据 无状态码
	 * 
	 * @param o
	 */
	public void writeJson(Object o) {
		this.writeJson(null, o, 0);
	}

	/**
	 * 输出json 数据 有状态码
	 * 
	 * @param o
	 */
	public void writeJson(Object o, int statusCode) {
		this.writeJson(null, o, statusCode);
	}

	/**
	 * 获取request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ((ServletWebRequest) RequestContextHolder.getRequestAttributes())
				.getResponse();
	}

	/**
	 * 获取session
	 * 
	 * @param request
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取session中的对象
	 * 
	 * @param request
	 * @param code
	 * @param clazz
	 * @return
	 */
	public <T> T getTargetFromSessionByCode(String code, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T terget = (T) getSession().getAttribute(code);
		return terget;
	}

	/**
	 * 添加对象到session
	 * 
	 * @param code
	 * @param target
	 */
	public void setTargetFromSession(String code, Object target) {
		getSession().setAttribute(code, target);
	}

	/**
	 * 删除session的对象
	 * 
	 * @param code
	 */
	public void removeTargetFromSessionByCode(String code) {
		getSession().removeAttribute(code);
	}

}
