
/**
 * 
 */
package com.just.practice.controller.order;

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
import com.just.practice.service.order.OrderService;
import com.just.practice.service.sys.SysUserService;


/**
 * @author zouhaoadmin
 * @date 2016年1月27日
 *用户控制器
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController  extends ControllerSupport{
	@Autowired
	private OrderService orderService;
	
	/**
	 * 加载用户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderlist")
	@ResponseBody
	public Json loadLoginInfo() throws Exception {
		List<Map<String, Object>> req = orderService.getorderlist();
		return new Json("获取出库单成功", true, req);
	}
	@RequestMapping("/showUsrTree")
	@ResponseBody
	public List<Map<String, Object>> showDept() throws Exception {
		return orderService.findusrForTree();
	}
	@RequestMapping("/droptreepanel")
	@ResponseBody
	public Json droptreepanel(String id,String pid) throws Exception {
		
		return new Json("修改%s!",orderService.droptreepanel(id, pid));
	}
	@RequestMapping("/sys_role_query")
	@ResponseBody
	public List<Map<String, Object>> sys_role_query() throws Exception {
		return orderService.sys_role_query();
	}
	
	
}
