
package com.just.practice.dao.order.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.just.practice.dao.AbstractDao;
import com.just.practice.dao.order.OrderDao;
import com.just.practice.utils.AssertUtils;




@Repository("OrderDao")
public class OrderDaoImpl extends AbstractDao implements OrderDao {

	@Override
	public List<Map<String, Object>> getorderlist() throws Exception {
		String sql = "select oh.* ,t.description,s.description as des from orderhead oh left join type t on oh.typecode=t.code left join  state s on oh.statecode=s.code order by oh.createtime desc";
		return getJdbcTemplate().queryForList(sql);
	}
	@Override
	public List<Map<String, Object>> revlist() throws Exception {
		String sql = "select rh.*,t.description,s.description as des from receivinghead rh left join type t on rh.RECEIVINGHEADMODE=t.code left join state s on s.code=rh.statecode order by rh.createtime desc";
		return getJdbcTemplate().queryForList(sql);
	}
	@Override
	public List<Map<String, Object>> getdetailist(String order_id) throws Exception {
		String sql = "select t.*,i.code,i.name,i.unitid from orderdetail t left join item i on i.itemid=t.itemid where t.orderid=?";
		return getJdbcTemplate().queryForList(sql,order_id);
	}


	
}
