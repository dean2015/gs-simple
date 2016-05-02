package org.gsimple.event;

import java.util.Iterator;

public interface EventDispatcher {

	void dispatch(Object event, Iterator<EventListener> eventListeners);
	
}
