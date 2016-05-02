package org.gsimple.event;

import org.gsimple.common.utils.CheckObject;

/**
 * An event whose handler could not be found.
 * 
 * @author gaosong
 * @version 1.0.0
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
