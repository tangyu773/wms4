package com.just.practice.service.sys.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.common.datasource.DataSource;
import com.just.common.service.ReportServiceAdapter;
import com.just.practice.dao.sys.SysUserDao;
import com.just.practice.po.order_param;



@Service("order_report")
public class OrderServiceImpl extends ReportServiceAdapter{
	@Autowired
	private SysUserDao sysUserDao;
	String st="";
	String et = "";
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> createDatasource(Map<String, Object> params)
			throws Exception {
		order_param values = new order_param();
		values.setStaffid((String) params.get("staffid"));
		values.setSeqno((String) params.get("seqno"));
		values.setSt((String) params.get("st"));
		values.setEt((String) params.get("et"));
		values.setUseflag((String) params.get("useflag"));
		
		st = (String) params.get("st");
		et = (String) params.get("et");
		Object object = sysUserDao.orderquery(values);
		if (object == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DataSource.DATASOURCE_MAP_KEY, new DataSource(
				(List<Map<String, Object>>) object));
		map.putAll(params);
		return map;
	}
	
	public String getFileName() {
		return "wxorder订单表("+st+"到"+et+").xls";
	};
}
