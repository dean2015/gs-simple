package org.gsimple.event.test.base;

import org.gsimple.event.DefaultEventBus;
import org.gsimple.event.EventBus;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        // Concrete an eventbus
        EventBus eventBus = new DefaultEventBus(TestMethod.class);
        // Register eventListener
        eventBus.register(new EventListener());
        // Concrete an event
        Event event = new Event();
        event.setData("Hello world!");
        // publish the event
        eventBus.publish(event);
    }

}
