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


	public List<Map<String, Object>> getorderlist() throws Exception;
	public List<Map<String, Object>> revlist() throws Exception;
	public List<Map<String, Object>> getdetailist(String order_id) throws Exception;
}

