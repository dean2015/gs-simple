# gsimple-event

gsimple-event is an event bus based on method reflection of java class.

First of all, thanks for guava. The basic idea of gsimple-event is from eventbus of guava. I pulled the main idea out of guava, and made it into gs-simple project.

There are some logic different from guava as well, and I think they make the eventbus easy to understand for myself.


How to use it?

There are 2 class should be cared. One is AbstractEventBus.class, and the other one is EventBus.class.

EventBus.class is the interface of eventbus, and AbstractEventBus wraps some basic logic of interface EventBus.class.

1. From EventBus.class, the user might know that there are 3 method to operate on a eventbus. They are 'register', 'unregister', and 'publish'

A 'register' opersion is to register a class with the method which to listen the event, and the event should be the only parameter of the method. In order to tag the method, an annotation is defined.

A 'unregister' operation is to unregister a class which has been reigister before.

A 'publish' operation is the key operation of eventbus, it could be used everywhere. This operation is to tell the event bus, some event is on, and the listeners should catch the event and trigger the methods.


2. The AbstractEventBus.class is a basic class that the users could extend by themselves and provide a threadpool and an annotation of class method to the AbstractEventBus.class.

Generally speaking, an event bus is a singleton. So the users may make the eventbus which extends AbstractEventBus.class as a singleton.


Also, a simple test has been provided in test package, please refer them and use the eventbus.
