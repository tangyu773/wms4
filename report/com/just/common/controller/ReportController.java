package com.just.common.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import com.just.common.Constants;
import com.just.common.service.ReportServiceI;
import com.just.common.util.ObjectUtil;
import com.just.common.view.AutoColumnsExcelView;
import com.just.practice.controller.ControllerSupport;

/**
 * 
 * @author CWJ
 * 
 */
@Controller
@RequestMapping("/report")
public class ReportController extends ControllerSupport {
	@RequestMapping("/report")
	public ModelAndView print(@RequestParam String format,
			@RequestParam String templateBeanId, @RequestParam(defaultValue="false", required=false) boolean isAutoColumns, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();

		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String[] values = request.getParameterValues(name);
			
			if (values.length == 1) {
				params.put(name, decodeURLString(values[0]));
				continue;
			}
			params.put(name, decodeURLStringArray(values));
		}
		// add by cwj 2014骞�2鏈�2鏃�10:44:29 start
		Object extParams = getSession().getAttribute(Constants.WEB_APP_COMMON_SESSION);
		params.put(com.just.common.util.Constants.REPORT_EXT_PARAMS, extParams);
		// add by cwj 2014骞�2鏈�2鏃�10:44:29 end
		/*if("xls".equalsIgnoreCase(format)) {
			String fileName = DateUtils.getYYYMMDDHHMMSSSSS() + ".xls";//璁剧疆涓嬭浇鏃跺鎴风Excel鐨勫悕绉�      
			response.reset();
			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		}*/
		
		
		
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		ReportServiceI reportService = (ReportServiceI) context.getBean(templateBeanId);
		Map<String, Object> datasourceParams = reportService.createDatasource(params);
		reportService.renameFile(params);
		
		
		if("xls".equals(format) && isAutoColumns){
			AutoColumnsExcelView excel = new AutoColumnsExcelView();
			if(reportService.getFileName() != null){
				excel.setFilename(reportService.getFileName());
			}
			return new ModelAndView(excel, datasourceParams);
		}
		
		datasourceParams.put(JasperReportsMultiFormatView.DEFAULT_FORMAT_KEY, format);
		//蹇界暐鍒嗛〉
		//datasourceParams.put(JRParameter.IS_IGNORE_PAGINATION, true);
		
		ModelAndView mav = new ModelAndView(templateBeanId, datasourceParams);
		WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		XmlViewResolver xmlViewResolver = wac.getBean(XmlViewResolver.class);
		LocaleResolver localeResolver = (LocaleResolver) request.getAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE); 
		Locale locale = localeResolver.resolveLocale(request);
		View targetView = new JasperReportsMultiFormatView();
		View view = xmlViewResolver.resolveViewName(templateBeanId, locale);
//		org.apache.commons.beanutils.BeanUtils.copyProperties(view, targetView );
//		PropertyUtils.copyProperties(targetView, view);
		
//		BeanCopier bc = BeanCopier.create(JasperReportsMultiFormatView.class, JasperReportsMultiFormatView.class, false);
//		bc.copy(view, targetView, null);
		if(reportService.getFileName() != null){
			if( targetView instanceof JasperReportsMultiFormatView){
				ObjectUtil.clone(view, targetView, JasperReportsMultiFormatView.class);
				view = targetView;
				JasperReportsMultiFormatView jasperView = (JasperReportsMultiFormatView) targetView;
				Properties mappings = new Properties();
				mappings.setProperty(format, reportService.getFileName());
				jasperView.setContentDispositionMappings(mappings);
			}
		}
		mav.setView(view);
		return mav;
	}
	
	private String decodeURLString(String value){
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String[] decodeURLStringArray(String[] values){
		for(int i = 0; i < values.length; i++){
			try {
				values[i] = URLDecoder.decode(values[i], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return values;
	}

}
