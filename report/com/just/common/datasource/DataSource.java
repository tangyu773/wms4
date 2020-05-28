/**
 * 
 */
package com.just.common.datasource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * @author Chenwenjie
 * @date 2009-12-13
 */
public class DataSource implements JRDataSource {
	
	/**
	 * datasource 数据（map）key名称
	 */
	public static final String DATASOURCE_MAP_KEY = "dataSource";
	public static final String FILE_NAME = "file_name";
	
	private List<Map<String,Object>> dataSource;
	private Iterator<Map<String, Object>> it;
	private Map<String, Object> item = null;
	public DataSource(List<Map<String,Object>> dataSource) {
		this.dataSource = dataSource;
		it = this.dataSource.iterator();
	}
	
	public Object getFieldValue(JRField f) throws JRException {
		return item.get(f.getName());
	}

	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDataSource#next()
	 */
	public boolean next() throws JRException {
		boolean flag = it.hasNext();
		
		if(flag){
			item = it.next();
		}
		return flag;
	}

	public List<Map<String,Object>> getDataSource(){
		return dataSource;
	}
}
