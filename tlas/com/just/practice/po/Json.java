package com.just.practice.po;

/**
 * 
 * @Description TODO json 消息对象
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-4 下午3:12:17
 * 
 */
public class Json {

	/**
	 * 消息
	 */
	private String info;

	/**
	 * 状态 true-成功，false-失败
	 */
	private boolean success;

	/**
	 * 数据
	 */
	private Object rows;

	public boolean isSuccess() {
		return success;
	}

	public Json() {
	}

	/**
	 * 
	 * @param info
	 * @param success
	 * @param rows
	 */
	public Json(String info, boolean success, Object rows) {
		// this.info = info;
		this.success = success;
		this.info = success ? String.format(info, "成功") : String.format(info,
				"失败");
		this.rows = rows;
	}

	public Json(String info, boolean success) {
		this(info, success, null);
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

}
