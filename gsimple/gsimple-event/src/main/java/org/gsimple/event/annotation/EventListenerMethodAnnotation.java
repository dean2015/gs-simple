package org.gsimple.event.annotation;

import java.lang.annotation.Annotation;

public interface EventListenerMethodAnnotation {

	Class<? extends Annotation> getEventListenerMethodAnnotation();

}
