package com.just.practice.common;

/**
 * 
 * @Description TODO extjs 分页配置
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-4 下午4:16:38
 * 
 */
public class PaginationContext {

	/**
	 * 开始记录
	 */
	private final static ThreadLocal<Integer> PAGE_START = new ThreadLocal<Integer>();

	/**
	 * 每页记录数
	 */
	private final static ThreadLocal<Integer> PAGE_SIZE = new ThreadLocal<Integer>();

	/**
	 * 默认记录数
	 */
	public final static int DEFAULT_PAGE_SIZE = 10;

	public static void setPageStart(int start) {
		PAGE_START.set(start);
	}

	public static void setPageSize(int size) {
		PAGE_SIZE.set(size);
	}

	public static int getPageStart() {
		return PAGE_START.get();
	}

	public static int getPageSize() {
		return PAGE_SIZE.get() <= 0 ? DEFAULT_PAGE_SIZE : PAGE_SIZE.get();
	}

	public static void removePageStart() {
		PAGE_START.remove();
	}

	public static void removePageSize() {
		PAGE_SIZE.remove();
	}

	public static void removeAll() {
		removePageSize();
		removePageStart();
	}
}
