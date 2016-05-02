/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import org.gsimple.common.utils.CheckObject;

/**
 * An event whose listener could not be found.
 * 
 * @author gaosong
 * 
 */
public class DeadEvent {

	private Object event;

	public Object getEvent() {
		return event;
	}

	public void setEvent(Object event) {
		this.event = event;
	}

	public DeadEvent(Object event) {
		super();
		this.event = CheckObject.checkIsNull(event);
	}

}
