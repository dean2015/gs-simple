package org.gsimple.event.annotation;

import java.lang.annotation.Annotation;

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
