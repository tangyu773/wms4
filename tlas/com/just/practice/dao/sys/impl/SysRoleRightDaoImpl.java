package com.just.practice.dao.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.just.practice.dao.AbstractDao;
import com.just.practice.dao.sys.SysRoleRightDao;


@Repository("sysRoleRightDao")
public class SysRoleRightDaoImpl extends AbstractDao implements SysRoleRightDao {

	@Override
	public List<Map<String, Object>> searchModule(int roleid, int pid)
			throws Exception {																										   
		String sql = "SELECT     rm.moduleid as id, m.pid,m.icon_cls as iconCls, rm.rightadd AS c,rm.rightaudit AS a, rm.rightedit AS u, rm.rightdel AS d,rm.rigthshow as r,m.status, m.moduleurl, m.compoment, m.param, m.text"
				+" FROM         wx10_sys_role AS r INNER JOIN"
				                      +" wx10_sys_rolemodule AS rm ON r.roleid = rm.roleid INNER JOIN"
				                     +" sys_menu AS m ON rm.moduleid = m.menuid"
				+" WHERE     (m.pid = ?) AND (r.roleid = ?) AND (m.status = 1) AND (rm.rigthshow > 0)"
				+" ORDER BY m.msort";
		/*String sql1 = "select t2.menuid, t3.pid, t2.crud, t3.moduleurl, t3.compoment, t3.text, t3.icon_cls as iconCls "
				+ "from sys_role as t1 INNER JOIN wx10_sys_rolemodule as t2 ON t1.roleid = t2.roleid "
				+ "INNER JOIN sys_menu as t3 ON t2.menuid = t3.menuid WHERE t3.pid = ? "
				+ "AND t1.roleid = ? AND t3.status = 1 AND SUBSTR(t2.crud,1,1) = '1' "
				+ "AND t3.menu_type = 0 order by t3.msort";*/
		List<Map<String, Object>> ms = getJdbcTemplate().queryForList(sql, pid,
				roleid);
		return ms;
	}
	@Override
	public List<Map<String, Object>> searchmapModule(int roleid)
			throws Exception {																										   
		String sql = "SELECT rm.moduleid as id, m.pid,m.icon_cls as iconCls, rm.rightadd AS c,rm.rightaudit AS a, rm.rightedit AS u, rm.rightdel AS d,rm.rigthshow as r,"
				+" m.status, m.moduleurl, m.compoment, m.param, m.text FROM as10_sys_role  r INNER JOIN as10_sys_rolemodule  rm ON r.roleid = rm.roleid INNER JOIN "
				+ "as10_sys_menu  m ON rm.moduleid = m.menuid WHERE (r.roleid = ?) AND (m.status = 1) AND (rm.rigthshow > 0) ORDER BY m.msort";
		List<Map<String, Object>> ms = getJdbcTemplate().queryForList(sql,
				roleid);
		return ms;
	}
}
