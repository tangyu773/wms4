/**
 * 
 */
package com.just.common.view;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.just.common.datasource.DataSource;
import com.just.common.util.ExcelUtil;

/**
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年12月23日 下午4:37:06
 * @update_describle 
 * <pre>无</pre>
 */
public class AutoColumnsExcelView extends AbstractExcelView {
	private String filename ;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Map<String, Object>> datasource =  ((DataSource)model.get(DataSource.DATASOURCE_MAP_KEY)).getDataSource();
		
		HSSFSheet sheet = workbook.createSheet();    
        sheet.setDefaultColumnWidth(12); 
        
        int size = datasource.size();
        if(size > 0){
        	int startRow = 0;
        	generatesTableHeader(sheet, datasource, startRow);
        	startRow++;
        	generatesTableBody(sheet, datasource, startRow);
        }
		
        filename = new String(filename.getBytes("gbk"),"iso-8859-1");//ExcelUtil.encodeFilename(filename, request);//处理中文文件名  
        response.setContentType("application/vnd.ms-excel");   
        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);     
        ouputStream.flush();     
        ouputStream.close(); 
	}

	private void generatesTableBody(HSSFSheet sheet, List<Map<String, Object>> datasource, int row) {
		Iterator<Map<String, Object>> it = datasource.iterator();
		while (it.hasNext()) {
			Map<java.lang.String, java.lang.Object> item = (Map<java.lang.String, java.lang.Object>) it.next();
			int col = 0;
			for(Map.Entry<String, Object> key : item.entrySet()){
				ExcelUtil.setCellValue(getCell(sheet, row, col++), key.getValue());
			}
			row++;
		}
	}

	/**
	 * 生成表头
	 * @param sheet
	 * @param datasource
	 * @param row
	 */
	private void generatesTableHeader(HSSFSheet sheet, List<Map<String, Object>> datasource, int row) {
		Map<String, Object> item = datasource.get(0);
		int col = 0;
		for(Map.Entry<String, Object> key : item.entrySet()){
			String keyStr = key.getKey();
			getCell(sheet, row, col++).setCellValue(keyStr);
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
