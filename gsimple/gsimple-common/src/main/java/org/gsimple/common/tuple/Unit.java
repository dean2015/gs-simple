/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.tuple;

import java.io.Serializable;

/**
 * Unit tuple
 * 
 * @param <A>
 *            The value of Unit
 * 
 * @author gaosong
 * 
 */
public class Unit<A> implements Serializable {

	private static final long serialVersionUID = 6283261538434608821L;

	protected A value0;

	public Unit(A val0) {
		value0 = val0;
	}

	public A getValue0() {
		return value0;
	}

	public void setValue0(A value0) {
		this.value0 = value0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value0 == null) ? 0 : value0.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit<?> other = (Unit<?>) obj;
		if (value0 == null) {
			if (other.value0 != null)
				return false;
		} else if (!value0.equals(other.value0))
			return false;
		return true;
	}

}
