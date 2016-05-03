/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EventHandler {

	private static final Logger logger = Logger.getLogger(EventHandler.class
			.getName());

	@TestMethod
	private void handleEvent4(ChildEvent e) {
		e.counter.getAndAdd(1);
		if (System.currentTimeMillis() - e.time < 10000) {
			TestMain.eventBus.publish(e);
		} else {
			logger.log(Level.INFO, "Event has triggered " + e.counter
					+ " times in 10 sec.");
			System.exit(0);
		}
	}

}
