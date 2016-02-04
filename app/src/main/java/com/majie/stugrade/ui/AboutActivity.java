package com.majie.stugrade.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.majie.stugrade.R;

/**
 *
 */
public class AboutActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);
    }
}
