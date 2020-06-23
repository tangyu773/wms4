package com.just.practice.dao.sys.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.just.practice.dao.AbstractDao;
import com.just.practice.dao.sys.SysUserDao;
import com.just.practice.po.order_param;
import com.just.practice.utils.AssertUtils;



@Repository("sysUserDao")
public class SysUserDaoImpl extends AbstractDao implements SysUserDao {

    /**
     * 根据账号查找用户信息	
     */
	@Override
	public Map<String, Object> loadSysUser(String account) throws Exception {
		String sql = "select " +
				"u.staffid,u.staffname,u.phone," +
				"u.pwd,ur.roleid," +
				"r.useflag,r.roledes " +				
				" from as10_staff  u " +		
				"left join as10_sys_roleuser  ur ON (u.staffid = ur.staff_id) " +
				"left join as10_sys_role  r ON (ur.roleid = r.roleid)  where u.staffid=? and u.useflag=1";
			List<Map<String, Object>> datast = getJdbcTemplate().queryForList(sql, Integer.valueOf(account));
			return AssertUtils.getUniqueResult(datast);
	}
	@Override
	public List<Map<String, Object>> findusertree() throws Exception {
		String sql = "SELECT usrname as text,staff_id as id,p_staff_id as pid  "+
				" FROM wx10_sys_user AS m  order by m.p_staff_id asc";
		return getJdbcTemplate().queryForList(sql);
	}
	@Override
	public boolean droptreepanel(String id,String pid) {
		String sql = "update wx10_sys_user set p_staff_id = ?  where staff_id = ?";		
		return getJdbcTemplate().update(sql, id,pid) > 0;	
	}
	@Override
	public boolean updatepwd(String id,String pwd) {
		String sql = "update wx10_sys_user set passwd = ?  where staff_id = ?";		
		return getJdbcTemplate().update(sql, pwd,id) > 0;	
	}
	@Override
	public Object orderquery(order_param odparam) {
		//String sql = "update wx10_sys_user set passwd = ?  where staff_id = ?";		
		//return getJdbcTemplate().update(sql, odparam.getStaffid(),odparam.getSeqno(),odparam.getSt(),odparam.getEt(),odparam.getUseflag()) > 0;	
		try{
			String sql = "{call sp_web_order_query(?,?,?,?,?)}";
			/*List<Object> params = new ArrayList<Object>();
			params.add(odparam.getStaffid());
			params.add(odparam.getSeqno());
			params.add(odparam.getSt());
			params.add(odparam.getEt());			
			params.add(odparam.getUseflag());*/
			
			return getJdbcTemplate().queryForList(sql, odparam.getStaffid(),odparam.getSeqno(),odparam.getSt(),odparam.getEt(),odparam.getUseflag());
			}catch(Exception e){
				logger.error("导出满意度统计表错误："+e.getMessage());
				return null;
			}
	}
	@Override
	public Map<String, Object> getkur() throws Exception {
		String sql = "select round(count(0)/18700,2) as krvalue from location l where l.zoneid = 'b83bd5e41ccd4b7c90d1f1eccb0361ff'  and l.containerbarcode is not null ";
			List<Map<String, Object>> datast = getJdbcTemplate().queryForList(sql);
			return AssertUtils.getUniqueResult(datast);
	}
}
