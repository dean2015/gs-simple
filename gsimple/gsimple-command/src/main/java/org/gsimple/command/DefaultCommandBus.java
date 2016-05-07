/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.command;

import java.util.Iterator;

import org.gsimple.command.event.CommandEventBus;
import org.gsimple.command.model.AbstractCommand;
import org.gsimple.common.utils.CheckObject;

/**
 * A default command bus.
 * 
 * @author gaosong
 *
 */
public class DefaultCommandBus implements CommandBus {

	protected final CommandHandlerRegistryCenter commandHandlerRegistryCenter;

	private final CommandEventBus commandEventBus;

	private DefaultCommandBus() {
		commandEventBus = CommandEventBus.getInstance();
		commandHandlerRegistryCenter = new CommandHandlerRegistryCenter();
	}

	private static class InnerInstance {
		private static final DefaultCommandBus instance = new DefaultCommandBus();
	}

	public static DefaultCommandBus getInstance() {
		return InnerInstance.instance;
	}

	@Override
	public void register(Object CommandHandler) {
		CheckObject.checkIsNull(CommandHandler);
		commandHandlerRegistryCenter.register(CommandHandler);
		commandEventBus.register(CommandHandler);
	}

	@Override
	public void unregister(Object CommandHandler) {
		CheckObject.checkIsNull(CommandHandler);
		commandEventBus.unregister(CommandHandler);
		commandHandlerRegistryCenter.unregister(CommandHandler);
	}

	@Override
	public void publish(String command, Object data) {
		CheckObject.checkIsNull(command);
		Iterator<AbstractCommand> commandHandlers = commandHandlerRegistryCenter
				.get(command);
		if (!CheckObject.isNull(commandHandlers)) {
			while (commandHandlers.hasNext()) {
				AbstractCommand cmd = commandHandlers.next();
				cmd.setData(data);
				commandEventBus.publish(cmd);
			}
		}
	}

}
