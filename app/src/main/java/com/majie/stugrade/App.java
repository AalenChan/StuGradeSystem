package com.majie.stugrade;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.majie.stugrade.ui.baidu.service.LocationService;
import com.majie.stugrade.ui.baidu.service.WriteLog;

/**
 */
public class App extends Application {

    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        WriteLog.getInstance().init(); // 初始化日志
    }
}
