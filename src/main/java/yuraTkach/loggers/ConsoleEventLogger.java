package yuraTkach.loggers;

import yuraTkach.events.Event;

public class ConsoleEventLogger implements EventLogger{

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
