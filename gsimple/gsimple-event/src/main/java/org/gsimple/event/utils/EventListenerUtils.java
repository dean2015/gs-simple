package org.gsimple.event.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.gsimple.common.utils.ClassesScaner;

public abstract class EventListenerUtils {

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

	public static Iterator<Class<?>> findEventListenerWithAnnotation(
			Class<?> annotationClass) {
		Set<Class<?>> classesWithAnnotation = new HashSet<Class<?>>();
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
