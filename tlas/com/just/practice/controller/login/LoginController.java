package com.just.practice.controller.login;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.just.common.Constants;
import com.just.common.SpExtParams;
import com.just.practice.constants.SessionCode;
import com.just.practice.controller.ControllerSupport;
import com.just.practice.po.Json;
import com.just.practice.service.sys.SysUserService;

import com.just.practice.utils.MessageSourceHolder;

/**
 * 登陆控制器
 * 
 * @author zouhaoadmin
 * 
 */
@Controller
public class LoginController extends ControllerSupport {
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 登陆验证
	 * 
	 * @param response
	 * @param account
	 * @param password
	 * @return 登录信息
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Json login(HttpServletResponse response, String account,
			String password) throws Exception {
		if (StringUtils.isBlank(account)) {
			return new Json(MessageSourceHolder.getMessage(
					"exception.login.error", "用户名为空"), false);
		}
		if (StringUtils.isBlank(password)) {
			return new Json(MessageSourceHolder.getMessage(
					"exception.login.error", "密码为空"), false);
		}
		Map<String, Object> user = sysUserService.loadSysUser(account);
		if(user == null || user.size() == 0) {
			return new Json(MessageSourceHolder.getMessage("exception.login.error","该用户不存在"),false);
		}
		
		String p = user.get("pwd").toString();
		if(!password.equals(p)) {
			return new Json(MessageSourceHolder.getMessage("exception.login.error","密码错误"), false);
		}
		
		
		int status = ((BigDecimal)user.get("useflag")).intValue();
		int roleid = ((BigDecimal)user.get("roleid")).intValue();
		
		//int rstatus = (Integer)user.get("rolestatus");
		if(status != 1 || roleid == 0 ) {
			return new Json(MessageSourceHolder.getMessage("exception.login.error","该用户已失效"), false);
		}
		setTargetFromSession(SessionCode.LOGIN_USER_ROLE, user);
		Map<String, Object> commSession = new HashMap<String, Object>();
//		commSession.put(SpExtParams.IDCARD, value);
//		commSession.put(SpExtParams.DEPARTID, value);
//		commSession.put(SpExtParams.POOLID, value);
		commSession.put(SpExtParams.ROLEID, String.valueOf(user.get("roleid")));
		commSession.put(SpExtParams.STAFFID, String.valueOf(user.get("staff_id")));
		commSession.put(SpExtParams.IDCARD, String.valueOf(user.get("idcard")));
		setTargetFromSession(Constants.WEB_APP_COMMON_SESSION, commSession);
		//user.remove("password");
		return new Json("登录成功!",true);
	}
	@RequestMapping("/validatepwd")
	@ResponseBody
	public Json validatepwd(HttpServletResponse response, String account,
			String password) throws Exception {
		
		if (StringUtils.isBlank(password)) {
			return new Json(MessageSourceHolder.getMessage(
					"exception.login.error", "密码为空"), false);
		}
		Map<String, Object> user = sysUserService.loadSysUser(account);
		
		
		String p = user.get("passwd").toString();
		if(!password.equals(p)) {
			return new Json(MessageSourceHolder.getMessage("exception.login.error","密码错误"), false);
		}
	
		return new Json("验证成功!",true);
	}
	@RequestMapping("/updatepwd")
	@ResponseBody
	public Json updatepwd(HttpServletResponse response, String account,
			String password) throws Exception {
		
		if (StringUtils.isBlank(password)) {
			return new Json(MessageSourceHolder.getMessage(
					"exception.login.error", "密码为空"), false);
		}
		return new Json("修改%s!",sysUserService.updatepwd(account, password));

		
		
		
	}
	/**
	 * 系统退出
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Json logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionCode.LOGIN_USER_ROLE);
		return new Json("成功退出系统", true);
	}

}
