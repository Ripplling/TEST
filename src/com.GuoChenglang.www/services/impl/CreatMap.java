package services.impl;

import controller.CreateMap;

import java.util.Timer;

public class CreatMap {
    public CreatMap() {
    }
    public void creatMap(){
        //每24小时生成就诊记录表
        Timer timer = new Timer();
        CreateMap createMap = new CreateMap();
        timer.schedule(createMap,0,86400000L);
    }
}
