package pojo;

public class Doctor {
    private String name;
    private String room;
    private int istrue;

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                ", istrue=" + istrue +
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
        return istrue;
    }

    public void setIstrue(int istrue) {
        this.istrue = istrue;
    }

    public Doctor() {
    }

    public Doctor(String name, String room, int istrue) {
        this.name = name;
        this.room = room;
        this.istrue = istrue;
    }
}
