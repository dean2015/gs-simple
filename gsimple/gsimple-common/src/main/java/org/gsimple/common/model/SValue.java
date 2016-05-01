/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.model;

import java.io.Serializable;

/**
 * SValue<T> represents a value of a type <T>
 * 
 * @author gaosong
 *
 */
public abstract class SValue<T> implements Serializable {

	private static final long serialVersionUID = 4122054308767161077L;

	private T value;

	public T getValue() {
		return value;
	}

	public SValue(final T value) {
		this.value = value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SValue<?> other = (SValue<?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
