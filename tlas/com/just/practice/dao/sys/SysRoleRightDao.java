package com.just.practice.dao.sys;

import java.util.List;
import java.util.Map;

/***
 * 
 * @author zouhaoadmin 模块管理
 */
public interface SysRoleRightDao {
	/**
	 * 获取模块菜单
	 * 
	 * @param roleid
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map<String, Object>> searchModule(int roleid, int pid)
			throws Exception;
	public abstract List<Map<String, Object>> searchmapModule(int roleid) throws Exception;
}
