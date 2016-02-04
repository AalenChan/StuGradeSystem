package com.majie.stugrade.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.majie.stugrade.R;


@SuppressLint("InlinedApi")
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getSharedPreferences("stuGrade", MODE_PRIVATE);
        boolean loggedIn = TextUtils.isEmpty(sp.getString("user_id", ""));

        if (loggedIn) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            }, 2500);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
