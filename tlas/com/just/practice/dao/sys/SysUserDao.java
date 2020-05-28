package com.just.practice.dao.sys;

import java.util.List;
import java.util.Map;

import com.just.practice.po.order_param;

public interface SysUserDao {
	/**
	 * 根据用户名查询一条数据
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, Object> loadSysUser(String account)
			throws Exception;
	public List<Map<String, Object>> findusertree() 
			throws Exception ;
	public boolean droptreepanel(String id,String pid) ;
	public abstract boolean updatepwd(String id,String pwd) ;
	public abstract Object orderquery(order_param odparam) ;
		
	
}
