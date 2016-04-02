package com.majie.stugrade.ui;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.majie.stugrade.App;
import com.majie.stugrade.R;
import com.majie.stugrade.model.BaseResponse;
import com.majie.stugrade.ui.baidu.service.LocationService;
import com.majie.stugrade.ui.kechengbiao.ContentMainActivity;
import com.majie.stugrade.ui.notes.activity.NotesMainActivity;
import com.majie.stugrade.ui.score.ScoreMainActivity;
import com.majie.stugrade.ui.weather.activity.WeatherActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private static final int animateTime = 500;

    private Button scoreBtn;
    private Button weatherBtn;
    private Button notesBtn;
    private Button introductionBtn;
    private Button scoreRulesBtn;
    private Button courseBtn;

    private TextView locationResult;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        locationResult = (TextView) findViewById(R.id.location);

        scoreBtn = (Button) findViewById(R.id.main_score);
        weatherBtn = (Button) findViewById(R.id.main_weather);
        notesBtn = (Button) findViewById(R.id.main_notes);
        introductionBtn = (Button) findViewById(R.id.main_introduction);
        scoreRulesBtn = (Button) findViewById(R.id.main_scoreRule);
        courseBtn = (Button) findViewById(R.id.main_course);

        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(scoreBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ScoreMainActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(weatherBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(notesBtn);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, NotesMainActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        introductionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(introductionBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        scoreRulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(scoreRulesBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ScoreRulesActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(animateTime).playOn(courseBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ContentMainActivity.class);
                        startActivity(intent);
                    }
                }, animateTime);
            }
        });

        if (getIntent().hasExtra("userID")) {
            userID = getIntent().getIntExtra("userID", 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.about:
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                getSharedPreferences("stuGrade", MODE_PRIVATE).edit().putString("user_id","").commit();
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private LocationService locationService;

    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = ((App) getApplication()).locationService;
        //获取locationService实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationService实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }

        locationService.start();
    }

    /*****
     * copy function to you project
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                //收到定位信息后
                LatLng newLatLng = new LatLng(location.getLatitude(),location.getLongitude());

                int type;

                if (SpatialRelationUtil.isCircleContainsPoint(Constants.westRestLatLng, 200, newLatLng)) {
                    type = 1;
                    Toast.makeText(MainActivity.this, "休息时间", Toast.LENGTH_SHORT).show();
                } else if (SpatialRelationUtil.isCircleContainsPoint(Constants.eastRestLatLng, 200, newLatLng)) {
                    type = 1;
                    Toast.makeText(MainActivity.this, "休息时间", Toast.LENGTH_SHORT).show();
                } else if (SpatialRelationUtil.isCircleContainsPoint(Constants.sportLatLng, 400, newLatLng)) {
                    type = 2;
                    Toast.makeText(MainActivity.this, "运动时间", Toast.LENGTH_SHORT).show();
                } else if (SpatialRelationUtil.isCircleContainsPoint(Constants.studyMainLatLng, 150, newLatLng)) {
                    type = 3;
                    Toast.makeText(MainActivity.this, "学习时间", Toast.LENGTH_SHORT).show();
                } else if (SpatialRelationUtil.isCircleContainsPoint(Constants.studyLatLng, 100, newLatLng)) {
                    type = 3;
                    Toast.makeText(MainActivity.this, "学习时间", Toast.LENGTH_SHORT).show();
                } else {
                    type = 4;
                    Toast.makeText(MainActivity.this, "其他时间", Toast.LENGTH_SHORT).show();
                }

                StringBuilder sb = new StringBuilder(256);
                sb.append("time : ");

                new UpdateLocation().execute(location.getLatitude() + "," + location.getLongitude(),String.valueOf(type));

                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlongitude : ");
                sb.append(location.getLongitude());
                sb.append("\nDescribe: ");
                sb.append(location.getLocationDescribe());
                logMsg(sb.toString());
            } else {
                logMsg("定位失败");
            }
        }

    };

    public void logMsg(String str) {
        try {
            if (locationResult != null)
                locationResult.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    private class UpdateLocation extends AsyncTask<String,Void,String> {

        @Override
        protected void onPostExecute(String s) {
            if (!TextUtils.isEmpty(s)) {
                BaseResponse response = JSON.parseObject(s, BaseResponse.class);
                if (response.getCode() == 200) {
                    //成功
                    Log.d("MainActivity","成功");
                } else {
                    //失败
                    Log.d("MainActivity",response.getMessage());
                }
            } else {
                //失败
                Log.d("MainActivity","失败");
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String loc = params[0];
            String type = params[1];

            StringBuilder json = new StringBuilder();
            try {
                URL url = new URL("http://192.168.1.104:8080/StuSystem/HandleLocationServlet?location=" + loc + "&type=" + type);
                URLConnection yc = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return json.toString();
        }
    }
}
