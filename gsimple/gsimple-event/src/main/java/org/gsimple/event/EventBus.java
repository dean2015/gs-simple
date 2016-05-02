/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

/**
 * Event bus interface
 * 
 * @author gaosong
 *
 */
public interface EventBus {

	void publish(Object event);

	void register(Object eventListener);

	void unregister(Object eventListener);

}
