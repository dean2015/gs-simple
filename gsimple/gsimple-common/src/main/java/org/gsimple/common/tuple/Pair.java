/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.tuple;

/**
 * Pair tuple
 * 
 * @param <A>
 *            First value of Pair
 * @param <B>
 *            Second value of Pair
 * 
 * @author gaosong
 * 
 */
public class Pair<A, B> extends Unit<A> {

	private static final long serialVersionUID = 143376971447679569L;

	protected B value1;

	public Pair(A val0, B val1) {
		super(val0);
		value1 = val1;
	}

	public B getValue1() {
		return value1;
	}

	public void setValue1(B value1) {
		this.value1 = value1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		return super.equals(obj);
	}

}
