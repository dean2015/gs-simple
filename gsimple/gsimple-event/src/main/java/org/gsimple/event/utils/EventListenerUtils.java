/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.gsimple.common.utils.ClassesScaner;

/**
 * Event listener utilities
 * 
 * @author gaosong
 * 
 */
public abstract class EventListenerUtils {

	/**
	 * Find out event listener method from an event listener class with the
	 * specific annotation
	 * 
	 * @param eventListener
	 * @param annotationClass
	 * @return
	 */
	public static Set<Class<?>> findEvents(Object eventListener,
			Class<? extends Annotation> annotationClass) {
		Set<Class<?>> events = new HashSet<Class<?>>();
		Class<?> clz = eventListener.getClass();
		for (Method method : clz.getDeclaredMethods()) {
			if (method.getAnnotation(annotationClass) == null) {
				continue;
			} else {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length != 1) {
					throw new IllegalArgumentException(
							"事件监听方法只有一个参数，代表事件Class（类型）");
				}
				events.add(parameterTypes[0]);
			}
		}
		return events;
	}

	/**
	 * Scan all classes (jar is not included) and find out event listener method
	 * from an event listener class with the specific annotation
	 * 
	 * @param annotationClass
	 * @return
	 */
	public static Iterator<Class<?>> findEventListenerWithAnnotation(
			Class<?> annotationClass) {
		Set<Class<?>> classesWithAnnotation = new HashSet<Class<?>>();
		// Scan all classes (jar is not included)
		Set<Class<?>> classes = ClassesScaner.scanAllClasses(false);
		for (Class<?> clz : classes) {
			Annotation[] annotations = clz.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == annotationClass) {
					classesWithAnnotation.add(clz);
					break;
				}
			}
		}
		return classesWithAnnotation.iterator();
	}

}
