/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.command.event;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.command.CommandBus;
import org.gsimple.command.event.annotation.CommandHandlerMethod;
import org.gsimple.event.AbstractEventBus;

/**
 * A concrete of eventbus which is for a command bus.
 * 
 * @author gaosong
 *
 */
public class CommandEventBus extends AbstractEventBus {

	private static final Logger logger = Logger.getLogger(CommandEventBus.class
			.getName());

	static int threadPoolSize = 10;

	static {
		Properties properties = new Properties();
		try {
			InputStream input = CommandBus.class.getClassLoader()
					.getResourceAsStream("command-config.properties");
			properties.load(input);
			if (!properties.isEmpty()) {
				threadPoolSize = Integer.parseInt(properties
						.getProperty("THREAD_POOL_SIZE"));
			}
		} catch (Exception e) {
			logger.log(
					Level.WARNING,
					"Get threadpool size of command eventbus in error. Threadpool size will be set to 10 as default.",
					e);
		}
	}

	private CommandEventBus() {
		super(Executors.newFixedThreadPool(threadPoolSize),
				CommandHandlerMethod.class);
		
	}

	private static class InnerInstance {
		private static final CommandEventBus instance = new CommandEventBus();
	}

	public static CommandEventBus getInstance() {
		return InnerInstance.instance;
	}
	
}
