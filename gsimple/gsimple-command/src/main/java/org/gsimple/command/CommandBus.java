/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.command;

/**
 * Command eventbus
 * 
 * @author gaosong
 * 
 */
public interface CommandBus {

	void register(Object CommandHandler);

	void unregister(Object CommandHandler);

	void publish(String command, Object data);

}
