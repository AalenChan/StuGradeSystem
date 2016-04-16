package com.majie.stugrade.ui;

import com.baidu.mapapi.model.LatLng;

/**
 */
public class Constants {

    //西区宿舍
    public static final LatLng westRestLatLng = new LatLng(117.058842,39.245593);
    //东区宿舍
    public static final LatLng eastRestLatLng = new LatLng(117.072074,39.247517);
    //运动
    public static final LatLng sportLatLng = new LatLng(117.066343,39.246385);
    //教学楼
    public static final LatLng studyMainLatLng = new LatLng(117.066208,39.242585);
    //学院楼
    public static final LatLng studyLatLng = new LatLng(117.063352,39.240294);

    public static final int REST_TIME = 1;
    public static final int SPORT_TIME = 2;
    public static final int STUDY_TIME = 3;
    public static final int OTHER_TIME = 4;


    public static final String IP = "http://192.168.1.104:8080/StuSystem/";
}
