package com.just.practice.utils;

import java.util.List;
import java.util.Map;

import com.just.practice.exception.DataAaccessException;

/**
 * 
 * @Description TODO 常用异常处理工具类
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-11-28 上午10:21:47
 * 
 */
public class AssertUtils {

	/**
	 * 非空判断
	 * 
	 * @param terget
	 * @param message
	 */
	public static void notNull(Object terget, String message) {
		if (terget == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 数组是否为空
	 * 
	 * @param obj
	 * @param message
	 */
	public static void isNotEmpty(Object[] obj, String message) {
		if (obj == null || obj.length == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断查询语句是否正确
	 * 
	 * @param sql
	 */
	public static void isTureOfQuerySql(String sql, String message) {
		if (sql == null || !sql.trim().toLowerCase().startsWith("select")) {
			throw new DataAaccessException(message);
		}
	}

	/**
	 * 返回唯一记录(解决 queryForObject null 异常)
	 * 
	 * @param datas
	 * @return
	 */
	public static <E> E getUniqueResult(List<E> datas) {
		if (datas == null || datas.size() == 0) {
			return null;
		}
		if (datas.size() > 1) {
			throw new DataAaccessException("该条记录在数据库中不是唯一值");
		}
		return datas.iterator().next();
	}

	/**
	 * 格式化报表数据
	 * 
	 * @param datas
	 * @return
	 */
	public static void formatOrderInfoForReport(List<Map<String, Object>> datas) {
		if (datas == null || datas.isEmpty()) {
			return;
		}
		for (Map<String, Object> data : datas) {
			String cardids = data.get("cardids") == null ? "" : data.get(
					"cardids").toString();
			if ("".equals(cardids)) {
				continue;
			}
			String[] cards = cardids.split("#");
			String card = "";
			int len = cards.length;
			for (int i = 0; i < len; i++) {
				card += (i == (len - 1)) ? cards[i] : cards[i] + "、";
			}
			data.put("cardids", card);
		}
	}
}
