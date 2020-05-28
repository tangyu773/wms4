package com.just.practice.utils;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.just.practice.service.sys.SysModuleService;

/**
 * 
 * @Description TODO 获取模块工具类
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-10 下午2:22:46
 * 
 */
@Component
public class ModuleHolder {

	private static SysModuleService sysModuleService;

	/**
	 * 根据路径，参数
	 * @param path
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static int getModuleIdByPath(String path, String param)
			throws Exception {
		return sysModuleService.loadSysModuleForIdByPath(path, param);
	}


	@Resource
	public void setSysModuleService(SysModuleService sysModuleService) {
		ModuleHolder.sysModuleService = sysModuleService;
	}

}
