package com.just.practice.utils;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description TODO 本地消息工具类
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-11-27 下午5:00:38
 * 
 */
@Component
public class MessageSourceHolder {

	private static MessageSource messageSource;

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @param defaultMsg
	 *            默认消息
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Object[] args,
			String defaultMsg, Locale locale) {
		return messageSource.getMessage(code, args, defaultMsg, locale);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, "", locale);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, "", null);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @return
	 */
	public static String getMessage(String code, Object arg) {
		return messageSource.getMessage(code, new Object[] { arg }, "", null);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Object arg, Locale locale) {
		return messageSource.getMessage(code, new Object[] { arg }, "", locale);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param args
	 *            属性文件中的占位符
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Object arg, String defaultMsg,
			Locale locale) {
		return messageSource.getMessage(code, new Object[] { arg }, defaultMsg,
				locale);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @param locale
	 *            本地化
	 * @return
	 */
	public static String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, null, "", locale);
	}

	/**
	 * 
	 * @param code
	 *            属性文件的key
	 * @return
	 */
	public static String getMessage(String code) {
		return messageSource.getMessage(code, null, "", null);
	}

	public static MessageSource getMessageSource() {
		return messageSource;
	}

	@Resource
	public void setMessageSource(MessageSource messageSource) {
		MessageSourceHolder.messageSource = messageSource;
	}

}
