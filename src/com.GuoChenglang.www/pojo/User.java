package pojo;

public class User {
    private String username;
    private String password;
    private String phone;
    private String name;
    private String id;
    private int istrue;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", istrue=" + istrue +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIstrue() {
        return istrue;
    }

    public void setIstrue(int istrue) {
        this.istrue = istrue;
    }

    public User(String username, String password, String phone, String name, String id, int istrue) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.id = id;
        this.istrue = istrue;
    }
}
