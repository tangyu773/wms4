package com.just.practice.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.just.practice.common.BaseObject;
import com.just.practice.po.Pagination;
import com.just.practice.utils.AssertUtils;
import com.just.practice.utils.MessageSourceHolder;

/**
 * 
 * @Description TODO 抽象dao
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-4 下午4:06:17
 * 
 */
public class AbstractDao extends BaseObject {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * 总页数字段别名
	 */
	private final static String TABLE_COUNT_NAME = "total";

	/**
	 * 分页 有参
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public Pagination getPager(String sql, Object[] params) throws Exception {
		AssertUtils.isTureOfQuerySql(sql, MessageSourceHolder
				.getMessage("exception.dao.sql.select.error"));
		Map<String, Object> count = AssertUtils.getUniqueResult(jdbcTemplate
				.queryForList(getCountSql(sql), params));
		int total = count == null ? 0 : Integer.parseInt(count.get(
				TABLE_COUNT_NAME).toString());
		// int total = rowSet.getInt(1);
		Pagination pager = new Pagination();
		pager.setSuccess(true);
		if (total != 0) {
			StringBuffer sb = new StringBuffer(sql);

			int start = 0;
			int pagesize = 10;
			/* sb.append(" limit ").append("?").append(", ").append("?"); */
			sql = sb.toString();
			logger.info("====查询分页sql:" + sql);
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql/*
																		 * ,
																		 * DataUtils
																		 * .
																		 * concatenateArray
																		 * (
																		 * params
																		 * , new
																		 * Object
																		 * [
																		 * ]{start
																		 * ,
																		 * pagesize
																		 * })
																		 */);
			pager.setTotal(total);
			pager.setRows(rows);
		}
		return pager;
	}

	/**
	 * 分页 无参
	 * 
	 * @return
	 */
	public Pagination getPager(String sql) throws Exception {
		return getPager(sql, null);
	}

	/**
	 * 获取所有符合条件数据
	 * 
	 * @return
	 */
	public Pagination getAllData(String sql, Object[] params) throws Exception {
		Pagination pager = new Pagination();
		pager.setSuccess(true);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
		pager.setRows(rows);
		if (rows != null) {
			pager.setTotal(rows.size());
		}
		return pager;
	}

	/**
	 * 获取ID
	 * 
	 * @param tableName
	 * @return
	 */
	public final int getSysId(final String tableName) {
		return jdbcTemplate.execute(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection conn)
					throws SQLException {
				String storedProc = "{call dbo.cc40_getsys_mid(?,?)}";
				CallableStatement cs = conn.prepareCall(storedProc);
				cs.setString(1, tableName);
				cs.registerOutParameter(2, Types.INTEGER);
				return cs;
			}
		}, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				return cs.getInt(2);
			}

		});
	}

	private String getCountSql(String sql) {

		sql = sql.trim();

		int index = StringUtils.indexOf(sql.toLowerCase(), " from ");

		String from = sql.substring(index);

		if (StringUtils.containsIgnoreCase(from, " group by ")) {
			sql = "select count(*) as " + TABLE_COUNT_NAME + " from (" + sql
					+ ") as temp";
		} else {
			sql = "select count(*) as " + TABLE_COUNT_NAME + " " + from;
		}

		logger.info("====获取分页总记录数sql语句:" + sql);

		return sql;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

}
