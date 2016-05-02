/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

import org.gsimple.common.utils.ClassesScaner;
import org.gsimple.event.annotation.EventListenerMethodAnnotation;

/**
 * Event listener registry center
 * 
 * @author Administrator
 * 
 */
public final class EventListenerRegistryCenter {

	private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<EventListener>> eventListenerMap = new ConcurrentHashMap<Class<?>, CopyOnWriteArraySet<EventListener>>();

	private EventListenerMethodAnnotation eventListenerMethodAnnotation;

	EventListenerRegistryCenter(EventListenerMethodAnnotation eventAnnotation) {
		this.eventListenerMethodAnnotation = eventAnnotation;
	}

	public Iterator<EventListener> get(Object event) {
		CopyOnWriteArraySet<EventListener> listeners = eventListenerMap
				.get(event.getClass());
		return listeners == null ? null : listeners.iterator();
	}

	void register(Object eventListener, Executor executor) {
		Map<Class<?>, Set<EventListener>> listenerMap = findEventListenerMethods(
				eventListener, executor);
		Set<Class<?>> classes = ClassesScaner.scanAllClasses(false);
		for (Entry<Class<?>, Set<EventListener>> entry : listenerMap.entrySet()) {
			fillEventListenerMap(entry.getKey(), entry.getValue());
			for (Class<?> clz : classes) {
				if (entry.getKey().isAssignableFrom(clz)) {
					fillEventListenerMap(clz, entry.getValue());
				}
			}
		}
	}

	private void fillEventListenerMap(Class<?> eventClass,
			Set<EventListener> eventListeners) {
		CopyOnWriteArraySet<EventListener> listeners = eventListenerMap
				.get(eventClass);
		if (listeners == null) {
			listeners = new CopyOnWriteArraySet<EventListener>();
			eventListenerMap.put(eventClass, listeners);
		}
		listeners.addAll(eventListeners);
	}

	void unregister(Object eventListener) {
		Map<Class<?>, Set<EventListener>> listenerMap = findEventListenerMethods(
				eventListener, null);
		for (Entry<Class<?>, Set<EventListener>> entry : listenerMap.entrySet()) {
			removeListenersFromEventListenerMap(entry.getKey(),
					entry.getValue());
			Set<Class<?>> classes = ClassesScaner.scanAllClasses(false);
			for (Class<?> clz : classes) {
				if (entry.getKey().isAssignableFrom(clz)) {
					removeListenersFromEventListenerMap(clz, entry.getValue());
				}
			}
		}
	}

	private void removeListenersFromEventListenerMap(Class<?> eventClass,
			Set<EventListener> eventListeners) {
		CopyOnWriteArraySet<EventListener> listeners = eventListenerMap
				.get(eventClass);
		if (listeners == null) {
			return;
		}
		listeners.removeAll(eventListeners);
	}

	private Map<Class<?>, Set<EventListener>> findEventListenerMethods(
			Object eventListener, Executor executor) {
		Map<Class<?>, Set<EventListener>> listenerMap = new HashMap<Class<?>, Set<EventListener>>();
		Class<?> clz = eventListener.getClass();
		for (Method method : clz.getDeclaredMethods()) {
			if (method.getAnnotation(eventListenerMethodAnnotation
					.getEventListenerMethodAnnotation()) == null) {
				continue;
			} else {
				EventListener listener = new EventListener(eventListener,
						method, executor);
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length != 1) {
					throw new IllegalArgumentException(
							"事件监听方法只有一个参数，代表事件Class（类型）");
				}
				Class<?> eventType = parameterTypes[0];
				Set<EventListener> listeners = listenerMap.get(eventType);
				if (listeners == null) {
					listeners = new HashSet<EventListener>();
					listenerMap.put(eventType, listeners);
				}
				listeners.add(listener);
			}
		}
		return listenerMap;
	}

}