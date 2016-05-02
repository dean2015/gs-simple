/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.gsimple.event.annotation.EventListenerMethod;

/**
 * A default event bus.
 * 
 * @author gaosong
 * 
 */
public final class DefaultEventBus extends AbstractEventBus {

	public DefaultEventBus() {
		this(Executors.newFixedThreadPool(1));
	}

	public DefaultEventBus(Executor executor) {
		this(executor, EventListenerMethod.class);
	}

	public DefaultEventBus(Class<? extends Annotation> annotationClass) {
		this(Executors.newFixedThreadPool(1), annotationClass);
	}

	public DefaultEventBus(Executor executor,
			Class<? extends Annotation> annotationClass) {
		super(executor, annotationClass);
	}

}
