package yuraTkach.events;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;
    public Event(int id, Date date, String msg) {
        this.id = id;
        this.date = date;
        this.msg = msg;
    }
    public Event(int id, String msg, Date date) {
        this.id = AUTO_ID.incrementAndGet();
        this.msg = msg;
        this.date = date;
    }
    public Event(Date date, DateFormat df) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.dateFormat = df;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
    public static void initAutoId(int id) {
        AUTO_ID.set(id);
    }
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
