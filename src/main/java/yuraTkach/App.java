package yuraTkach;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yuraTkach.loggers.EventLogger;

public class App {
    private Client client;

    private EventLogger eventLogger;

    public App() {
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");
        Event event1 = (Event)context.getBean("event");
        Thread.sleep(1000);
        Event event2 = (Event)context.getBean("event");

        app.logEvent("Some message for 1",event1);
        app.logEvent("Some message for 2",event2);
    }
    private void logEvent(String msg, Event event) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);


    }

}
