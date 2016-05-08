package org.gsimple.event.test.base;

public class EventListener {

    @TestMethod
    private void handle(Event e) {
        //TODO: deal with e.getData()
        System.out.println(e.getData());
    }

}
