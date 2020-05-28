package com.just.common.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 图片工具类
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年10月22日 下午5:49:15
 * @update_describle 
 * <pre>无</pre>
 */
public class ImageUtil {
	public static String getImagePath(String folderName,String fileName){
		@SuppressWarnings("deprecation")
		String path = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRealPath("") + File.separator;
		if(folderName == null || "".equals(folderName.trim())){
			return path + fileName;
		}
		return path + folderName + File.separator + fileName;
	}
	
//	public static String getImagePath(String fileName){
//		String path = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getRealPath("") + File.separator;
//		return path + fileName;
//	}
	
	public static String getVarImagePath(String fileName){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String format = request.getParameter("format");
		if("html".equals(format)){
			return "../"+fileName;
		}
		
		
		String path = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext().getRealPath("") + File.separator;
		File file = new File(path, fileName);
//		if(folderName == null || "".equals(folderName.trim())){
//			return path + fileName;
//		}
		return file.getAbsolutePath();
	}
}
