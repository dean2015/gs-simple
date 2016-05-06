/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gsimple.common.utils.CheckObject;
import org.gsimple.event.annotation.DefaultEventListenerMethodAnnotation;
import org.gsimple.event.annotation.EventListenerMethod;

/**
 * Abstract event bus while implements event bus interface
 * 
 * This class provides a general operation of an event bus.
 * 
 * A customized event bus should be extended this class, and it is easy to do
 * that.
 * 
 * PS: Not thread safe
 * 
 * @author gaosong
 * 
 */
public abstract class AbstractEventBus implements EventBus {

	private static final Logger logger = Logger
			.getLogger(AbstractEventBus.class.getName());

	protected final EventListenerRegistryCenter eventListenerRegistryCenter;

	protected final ReadWriteLock lock = new ReentrantReadWriteLock();

	protected final Executor executor;

	protected final EventDispatcher eventDispatcher;

	public AbstractEventBus(Executor executor) {
		this(executor, EventListenerMethod.class,
				DefaultEventDispatcher.DIRECT_DISPATCHER);
	}

	public AbstractEventBus(Executor executor,
			Class<? extends Annotation> methodAnnotationClass) {
		this(executor, methodAnnotationClass,
				DefaultEventDispatcher.DIRECT_DISPATCHER);
	}

	public AbstractEventBus(Executor executor,
			Class<? extends Annotation> methodAnnotationClass,
			EventDispatcher eventDispatcher) {
		this.executor = executor;
		this.eventDispatcher = eventDispatcher;
		eventListenerRegistryCenter = new EventListenerRegistryCenter(
				new DefaultEventListenerMethodAnnotation(methodAnnotationClass));
	}

	@Override
	public void register(Object eventListener) {
		try {
			CheckObject.checkIsNull(eventListener);
		} catch (NullPointerException e) {
			logger.log(Level.SEVERE,
					"The eventlistener which is to register is Null.", e);
			return;
		}
		lock.writeLock().lock();
		try {
			eventListenerRegistryCenter.register(eventListener, executor);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void unregister(Object eventListener) {
		try {
			CheckObject.checkIsNull(eventListener);
		} catch (NullPointerException e) {
			logger.log(Level.SEVERE,
					"The eventlistener which is to unregister is Null.", e);
			return;
		}
		lock.writeLock().lock();
		try {
			eventListenerRegistryCenter.unregister(eventListener);
		} finally {
			lock.writeLock().unlock();
		}

	}

	@Override
	public void publish(final Object event) {
		CheckObject.checkIsNull(event);
		Iterator<EventListener> eventListeners = eventListenerRegistryCenter
				.get(event);
		if (!CheckObject.isNull(eventListeners)) {
			eventDispatcher.dispatch(event, eventListeners);
		} else {
			if (!(event instanceof DeadEvent)) {
				publish(new DeadEvent(event));
			} else {
				// event ignored.
			}
		}
	}

}
