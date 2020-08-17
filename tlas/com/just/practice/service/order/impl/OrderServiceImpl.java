
package com.just.practice.service.order.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.practice.common.BaseObject;
import com.just.practice.dao.order.OrderDao;
import com.just.practice.dao.sys.SysUserDao;
import com.just.practice.service.order.OrderService;



@Service("OrderService")

public class OrderServiceImpl extends BaseObject implements OrderService {

	@Autowired
	private OrderDao orderDao;
	List<Map<String, Object>> ms = new ArrayList<Map<String, Object>>();  
	@Override
	public List<Map<String, Object>> findusrForTree() throws Exception {		
		ms = orderDao.findusertree();
		return getTree("0");
	}
	/**
	 *生成树
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getTree(String pid) throws Exception {
		List<Map<String, Object>> mt = findKgBymap(pid);
		for(Map<String, Object> m: mt) {
			String p = (String)m.get("id");
			if(isNotLeaf(p)) {
				m.put("expanded", true);
				m.put("leaf", false);
				List<Map<String,Object>> children = getTree(p);
				m.put("children", children);
			}else {
				m.put("leaf", true);
			}
		}
		return mt;
 	}
	
	/**
	 * 是否为叶子节点
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	private boolean isNotLeaf(String pid)throws Exception {
		List<Map<String, Object>> mi = findKgBymap(pid);
		return (mi != null && mi.size() > 0);
	}
	
	public List<Map<String, Object>> findKgBymap(String pid) throws Exception {
		List<Map<String, Object>> mm =  new ArrayList<Map<String, Object>>();  
		for(Map<String, Object> m: ms) {
			String p = (String) m.get("pid");
		
			if(p.equalsIgnoreCase(pid) ){
				mm.add(m);
			}
		}
		return mm;
	}
	/**
	 * 获取一条用户信息
	 */
	@Override
	public Map<String, Object> loadSysUser(String account) throws Exception {
		logger.info("====获取用户一条信息...");
		return orderDao.loadSysUser(account);
	}
	@Override
	public boolean  updatepwd(String account ,String pwd) throws Exception {
	
		return orderDao.updatepwd(account, pwd);
	}
	@Override
	public boolean droptreepanel(String id,String pid) throws Exception{	
		return orderDao.droptreepanel(id,pid);
	}
	@Override
	public Map<String, Object> getkur() throws Exception {
		// TODO Auto-generated method stub
		return orderDao.getkur();
	}
	@Override
	public List<Map<String, Object>> sys_role_query() throws Exception {
		// TODO Auto-generated method stub
		return orderDao.sys_role_query();
	
	}
	@Override
	public List<Map<String, Object>> getMtype() throws Exception {
		return orderDao.getMtype();
	}
	@Override
	public List<Map<String, Object>> getSCgroup() throws Exception {
		return orderDao.getSCgroup();
	}
	@Override
	public List<Map<String, Object>> getqushi() throws Exception {
		return orderDao.getqushi();
	}
	@Override
	public List<Map<String, Object>> getorderlist() throws Exception {
		return orderDao.getorderlist();
	}

}
