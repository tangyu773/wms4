/**
 * 
 */
package com.just.practice.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.just.common.web.controller.ControllerSupport;
import com.just.practice.constants.SessionCode;
import com.just.practice.po.Json;
import com.just.practice.service.sys.SysUserService;


/**
 * @author zouhaoadmin
 * @date 2016年1月27日
 *用户控制器
 *
 */
@Controller
@RequestMapping("/sys/user")
public class UserController  extends ControllerSupport{
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 加载用户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadLoginInfo")
	@ResponseBody
	public Json loadLoginInfo() throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> user = getTargetFromSessionByCode(
				SessionCode.LOGIN_USER_ROLE, HashMap.class);
		if (user == null) {
			return new Json("获取登录信息失败,尚未登录", false);
		}
		user.remove("password");
		return new Json("获取登录信息成功", true, user);
	}
	@RequestMapping("/showUsrTree")
	@ResponseBody
	public List<Map<String, Object>> showDept() throws Exception {
		return sysUserService.findusrForTree();
	}
	@RequestMapping("/droptreepanel")
	@ResponseBody
	public Json droptreepanel(String id,String pid) throws Exception {
		
		return new Json("修改%s!",sysUserService.droptreepanel(id, pid));
	}
	
	
}
