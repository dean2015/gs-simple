package org.gsimple.event;

import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.gsimple.event.annotation.EventListenerMethod;

/**
 * Not thread safe
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
