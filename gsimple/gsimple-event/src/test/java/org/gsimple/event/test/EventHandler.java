package org.gsimple.event.test;

public class EventHandler {

	@TestMethod
	private void handleEvent4(ChildEvent e) {
		e.counter.getAndAdd(1);
		if (System.currentTimeMillis() - e.time < 1000) {
			TestMain.eventBus.publish(e);
		} else {
			System.out.println(e.counter);
			System.exit(0);
		}
	}

}
