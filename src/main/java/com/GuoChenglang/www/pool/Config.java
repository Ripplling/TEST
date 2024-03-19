package com.GuoChenglang.www.pool;


//连接池的配置类
public class Config {
    //初始链接个数
    private int init = 3;
    //最大链接个数
    private int max = 5;
    //url username password
    private String url = "jdbc:mysql:///hospital";
    private String username = "root";
    private String password = "123456";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Config() {
    }

    public Config(int init, int max) {
        this.init = init;
        this.max = max;
    }
}
