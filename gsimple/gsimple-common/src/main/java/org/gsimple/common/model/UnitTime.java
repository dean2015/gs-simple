/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * United time
 * 
 * @author gaosong
 *
 */
public final class UnitTime implements Serializable {

	private static final long serialVersionUID = 2558640141012289818L;

	/**
	 * An unit that refers java.util.concurrent.TimeUnit
	 */
	private TimeUnit unit;

	/**
	 * The data of time, and its type is Long
	 */
	private SValue<Long> value;

	public UnitTime(SValue<Long> value, TimeUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public SValue<Long> getValue() {
		return value;
	}

	public void setValue(SValue<Long> value) {
		this.value = value;
	}

}
