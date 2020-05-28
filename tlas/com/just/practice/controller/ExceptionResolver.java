package com.just.practice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.just.practice.constants.HttpStatusCode;
import com.just.practice.exception.DataAaccessException;
import com.just.practice.exception.SecurityException;
import com.just.practice.exception.ServiceException;
import com.just.practice.utils.MessageSourceHolder;

/**
 * 自定义异常处理(json)
 * 
 * @author QQing
 * 
 */
@Controller
public class ExceptionResolver extends ControllerSupport implements
		HandlerExceptionResolver {

	/**
	 * json 异常处理
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error(ex.getMessage(), ex);
		String message = MessageSourceHolder
				.getMessage("exception.default.msg");
		int httpCode = HttpStatusCode._500;
		if (ex instanceof ServiceException
				|| ex instanceof DataAaccessException) {
			// httpCode = HttpStatusCode._400;
			message = ex.getMessage();
		} else if (ex instanceof SecurityException) {
			message = ex.getMessage();
			httpCode = HttpStatusCode._403;
		}
		response.setStatus(httpCode);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", false);
		model.put("info", message);
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setAttributesMap(model);
		ModelAndView mv = new ModelAndView(view);
		return mv;
	}

}
