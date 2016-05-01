/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.utils;

import java.util.Collection;

/**
 * Collection util
 * 
 * @author gaosong
 * 
 */
public abstract class CheckCollection {

	/**
	 * Check collection Null or Empty
	 * 
	 * @param object
	 *            An instance of Collection
	 * @return Boolean
	 */
	public static <T extends Collection<?>> Boolean isEmpty(T object) {
		return null == object || object.isEmpty() ? Boolean.TRUE
				: Boolean.FALSE;
	}

}
