package com.just.practice.dao.sys;

import java.util.List;
import java.util.Map;


public interface SysModuleDao {

	/**
	 * 根据访问路径获取模块信息
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, Object> loadSysModuleByPath(String path,
			String param);
	
	/**
	 * 根据id获取所有模块
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map<String, Object>> findSysModule(int pid)
			throws Exception;
	public abstract List<Map<String, Object>> findallModule(int pid)
			throws Exception ;
	public abstract List<Map<String, Object>> findRolelModule(int roleid)
			throws Exception ;

}
