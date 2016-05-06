/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.utils;

/**
 * 
 * @author gaosong
 *
 */
public abstract class StringUtil {

	public static boolean isEmpty(String string) {
		return string == null || "".equals(string);
	}

}
