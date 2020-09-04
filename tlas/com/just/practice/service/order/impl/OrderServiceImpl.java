
package com.just.practice.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.practice.common.BaseObject;
import com.just.practice.dao.order.OrderDao;
import com.just.practice.service.order.OrderService;



@Service("OrderService")

public class OrderServiceImpl extends BaseObject implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Map<String, Object>> getorderlist() throws Exception {
		return orderDao.getorderlist();
	}
	@Override
	public List<Map<String, Object>> revlist() throws Exception {
		// TODO Auto-generated method stub
		return orderDao.revlist();
	}
	@Override
	public List<Map<String, Object>> getdetailist(String order_id) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.getdetailist(order_id);
	}





}
