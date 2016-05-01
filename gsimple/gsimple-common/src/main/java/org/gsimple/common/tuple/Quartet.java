/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.tuple;

/**
 * Quartet tuple
 * 
 * @param <A>
 *            First value of Quartet
 * @param <B>
 *            Second value of Quartet
 * @param <C>
 *            Third value of Quartet
 * @param <D>
 *            Forth value of Quartet
 * 
 * @author gaosong
 * 
 */
public class Quartet<A, B, C, D> extends Triplet<A, B, C> {

	private static final long serialVersionUID = -702450512233072132L;

	protected D value3;

	public Quartet(A val0, B val1, C val2, D val3) {
		super(val0, val1, val2);
		value3 = val3;
	}

	public D getValue3() {
		return value3;
	}

	public void setValue3(D value3) {
		this.value3 = value3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value3 == null) ? 0 : value3.hashCode());
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
		Quartet<?, ?, ?, ?> other = (Quartet<?, ?, ?, ?>) obj;
		if (value3 == null) {
			if (other.value3 != null)
				return false;
		} else if (!value3.equals(other.value3))
			return false;
		return super.equals(obj);
	}

}
