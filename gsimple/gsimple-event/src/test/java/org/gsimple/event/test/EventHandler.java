/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.test;

public class EventHandler {

	@TestMethod
	private void handleEvent4(ChildEvent e) {
		e.counter.getAndAdd(1);
		if (System.currentTimeMillis() - e.time < 1000) {
			TestMain.eventBus.publish(e);
		} else {
			System.out.println(e.counter);
			System.exit(0);
		}
	}

}
