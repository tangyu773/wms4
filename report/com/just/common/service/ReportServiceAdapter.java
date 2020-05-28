/**
 * 
 */
package com.just.common.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年9月30日 下午1:16:15
 * @update_describle 
 * <pre>无</pre>
 */
public abstract class ReportServiceAdapter implements ReportServiceI {
	private String filename = null;
	/* (non-Javadoc)
	 * @see com.just.common.service.ReportServiceI#createDatasource(java.util.Map)
	 */
	@Override
	public Map<String, Object> createDatasource(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.just.common.service.ReportServiceI#renameFile(java.util.Map)
	 */
	@Override
	public void renameFile(Map<String, Object> params) {
		
	}
	
	@Override
	public void setFileName(String filename) {
		try {
			this.filename = "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO_8859_1") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getFileName() {
		
		return filename;
	}

}
