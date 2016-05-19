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
 * A base event bus, which can be concreted.
 * 
 * @author gaosong
 * 
 */
public final class BaseEventBus extends AbstractEventBus {

	public BaseEventBus() {
		this(Executors.newFixedThreadPool(1));
	}

	public BaseEventBus(Executor executor) {
		this(executor, EventListenerMethod.class);
	}

	public BaseEventBus(Class<? extends Annotation> annotationClass) {
		this(Executors.newFixedThreadPool(1), annotationClass);
	}

	public BaseEventBus(Executor executor,
			Class<? extends Annotation> annotationClass) {
		super(executor, annotationClass);
	}

}
