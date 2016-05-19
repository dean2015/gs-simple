/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.test.adv;

import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.event.BaseEventBus;
import org.gsimple.event.EventBus;

public class TestMain {

	private static final Logger logger = Logger.getLogger(TestMain.class
			.getName());

	public static EventBus eventBus = new BaseEventBus(
			Executors.newFixedThreadPool(1), TestMethod.class);

	public static void main(String[] args) throws InterruptedException {
		eventBus.register(new EventHandler());
		Event event = new ChildEvent();
		event.time = System.currentTimeMillis();
		eventBus.publish(event);

		logger.log(Level.INFO, "Process will finish in 10 sec.");
	}

}
