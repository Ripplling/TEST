package pojo;

public class Doctor {
    private String name;
    private String room;
    private int isfree;

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", isfreee=" + isfree +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getIstrue() {
        return isfree;
    }

    public void setIstrue(int istrue) {
        this.isfree = istrue;
    }

    public Doctor() {
    }

    public Doctor(String name, String room, int istrue) {
        this.name = name;
        this.room = room;
        this.isfree = istrue;
    }
}
