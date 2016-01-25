package com.majie.stugrade.ui.weather.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.majie.stugrade.R;
import com.majie.stugrade.ui.weather.adapter.CityAdapter;
import com.majie.stugrade.ui.weather.utils.DataManager;
import com.majie.stugrade.ui.weather.utils.Utils;

import java.sql.SQLException;

import butterknife.Bind;

@SuppressLint("InlinedApi")
public class SelectCityActivity extends BaseActivity implements OnClickListener, TextWatcher,
        OnItemClickListener, OnEditorActionListener, BDLocationListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.gv_city_list)
    GridView gvCity;
    @Bind(R.id.et_input_city)
    EditText etInputCity;
    @Bind(R.id.iv_search)
    ImageView ivSearch;

    private String[] mCities;
    private Intent mIntent;
    private ProgressDialog mDialog;
    private String mCity;
    private LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        etInputCity.addTextChangedListener(this);
        etInputCity.setOnEditorActionListener(this);
        mCities = getResources().getStringArray(R.array.citys);
        gvCity.setAdapter(new CityAdapter(this, mCities));
        gvCity.setOnItemClickListener(this);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage(getResources().getString(R.string.locating));
        mDialog.setCanceledOnTouchOutside(false);

        initBaiduLocation();
    }

    private void initBaiduLocation() {
        // 声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        // 注册监听函数
        mLocationClient.registerLocationListener(this);
        // 设置定位参数
        setLocationOption();
    }

    /**
     * 设置定位参数。 定位模式（单次定位，定时定位），返回坐标类型，是否打开GPS等等。
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(24 * 60 * 60 * 1000);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
    }

    /**
     * 请求位置信息
     */
    private void requestLocation() {
        if (mLocationClient != null) {
            if (!mLocationClient.isStarted()) {
                mLocationClient.start();
            } else {
                mLocationClient.requestLocation();
            }
        } else {
            Log.d("LocSDK5", "locClient is null or not started");
        }
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        mDialog.cancel();
        if (location == null) {
            Toast.makeText(this, R.string.locate_fail, Toast.LENGTH_SHORT).show();
            return;
        }
        int code = location.getLocType();
        mCity = location.getCity();
        if (code == 161 && mCity != null) {
            // 定位成功
            mIntent = new Intent();
            mIntent.putExtra(WeatherActivity.CITY, mCity);
            setResult(RESULT_OK, mIntent);
            finish();
        } else {
            // 定位失败
            Toast.makeText(this, R.string.locate_fail, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        mCity = mCities[position];
        if (mCities[0].equals(mCity)) {
            if (!Utils.isNetworkAvailable(this)) {
                Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
                return;
            }
            mDialog.show();
            requestLocation();
        } else {
            mIntent = new Intent();
            mIntent.putExtra(WeatherActivity.CITY, mCity);
            setResult(RESULT_OK, mIntent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_search:
                mCity = etInputCity.getText().toString();
                mIntent = new Intent();
                mIntent.putExtra(WeatherActivity.CITY, mCity);
                setResult(RESULT_OK, mIntent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (etInputCity.length() == 0) {
                return true;
            }
            ivSearch.performClick();
        }
        return false;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (etInputCity.getText().toString().length() == 0) {
            ivSearch.setVisibility(View.GONE);
        } else {
            ivSearch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    protected void onStop() {
        mLocationClient.stop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        DataManager storageManager = DataManager.getInstance().setContext(this);
        try {
            if (storageManager.getData() == null) {
                WeatherActivity.mContext.finish();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finish();
    }
}
