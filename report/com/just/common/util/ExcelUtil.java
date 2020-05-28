/**
 * 
 */
package com.just.common.util;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Excel工具类
 * @author CWJ
 * @email 383930815@qq.com
 * @create_time 2014年9月2日 下午12:02:55
 * @update_describle <pre>
 * 无
 * </pre>
 */
public class ExcelUtil {
	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell){
		return getCellValue(cell, null);
	}
	
	/**
	 * 获取单元格的值
	 * @param cell
	 * @param dateFmtPattern
	 * @return
	 */
	public static String getCellValue(Cell cell, String dateFmtPattern) {
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return processNumberCell(cell,dateFmtPattern);
		}
		return "";
	}
	
	public static void setCellValue(Cell cell, Object value){
		if(value instanceof Integer){
			cell.setCellValue((Integer)value);
		}else if(value instanceof String){
			cell.setCellValue((String)value);
		}else {
			cell.setCellValue(String.valueOf(value));
		}
	}

	/**
	 * 处理数字和日期
	 * @param cell
	 * @param dateFmtPattern
	 * @return
	 */
	public static String processNumberCell(Cell cell, String dateFmtPattern) {
		String cellValue = "";
		if (DateUtil.isCellDateFormatted(cell)) {
			if(dateFmtPattern == null){
				dateFmtPattern = "yyyy-MM-dd";
			}
			// 把Date转换成本地格式的字符串
			Date date = cell.getDateCellValue(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					dateFmtPattern, Locale.SIMPLIFIED_CHINESE);
			cellValue = dateFormat.format(date);
		}
		else {
			// 如果是纯数字
			short dataFormatIndex = cell.getCellStyle().getDataFormat();
			double numCellVal = cell.getNumericCellValue();
			DecimalFormat df;
			switch (dataFormatIndex) {
			case 0:
			case 1:
				df = new DecimalFormat("0");
				cellValue = df.format(numCellVal);
				break;
			case 2:
				df = new DecimalFormat("0.00");
				cellValue = df.format(numCellVal);
				break;
			case 3:
				df = new DecimalFormat("#,###");
				cellValue = df.format(numCellVal);
				break;
			case 4:
				df = new DecimalFormat("#,##0.00");
				cellValue = df.format(numCellVal);
				break;
			default:
				cellValue = String.valueOf(numCellVal);
				break;
			}
			if (dataFormatIndex < 0 || dataFormatIndex > 0x31
					|| dataFormatIndex == 0x1f) {
				Date date = DateUtil.getJavaDate(numCellVal);
				if (numCellVal > 1.00) {
					if(dateFmtPattern == null){
						dateFmtPattern = "yyyy-MM-dd";
					}
//					SimpleDateFormat dateFormat = new SimpleDateFormat(
//							dateFmtPattern, Locale.SIMPLIFIED_CHINESE);
//					cellValue = dateFormat.format(date);
				} else {
					if(dateFmtPattern == null){
						dateFmtPattern = "HH:mm:ss";
					}
//					SimpleDateFormat dateFormat = new SimpleDateFormat(
//							"HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
//					cellValue = dateFormat.format(date);
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						dateFmtPattern, Locale.SIMPLIFIED_CHINESE);
				cellValue = dateFormat.format(date);
			}
		}
		return cellValue;
	}
	
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}
	
	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}

		return false;
	}
	
	
	 public static String encodeFilename(String filename, HttpServletRequest request) {    
	      /**  
	       * 获取客户端浏览器和操作系统信息  
	       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
	       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
	       */    
	      String agent = request.getHeader("USER-AGENT");    
	      try {    
	        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
	          String newFileName = URLEncoder.encode(filename, "UTF-8");    
	          newFileName = StringUtils.replace(newFileName, "+", "%20");    
	          if (newFileName.length() > 150) {    
	            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
	            newFileName = StringUtils.replace(newFileName, " ", "%20");    
	          }    
	          return newFileName;    
	        }    
	        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
	          return MimeUtility.encodeText(filename, "UTF-8", "B");    
	      
	        return filename;    
	      } catch (Exception ex) {    
	        return filename;    
	      }    
	    } 
	
}
