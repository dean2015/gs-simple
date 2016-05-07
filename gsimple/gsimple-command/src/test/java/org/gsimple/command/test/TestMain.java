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

import org.gsimple.command.CommandBus;
import org.gsimple.command.DefaultCommandBus;

public class TestMain {

	private static final Logger logger = Logger.getLogger(TestMain.class
			.getName());

	public static CommandBus commandBus = DefaultCommandBus.getInstance();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		commandBus.register(new TestCommandHandler());
		commandBus.publish("TestCommandLoop", 1);

		logger.log(Level.INFO, "Test starts.");
	}

}
