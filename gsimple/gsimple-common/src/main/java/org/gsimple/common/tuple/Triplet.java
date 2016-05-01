/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.tuple;

/**
 * Triplet tuple
 * 
 * @param <A>
 *            First value of Triplet
 * @param <B>
 *            Second value of Triplet
 * @param <C>
 *            Third value of Triplet
 * @author gaosong
 * 
 */
public class Triplet<A, B, C> extends Pair<A, B> {

	private static final long serialVersionUID = 7783225606384782799L;

	protected C value2;

	public Triplet(A val0, B val1, C val2) {
		super(val0, val1);
		value2 = val2;
	}

	public C getValue2() {
		return value2;
	}

	public void setValue2(C value2) {
		this.value2 = value2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
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
		Triplet<?, ?, ?> other = (Triplet<?, ?, ?>) obj;
		if (value2 == null) {
			if (other.value2 != null)
				return false;
		} else if (!value2.equals(other.value2))
			return false;
		return super.equals(obj);
	}

}
