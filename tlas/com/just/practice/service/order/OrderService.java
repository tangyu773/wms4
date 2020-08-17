package com.just.practice.service.order;

import java.util.List;
import java.util.Map;

public interface OrderService {
	/**
	 * 用户登录
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, Object> loadSysUser(String account)
			throws Exception;
	public List<Map<String, Object>> findusrForTree() 
			throws Exception ;
	public Map<String, Object> getkur() 
			throws Exception ;
	public boolean droptreepanel(String id,String pid) throws Exception;
	public abstract  boolean  updatepwd(String account ,String pwd) throws Exception ;
	public List<Map<String, Object>> sys_role_query() 
			throws Exception ;
	public List<Map<String, Object>> getMtype() 
			throws Exception ;
	public List<Map<String, Object>> getSCgroup() 
			throws Exception ;
	public List<Map<String, Object>> getqushi() 
			throws Exception ;
	public List<Map<String, Object>> getorderlist() throws Exception;
}

