package pojo;

import java.sql.Date;

public class Patient {
    private String name;
    private String ID;
    private String yourdoc;
    private Date date;
    private String room;
    private int complete;


    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", yourdoc='" + yourdoc + '\'' +
                ", date=" + date +
                ", room='" + room + '\'' +
                ", complete=" + complete +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public Patient(String name, String ID, String yourdoc, Date date, String room, int complete) {
        this.name = name;
        this.ID = ID;
        this.yourdoc = yourdoc;
        this.date = date;
        this.room = room;
        this.complete = complete;
    }

    public Patient() {
    }
}
