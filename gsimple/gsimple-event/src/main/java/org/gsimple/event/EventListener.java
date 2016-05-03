/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event listener class & method
 * 
 * @author gaosong
 * 
 */
public final class EventListener {

	private static final Logger logger = Logger.getLogger(EventListener.class
			.getName());

	private final Object eventListenerInstance;

	private final Method eventListenerMethod;

	private final Executor executor;

	EventListener(Object eventListenerHostClass, Method eventListenerMethod,
			Executor executor) {
		super();
		this.eventListenerInstance = eventListenerHostClass;
		this.eventListenerMethod = eventListenerMethod;
		this.executor = executor;
	}

	void call(final Object event) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				boolean accessiable = eventListenerMethod.isAccessible();
				try {
					eventListenerMethod.setAccessible(true);
					eventListenerMethod.invoke(eventListenerInstance, event);
				} catch (IllegalArgumentException e) {
					logger.log(Level.WARNING,
							"Illegal arguments for Event listener", e);
				} catch (IllegalAccessException e) {
					logger.log(Level.WARNING,
							"Illegal access for Event listener", e);
				} catch (InvocationTargetException e) {
					logger.log(Level.WARNING, "Bad Event listener", e);
				} finally {
					eventListenerMethod.setAccessible(accessiable);
				}
			}
		});
	}

	Object getEventListenerInstance() {
		return eventListenerInstance;
	}

	Method getEventListenerMethod() {
		return eventListenerMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((eventListenerInstance == null) ? 0 : eventListenerInstance
						.hashCode());
		result = prime
				* result
				+ ((eventListenerMethod == null) ? 0 : eventListenerMethod
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventListener other = (EventListener) obj;
		if (eventListenerInstance == null) {
			if (other.eventListenerInstance != null)
				return false;
		} else if (!eventListenerInstance.equals(other.eventListenerInstance))
			return false;
		if (eventListenerMethod == null) {
			if (other.eventListenerMethod != null)
				return false;
		} else if (!eventListenerMethod.equals(other.eventListenerMethod))
			return false;
		return true;
	}

}
