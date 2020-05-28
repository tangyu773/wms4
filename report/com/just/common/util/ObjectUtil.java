package com.just.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectUtil {
	/**
	 * 将sourceObj的属性拷贝到targetObj
	 * 
	 * @param sourceObj
	 * @param targetObj
	 * @param clazz
	 *            从哪一个类开始(比如sourceObj对象层级为:Object->User->ChineseUser->ChineseMan
	 *            ->ChineseChongQingMan)
	 *            如果需要从ChineseUser开始复制，clazz就指定为ChineseUser.class
	 */
	public static void clone(Object sourceObj, Object targetObj, Class<?> clazz)
			throws Exception {
		if (sourceObj == null || targetObj == null) {
			throw new Exception("源对象和目标对象不能为null");
		}
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Object sourceValue = fields[i].get(sourceObj);
			if (Modifier.isStatic(fields[i].getModifiers())) {
				continue;
			}
			fields[i].set(targetObj, sourceValue);
		}
		if (clazz.getSuperclass() == Object.class) {
			return;
		}
		clone(sourceObj, targetObj, clazz.getSuperclass());
	}
}
