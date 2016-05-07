/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.command;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.command.event.annotation.Command;
import org.gsimple.command.event.annotation.CommandHandlerMethod;
import org.gsimple.command.model.AbstractCommand;
import org.gsimple.common.utils.CheckObject;
import org.gsimple.common.utils.StringUtil;
import org.gsimple.event.utils.EventListenerUtils;

/**
 * Event listener registry center
 * 
 * @author gaosong
 * 
 */
public final class CommandHandlerRegistryCenter {

	private static final Logger logger = Logger
			.getLogger(CommandHandlerRegistryCenter.class.getName());

	private final ConcurrentMap<String, CopyOnWriteArraySet<AbstractCommand>> eventListenerMap = new ConcurrentHashMap<String, CopyOnWriteArraySet<AbstractCommand>>();

	CommandHandlerRegistryCenter() {
	}

	Iterator<AbstractCommand> get(String command) {
		CopyOnWriteArraySet<AbstractCommand> commands = eventListenerMap
				.get(command);
		return commands == null ? null : commands.iterator();
	}

	void register(Object commandHandler) {
		Set<Class<?>> commandClasses = EventListenerUtils.findEvents(
				commandHandler, CommandHandlerMethod.class);

		for (Class<?> commandClass : commandClasses) {
			if (!AbstractCommand.class.isAssignableFrom(commandClass)) {
				continue;
			}
			Command commandAnnotation = commandClass
					.getAnnotation(Command.class);
			if (CheckObject.isNull(commandAnnotation)) {
				continue;
			}
			String command = commandAnnotation.command();
			if (StringUtil.isEmpty(command)) {
				continue;
			}
			CopyOnWriteArraySet<AbstractCommand> commandObjects = eventListenerMap
					.get(command);
			if (CheckObject.isNull(commandObjects)) {
				commandObjects = new CopyOnWriteArraySet<AbstractCommand>();
				eventListenerMap.put(command, commandObjects);
			}
			// if duplicate commandObject, skip.
			boolean hasCommand = false;
			for (AbstractCommand cmd : commandObjects) {
				if (cmd.getClass() == commandClass) {
					hasCommand = true;
					break;
				}
			}
			if (!hasCommand) {
				try {
					commandObjects.add((AbstractCommand) commandClass
							.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					logger.log(Level.WARNING, "Register command [" + command
							+ "] in error.", e);
				}
			}
		}
	}

	void unregister(Object commandHandler) {
		throw new AbstractMethodError(
				"For a moment, I don't think an unregister method is necessary.");
	}

}