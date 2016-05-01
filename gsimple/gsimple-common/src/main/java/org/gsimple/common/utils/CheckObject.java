/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.utils;

/**
 * Object util
 * 
 * @author gaosong
 * 
 */
public abstract class CheckObject {

	/**
	 * Check Object null
	 * 
	 * @param object
	 * @return Boolean
	 */
	public static <T> Boolean isNull(T object) {
		return null == object ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Check Object null
	 * 
	 * @param object
	 *            An instance of object
	 * @return the object of formal parameter
	 * @exception NullPointerException
	 */
	public static <T> T checkIsNull(T object) {
		if (null == object) {
			throw new NullPointerException();
		}
		return object;
	}

	/**
	 * Check if a, b are from same Class.
	 * 
	 * @param a
	 * @param b
	 * @return Boolean
	 */
	public static <A, B> Boolean isFromSameClass(A a, B b) {
		return a != null && b != null && a.getClass() == b.getClass();
	}

}
