/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.util.Iterator;

public interface EventDispatcher {

	void dispatch(Object event, Iterator<EventListener> eventListeners);
	
}
