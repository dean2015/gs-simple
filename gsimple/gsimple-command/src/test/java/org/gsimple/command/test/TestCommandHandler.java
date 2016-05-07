/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.command.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.command.event.annotation.CommandHandlerMethod;

public class TestCommandHandler {

	private static final Logger logger = Logger
			.getLogger(TestCommandHandler.class.getName());

	static long startTime = System.currentTimeMillis();

	@CommandHandlerMethod
	public void handler1(TestCommand command) {
		System.out.println("handler1 triggered");
	}

	@CommandHandlerMethod
	public void handler2(TestCommand command) {
		System.out.println("handler2 triggered");
	}

	@CommandHandlerMethod
	public void handler3(TestCommandX command) {
		System.out.println("handler3 triggered");
	}

	@CommandHandlerMethod
	public void handler4(TestCommandY command) {
		System.out.println("handler4 triggered");
	}

	@CommandHandlerMethod
	public void handler5(TestCommandLoop command) {
		int index = (int) command.getData() + 1;

		if (index < 1000000) {
			TestMain.commandBus.publish("TestCommandLoop", index);
		} else {
			logger.log(Level.INFO,
					"Triggered 1M times to use "
							+ (System.currentTimeMillis() - startTime)
							+ " msec.");
			System.exit(0);
		}
	}
}
