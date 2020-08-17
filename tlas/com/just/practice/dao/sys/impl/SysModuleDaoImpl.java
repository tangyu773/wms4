package com.just.practice.dao.sys.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.just.practice.dao.AbstractDao;
import com.just.practice.dao.sys.SysModuleDao;
/**
 * 
 * @author zouhaoadmin
 * @date 2016年3月28日
 * 用户模块操作
 *
 */
@Repository("sysModuleDao")
public class SysModuleDaoImpl extends AbstractDao implements SysModuleDao {

	/**
	 * 根据路径获取数据
	 */
	@Override
	public Map<String, Object> loadSysModuleByPath(String path, String param) {
		String sql = null;
		if (StringUtils.isBlank(param)) {
			sql = "select m.menuid, m.pid, m.text, m.icon_cls, m.moduleurl, m.param, m.compoment, m.status, m.msort, m.menu_type "
				+ "from AS10_SYS_MENU m WHERE m.menu_type = 0 AND m.moduleurl = ?";
			return getJdbcTemplate().queryForMap(sql, path);
		}else{
			sql = "select m.menuid, m.pid, m.text, m.icon_cls, m.moduleurl, m.param, m.compoment, m.status, m.msort, m.menu_type "
					+ "from AS10_SYS_MENU m WHERE m.menu_type = 0 AND m.moduleurl = ? and m.param = ? ";
			return getJdbcTemplate().queryForMap(sql, path, param);
		}
	}
	
	
	/**
	 * 根据id获取模块
	 * @param pid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> findSysModule(int pid)
			throws Exception {

		String sql = "SELECT m.param,m.menuid as id,m.pid,m.text,m.compoment,m.moduleurl,m.status,m.msort,m.icon_cls as iconCls  FROM AS10_SYS_MENU  m where m.pid=? order by m.msort asc";
		return getJdbcTemplate().queryForList(sql, pid);
	}
	@Override
	public List<Map<String, Object>> findallModule(int roleid)
			throws Exception {

		String sql = "select t.rightadd AS c,t.rightaudit AS a, t.rightedit AS u, t.rightdel AS d,t.rigthshow as r,m.menuid as id,m.icon_cls as iconCls,m.*,t.* from AS10_SYS_MENU m LEFT JOIN (select * from AS10_SYS_ROLEMODULE rm where rm.roleid =?) t on t.moduleid = m.menuid";
		return getJdbcTemplate().queryForList(sql, roleid);
	}
	@Override
	public List<Map<String, Object>> findRolelModule(int roleid)
			throws Exception {

		String sql = "select m.menuid as id,m.icon_cls as iconCls,rm.rightadd AS c,rm.rightaudit AS a, rm.rightedit AS u, rm.rightdel AS d,rm.rigthshow as r,m.*,rm.* from AS10_SYS_ROLEMODULE rm left join AS10_SYS_MENU m on rm.moduleid = m.menuid where rm.roleid=?";
		return getJdbcTemplate().queryForList(sql, roleid);
	}
	
}
