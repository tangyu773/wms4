package com.just.practice.service.sys;

import java.util.List;
import java.util.Map;

public interface SysModuleService {
	
	/**
	 * 根据访问路径获取一条模块信息
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, Object> loadSysModuleByPath(String path, String param)
			throws Exception;

	/**
	 * 根据访问路径获取模块id
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public abstract int loadSysModuleForIdByPath(String path, String param)
			throws Exception;
	/**
	 * 根据pid获取模块
	 * @param pid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map<String, Object>> findSysModuleForTree(int pid)
			throws Exception;
	public abstract List<Map<String, Object>> findallmenu(int roleid) throws Exception ;
	public abstract List<Map<String, Object>> findrolemenu(int roleid) throws Exception ;
}
