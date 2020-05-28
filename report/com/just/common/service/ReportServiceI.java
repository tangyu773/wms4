/**
 * 
 */
package com.just.common.service;

import java.util.Map;


/**
 * 报表通用接口
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年9月30日 下午1:11:25
 * @update_describle 
 * <pre>无</pre>
 */
public interface ReportServiceI {
	/**
	 * 生成数据源
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> createDatasource(Map<String, Object> params) throws Exception;
	/**
	 * 重命名报表文件
	 * @param params
	 * @return
	 */
	public void renameFile(Map<String, Object> params);
	/**
	 * 设置文件名
	 * @param filename
	 */
	public void setFileName(String filename);
	/**
	 * 获取文件名
	 * @return
	 */
	public String getFileName();
	
}
