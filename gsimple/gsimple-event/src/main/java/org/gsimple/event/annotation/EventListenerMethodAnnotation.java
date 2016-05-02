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
 * Event listener annotation interface
 * @author gaosong
 *
 */
public interface EventListenerMethodAnnotation {

	/**
	 * Get the annotation for the current event bus 
	 * @return
	 */
	Class<? extends Annotation> getEventListenerMethodAnnotation();

}
