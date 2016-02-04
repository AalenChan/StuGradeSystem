package com.majie.stugrade.ui;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.location.LocationClientOption;
import com.majie.stugrade.App;
import com.majie.stugrade.R;

/**
 */
public class PreferenceActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_pref);



    }

    private void setOption(boolean isOpenGps){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(isOpenGps);
        option.setAddrType("detail");
        option.setCoorType("gcj02");
        option.setScanSpan(5000);
        option.disableCache(true);//禁止启用缓存定位
        ((App) getApplication()).locationService.setLocationOption(option);
    }
}
