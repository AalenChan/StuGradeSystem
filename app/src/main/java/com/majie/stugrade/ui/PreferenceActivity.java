package com.majie.stugrade.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.baidu.location.LocationClientOption;
import com.majie.stugrade.App;
import com.majie.stugrade.R;

/**
 */
public class PreferenceActivity extends Activity{
    private RadioGroup radioGroup;
    private LinearLayout high_AccuracyLL;
    private LinearLayout battery_SavingLL;
    private LinearLayout device_SensorsLL;


    private CheckBox high_AccuracyCB;
    private CheckBox battery_SavingCB;
    private CheckBox device_SensorsCB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_pref);

        high_AccuracyLL = (LinearLayout) findViewById(R.id.high_AccuracyLL);
        battery_SavingLL = (LinearLayout) findViewById(R.id.battery_SavingLL);
        device_SensorsLL = (LinearLayout) findViewById(R.id.device_SensorsLL);

        high_AccuracyCB = (CheckBox) findViewById(R.id.high_Accuracy);
        battery_SavingCB = (CheckBox) findViewById(R.id.battery_Saving);
        device_SensorsCB = (CheckBox) findViewById(R.id.device_Sensors);

        int i = getSharedPreferences("setting",MODE_PRIVATE).getInt("locationSettings",0);
        switch (i) {
            case 1:
                clickHighAccuracy();
                break;
            case 2:
                clickBatterySaving();
                break;
            case 3:
                clickDeviceSensors();
                break;
        }

        high_AccuracyLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHighAccuracy();
            }
        });

        battery_SavingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBatterySaving();
            }
        });

        device_SensorsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeviceSensors();
            }
        });

    }

    private void clickDeviceSensors() {
        high_AccuracyCB.setChecked(false);
        battery_SavingCB.setChecked(false);
        device_SensorsCB.setChecked(true);
        setOption(true, LocationClientOption.LocationMode.Device_Sensors,3);
    }

    private void clickBatterySaving() {
        high_AccuracyCB.setChecked(false);
        battery_SavingCB.setChecked(true);
        device_SensorsCB.setChecked(false);
        setOption(true, LocationClientOption.LocationMode.Battery_Saving,2);
    }

    private void clickHighAccuracy() {
        high_AccuracyCB.setChecked(true);
        battery_SavingCB.setChecked(false);
        device_SensorsCB.setChecked(false);
        setOption(true, LocationClientOption.LocationMode.Hight_Accuracy,1);
    }


    private void setOption(boolean isOpenGps,LocationClientOption.LocationMode mode,int i){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(mode);
        option.setOpenGps(isOpenGps);
        option.setAddrType("detail");
        option.setCoorType("gcj02");
        option.setScanSpan(5000);
        option.disableCache(true);//禁止启用缓存定位
        ((App) getApplication()).locationService.setLocationOption(option);

        getSharedPreferences("setting",MODE_PRIVATE).edit().putInt("locationSettings",i).apply();
    }
}
