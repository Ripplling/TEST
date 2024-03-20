package pojo;

import java.sql.Date;

public class Patient {
    private String name;
    private String yourdoc;
    private Date date;
    private String room;

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", yourdoc='" + yourdoc + '\'' +
                ", date=" + date +
                ", room='" + room + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYourdoc() {
        return yourdoc;
    }

    public void setYourdoc(String yourdoc) {
        this.yourdoc = yourdoc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Patient(String name, String yourdoc, Date date, String room) {
        this.name = name;
        this.yourdoc = yourdoc;
        this.date = date;
        this.room = room;
    }

    public Patient() {
    }
}
