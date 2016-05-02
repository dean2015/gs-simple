package org.gsimple.event;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.gsimple.common.utils.CheckObject;

public enum DefaultEventDispatcher implements EventDispatcher {
	DIRECT_DISPATCHER {

		@Override
		public void dispatch(final Object event,
				Iterator<EventListener> eventListeners) {
			CheckObject.checkIsNull(event);
			CheckObject.checkIsNull(eventListeners);
			while (eventListeners.hasNext()) {
				eventListeners.next().call(event);
			}
		}

	},

	QUEUED_DISPATCHER {

		/**
		 * Global event queue.
		 */
		private final ConcurrentLinkedQueue<EventListener> queue = new ConcurrentLinkedQueue<EventListener>();

		@Override
		public void dispatch(final Object event,
				Iterator<EventListener> eventListeners) {
			CheckObject.checkIsNull(event);
			CheckObject.checkIsNull(eventListeners);
			while (eventListeners.hasNext()) {
				queue.add(eventListeners.next());
			}
			EventListener eventListener;
			while ((eventListener = queue.poll()) != null) {
				eventListener.call(event);
			}
		}

	};

	@Override
	public abstract void dispatch(final Object event,
			Iterator<EventListener> eventListeners);

}
