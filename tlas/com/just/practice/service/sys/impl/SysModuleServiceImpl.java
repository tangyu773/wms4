package com.just.practice.service.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.practice.common.BaseObject;
import com.just.practice.dao.sys.SysModuleDao;
import com.just.practice.exception.ServiceException;
import com.just.practice.service.sys.SysModuleService;
import com.just.practice.utils.MessageSourceHolder;

/**
 * 系统模块
 * @author zouhaoadmin
 * @date 2016年3月28日
 *
 *
 */
@Service("sysModuleService")
public class SysModuleServiceImpl extends BaseObject implements
		SysModuleService {

	@Autowired
	private SysModuleDao sysModuleDao;
	List<Map<String, Object>> ms = new ArrayList<Map<String, Object>>();  
	@Override
	public List<Map<String, Object>> findallmenu(int roleid) throws Exception {		
		ms = sysModuleDao.findallModule(roleid);
		return getTreebymap(0);
	}
	@Override
	public List<Map<String, Object>> findrolemenu(int roleid) throws Exception {		
		ms = sysModuleDao.findRolelModule(roleid);
		return getTreebymap(0);
	}
    
	@Override
	public int loadSysModuleForIdByPath(String path, String param)
			throws Exception {
		logger.info(String.format("====根据访问路径获取模块id,path=%s,param=%s", path,
				param));
		Map<String, Object> m = loadSysModuleByPath(path, param);
		return m == null ? 0 : (Integer) m.get("menuid");
	}
	
	@Override
	public List<Map<String, Object>> findSysModuleForTree(int pid)
			throws Exception {
		return getTree(pid);
	}
	
	@Override
	public Map<String, Object> loadSysModuleByPath(String path, String param)
			throws Exception {
		logger.info(String.format("根据访问路径获取模块信息path=%s,param=%s", path, param));
		if (path == null && "".equals(path)) {
			throw new ServiceException(
					MessageSourceHolder
							.getMessage("exception.service.module.load.pathIsNull"));
		}
		try {
			Map<String, Object> m = sysModuleDao.loadSysModuleByPath(path,
					param);
			if (m != null && !m.isEmpty()) {
				return m;
			}
		} catch (Exception e) {
			logger.error("根据路径获取模块失败" + e.getMessage(), e);
		}
		throw new ServiceException(
				MessageSourceHolder
						.getMessage("exception.service.module.load.isNull"));
	}
	
	private List<Map<String, Object>> getTreebymap(int pid) throws Exception {
		List<Map<String, Object>> mt = findmenuBymap(pid);
		for(Map<String, Object> m: mt) {
			int p = (Integer)m.get("id");
			if(isNotLeafbymap(p)) {
				m.put("expanded", true);
				m.put("leaf", false);
				List<Map<String,Object>> children = getTreebymap(p);
				m.put("children", children);
			}else {
				m.put("leaf", true);
			}
		}
		return mt;
 	}
	
	private boolean isNotLeafbymap(int pid)throws Exception {
		List<Map<String, Object>> mi = findmenuBymap(pid);
		return (mi != null && mi.size() > 0);
	}
	
	public List<Map<String, Object>> findmenuBymap(int pid) throws Exception {
		List<Map<String, Object>> mm =  new ArrayList<Map<String, Object>>();  
		for(Map<String, Object> m: ms) {
			
			int p = Integer.valueOf((String)m.get("pid"));
			
			if(p == pid){
				mm.add(m);
			}
		}
		return mm;
	}
	/***
	 * 获取所有模块列表
	 * @param roleid
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getTree(int pid)
			throws Exception {
		List<Map<String, Object>> ms = sysModuleDao.findSysModule(pid);
		for (Map<String, Object> m : ms) {
			pid = (Integer) m.get("id");
			List<Map<String, Object>> children = getTree(pid);
			if(children == null || children.size()>0){
				m.put("expanded", true);
				m.put("leaf", false);
				m.put("children", children);
			}else{
				m.put("leaf", true);
			}
		}
		return ms;
	}
}
