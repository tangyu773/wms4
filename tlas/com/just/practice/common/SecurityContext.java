package com.just.practice.common;

/**
 * 
 * @Description TODO 角色， 模块 缓存
 * @author QQing
 * @category www.just-tech.com.cn
 * @date 2013-12-10 下午12:05:12
 * 
 */
public class SecurityContext {

	/**
	 * 角色id
	 */
	private final static ThreadLocal<Integer> ROLEID_LOCAL = new ThreadLocal<Integer>();

	/**
	 * 模块id
	 */
	private final static ThreadLocal<Integer> MODULEID_LOCAL = new ThreadLocal<Integer>();

	public static int getRoleId() {
		return ROLEID_LOCAL.get();
	}

	public static void setRoleId(int roleid) {
		ROLEID_LOCAL.set(roleid);
	}

	public static int getModuleid() {
		return MODULEID_LOCAL.get();
	}

	public static void setModuleid(int moduleid) {
		MODULEID_LOCAL.set(moduleid);
	}

	public static void removeLocalRoleid() {
		ROLEID_LOCAL.remove();
	}

	public static void removeLocalModuleid() {
		MODULEID_LOCAL.remove();
	}

	public static void removeLocalAll() {
		removeLocalRoleid();
		removeLocalModuleid();
	}
}
