package com.majie.stugrade.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.majie.stugrade.R;
import com.majie.stugrade.ui.kechengbiao.ContentMainActivity;
import com.majie.stugrade.ui.notes.activity.NotesMainActivity;
import com.majie.stugrade.ui.weather.activity.WeatherActivity;


public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private static final int animateTime = 500;

    private Button scoreBtn;
    private Button weatherBtn;
    private Button notesBtn;
    private Button introductionBtn;
    private Button scoreRulesBtn;
    private Button courseBtn;

    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
                showToast("分数");
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
                showToast("模式设置");
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
}
