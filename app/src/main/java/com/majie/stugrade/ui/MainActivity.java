package com.majie.stugrade.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.majie.stugrade.R;
import com.majie.stugrade.ui.kechengbiao.ContentMainActivity;
import com.majie.stugrade.ui.notes.activity.NotesMainActivity;
import com.majie.stugrade.ui.weather.activity.WeatherActivity;

public class MainActivity extends BaseActivity {
    private long exitTime = 0;

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
                showToast("分数");
            }
        });

        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

        notesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesMainActivity.class);
                startActivity(intent);
            }
        });

        introductionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("介绍");
            }
        });

        scoreRulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("评分规则");
            }
        });

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentMainActivity.class);
                startActivity(intent);
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
            case R.id.main_exit:
                showToast("exit");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
