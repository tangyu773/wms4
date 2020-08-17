package com.just.practice.service.sys.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.practice.common.BaseObject;
import com.just.practice.dao.sys.SysRoleRightDao;
import com.just.practice.exception.ServiceException;
import com.just.practice.service.sys.SysRoleRightService;
import com.just.practice.utils.MessageSourceHolder;



@Service("sysRoleRightService")
public class SysRoleRightServiceImpl extends BaseObject implements
		SysRoleRightService {

	@Autowired
	private SysRoleRightDao sysRoleRightDao;
	List<Map<String, Object>> ms = new ArrayList<Map<String, Object>>();  
	@Override
	public List<Map<String, Object>> getModuleList(int roleid, int pid) throws Exception {
		logger.info("==>获取模块列表...\n参数：roleid="+ roleid);
		if(roleid == 0){
			throw new ServiceException(MessageSourceHolder
			.getMessage("exception.service.searchModule.error"));
		}
		
		//return moduleList(roleid, pid);
		ms = sysRoleRightDao.searchmapModule(roleid);
		ms=transformUpperCase(ms);
		return getTree(0);
	}
	
	/***
	 * 获取所有模块列表
	 * @param roleid
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private List<Map<String, Object>> moduleList(int roleid, int pid)
			throws Exception {
		List<Map<String, Object>> ms = sysRoleRightDao
				.searchModule(roleid, pid);
		for (Map<String, Object> m : ms) {
			pid = ((BigDecimal)m.get("id")).intValue();
			List<Map<String, Object>> children = moduleList(roleid, pid);
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
	private List<Map<String, Object>> getTree(int i) throws Exception {
		List<Map<String, Object>> mt = findKgBymap(i);
		for(Map<String, Object> m: mt) {
			int p = ((BigDecimal)m.get("id")).intValue();
			if(isNotLeaf(p)) {
				m.put("expanded", false);
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
	private boolean isNotLeaf(int pid)throws Exception {
		List<Map<String, Object>> mi = findKgBymap(pid);
		return (mi != null && mi.size() > 0);
	}
	
	public List<Map<String, Object>> findKgBymap(int pid) throws Exception {
		List<Map<String, Object>> mm =  new ArrayList<Map<String, Object>>();  
		for(Map<String, Object> m: ms) {
			int p = Integer.parseInt( m.get("pid").toString());
			
			if(p == pid){
				mm.add(m);
			}
		}
		return mm;
	}
	public static List<Map<String, Object>> transformUpperCase(List<Map<String, Object>> list) {
        for (int i = 0;i < list.size(); i++){
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, Object> map = list.get(i);
 
            if (map == null || map.isEmpty()) {
 
                return list;
            }
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
            	String newKey=key;
            	if(!key.equalsIgnoreCase("iconCls")){
            		 newKey = key.toLowerCase();
            	}
            	else{
            		newKey="iconCls";
            	}
                
                //newKey = newKey.replace("_", "");
                resultMap.put(newKey, map.get(key));
            }
            list.set(i,resultMap);
        }
        return list;
	}
}
