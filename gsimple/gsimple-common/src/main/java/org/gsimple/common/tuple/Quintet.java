/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.tuple;

/**
 * Quintet tuple
 * 
 * @param <A>
 *            First value of Quintet
 * @param <B>
 *            Second value of Quintet
 * @param <C>
 *            Third value of Quintet
 * @param <D>
 *            Forth value of Quintet
 * @param <E>
 *            Fifth value of Quintet
 * 
 * @author gaosong
 * 
 */
public class Quintet<A, B, C, D, E> extends Quartet<A, B, C, D> {

	private static final long serialVersionUID = 7659362806794841460L;

	protected E value4;

	public Quintet(A val0, B val1, C val2, D val3, E val4) {
		super(val0, val1, val2, val3);
		value4 = val4;
	}

	public E getValue4() {
		return value4;
	}

	public void setValue4(E value4) {
		this.value4 = value4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value4 == null) ? 0 : value4.hashCode());
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
		Quintet<?, ?, ?, ?, ?> other = (Quintet<?, ?, ?, ?, ?>) obj;
		if (value4 == null) {
			if (other.value4 != null)
				return false;
		} else if (!value4.equals(other.value4))
			return false;
		return super.equals(obj);
	}

}
