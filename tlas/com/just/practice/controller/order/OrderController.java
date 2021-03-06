
/**
 * 
 */
package com.just.practice.controller.order;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.just.common.web.controller.ControllerSupport;
import com.just.practice.po.Json;
import com.just.practice.service.order.OrderService;



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
	public Json orderlist() throws Exception {
		List<Map<String, Object>> req = orderService.getorderlist();
		return new Json("获取出库单成功", true, req);
	}
	@RequestMapping("/revlist")
	@ResponseBody
	public Json revlist() throws Exception {
		List<Map<String, Object>> req = orderService.revlist();
		return new Json("获取入库单成功", true, req);
	}
	@RequestMapping("/detailist")
	@ResponseBody
	public Json detailist(@RequestParam(required=false)String order_id) throws Exception {
		
		List<Map<String, Object>> req = orderService.getdetailist(order_id);
		return new Json("获取出库详情成功", true, req);
	}
	
	
	
}
