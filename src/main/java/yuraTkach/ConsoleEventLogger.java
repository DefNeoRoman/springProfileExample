package yuraTkach;

import yuraTkach.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger{

    public void logEvent(String mesage) {
        System.out.println(mesage);
    }
}
