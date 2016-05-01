/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.common.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Identity represents an identifier of any record.
 * 
 * Identity can not be changed after definition.
 * 
 * @author gaosong
 * 
 */
public class Identity implements Serializable {

	private static final long serialVersionUID = 3748158934066150295L;

	protected final String[] identityPathElements;

	public Identity(final Identity identity) {
		identityPathElements = identity.identityPathElements;
	}

	public Identity(final String... elements) {
		if (elements.length > 0) {
			identityPathElements = elements;
		} else {
			throw new IllegalArgumentException(
					"At least one element shoule be provided for construnction.");
		}
	}

	public String[] getIdentityElements() {
		return identityPathElements;
	}

	public String getIdentityPath() {
		StringBuilder path = new StringBuilder();
		for (String element : identityPathElements) {
			if (path.length() > 0) {
				path.append("::");
			}
			path.append(element);
		}
		return path.toString();
	}

	@Override
	public String toString() {
		return getIdentityPath();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(identityPathElements);
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
		Identity other = (Identity) obj;
		if (!Arrays.equals(identityPathElements, other.identityPathElements))
			return false;
		return true;
	}

}
