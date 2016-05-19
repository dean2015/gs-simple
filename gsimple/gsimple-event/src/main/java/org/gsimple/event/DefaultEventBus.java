/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.event.annotation.EventListenerMethod;

/**
 * A default event bus.
 * 
 * @author gaosong
 * 
 */
public final class DefaultEventBus extends AbstractEventBus {

	private static final Logger logger = Logger.getLogger(DefaultEventBus.class
			.getName());

	static int threadPoolSize = 10;

	static {
		Properties properties = new Properties();
		try {
			InputStream input = DefaultEventBus.class.getClassLoader()
					.getResourceAsStream("default-eventbus-config.properties");
			properties.load(input);
			if (!properties.isEmpty()) {
				threadPoolSize = Integer.parseInt(properties
						.getProperty("THREAD_POOL_SIZE"));
			}
		} catch (Exception e) {
			logger.log(
					Level.WARNING,
					"Get threadpool size of default eventbus in error. Threadpool size will be set to 10 as default.",
					e);
		}
	}

	private DefaultEventBus() {
		super(Executors.newFixedThreadPool(threadPoolSize),
				EventListenerMethod.class);

	}

	private static class InnerInstance {
		private static final DefaultEventBus instance = new DefaultEventBus();
	}

	public static DefaultEventBus getInstance() {
		return InnerInstance.instance;
	}

}
