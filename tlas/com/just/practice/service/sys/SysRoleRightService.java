package com.just.practice.service.sys;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description TODO 角色模块授权业务操作
 * @author zouhao
 * @date 2015-10-9 下午2:51:34
 * 
 */
public interface SysRoleRightService {
	/**
	 * 根据角色获取模块列表（生成菜单）
	 * 
	 * @param roleid
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map<String, Object>> getModuleList(int roleid, int pid)
			throws Exception;
}
