# gsimple-event

gsimple-event is an event bus based on method reflection of java class.

First of all, thanks for guava. The basic idea of gsimple-event is from eventbus of guava. I pulled the main idea out of guava, and made it into gs-simple project.

There are some logic different from guava as well, and I think they make the eventbus easy to understand for myself.


### How to use it?

1 Definde an event class.

```
public class Event {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
   
}
```

2 Define an annotation of event listener.

```
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethod {

}
```

3 Define an event listener class.

```
public class EventListener {

    @TestMethod
    private void handle(Event e) {
        //TODO: deal with e.getData()
        System.out.println(e.getData());
    }

}
```

4 Test class
```
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
```