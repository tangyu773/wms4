package com.just.practice.controller.sys;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.just.practice.constants.SessionCode;
import com.just.practice.controller.ControllerSupport;
import com.just.practice.service.sys.SysModuleService;
import com.just.practice.service.sys.SysRoleRightService;

@Controller
@RequestMapping("/sys/module")
public class ModuleController extends ControllerSupport {
	@Autowired
	private SysRoleRightService sysRoleRightService;
	
	@Autowired
	private SysModuleService sysModuleService;
	
	/**
	 * 生成左边模块树菜单
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadModulelist")
	@ResponseBody
	public List<Map<String, Object>> loadModuleList(
			@RequestParam(value = "node", required = false, defaultValue = "0") int node)
			throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> ur = getTargetFromSessionByCode(
				SessionCode.LOGIN_USER_ROLE, HashMap.class);
		int roleid = ((BigDecimal) ur.get("roleid")).intValue();
		List<Map<String, Object>> req = sysRoleRightService.getModuleList(roleid, node);
		return req;
	}
	/**
	 * 生成模块树
	 * @return
	 * @throws Exception
	**/
	@RequestMapping("/show")
	@ResponseBody
	public List<Map<String, Object>> showModule(@RequestParam(required=false)String pid) throws Exception {
		int iPid = 0;
		if(!StringUtils.isEmpty(pid)){
			iPid = Integer.parseInt(pid);
		}
		List<Map<String, Object>> a = sysModuleService.findSysModuleForTree(iPid);
		return a;
	}
	@RequestMapping("/rootview")
	@ResponseBody
	public List<Map<String, Object>> showrootview(@RequestParam(required=false)String roleid) throws Exception {
		int iPid = 0;
		if(!StringUtils.isEmpty(roleid)){
			iPid = Integer.parseInt(roleid);
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> ur = getTargetFromSessionByCode(
				SessionCode.LOGIN_USER_ROLE, HashMap.class);
		int role_id = ((BigDecimal) ur.get("roleid")).intValue();
		
		if(iPid == 0){
			return sysModuleService.findallmenu(0);
		}
		if(iPid >0 )
		{
			if(role_id==1){
				return sysModuleService.findallmenu(iPid);
			}else{
				return sysModuleService.findrolemenu(iPid);
			}
			
			//return sysModuleService.findallmenu(role_id);
			//return sysModuleService.findrolemenu(iPid);
		}
		return sysModuleService.findallmenu(role_id);
	}
}
