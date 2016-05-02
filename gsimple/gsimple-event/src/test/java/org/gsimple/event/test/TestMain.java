/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.test;

import java.util.concurrent.Executors;

import org.gsimple.event.DefaultEventBus;
import org.gsimple.event.EventBus;

public class TestMain {

	public static EventBus eventBus = new DefaultEventBus(
			Executors.newFixedThreadPool(1), TestMethod.class);

	public static void main(String[] args) throws InterruptedException {
		eventBus.register(new EventHandler());
		Event event = new ChildEvent();
		event.time = System.currentTimeMillis();
		eventBus.publish(event);
	}

}
