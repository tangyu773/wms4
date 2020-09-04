
package com.just.practice.dao.order;

import java.util.List;
import java.util.Map;


public interface OrderDao {
	/**
	 * 根据用户名查询一条数据
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getorderlist() throws Exception;
	public List<Map<String, Object>> revlist() throws Exception;
	public List<Map<String, Object>> getdetailist(String order_id) throws Exception;
		
	
}
