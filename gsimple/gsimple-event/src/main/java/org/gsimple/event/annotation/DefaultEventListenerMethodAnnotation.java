/*
 * Copyright © gaosong
 *
 * This program and the accompanying materials are licensed under
 * the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package org.gsimple.event.annotation;

import java.lang.annotation.Annotation;

/**
 * Default annoation provider
 * 
 * @author gaosong
 *
 */
public class DefaultEventListenerMethodAnnotation implements
		EventListenerMethodAnnotation {

	private final Class<? extends Annotation> annotationClass;

	public DefaultEventListenerMethodAnnotation(
			Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	@Override
	public Class<? extends Annotation> getEventListenerMethodAnnotation() {
		return annotationClass;
	}

}
