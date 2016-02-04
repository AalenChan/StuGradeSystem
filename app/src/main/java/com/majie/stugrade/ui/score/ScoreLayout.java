package com.majie.stugrade.ui.score;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.majie.stugrade.R;

/**
 *
 * Created by Maniac on 16/2/4.
 */
public class ScoreLayout extends LinearLayout{

    private Context context;
    public ScoreLayout(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ScoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public ScoreLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(context).inflate(R.layout.score_layout,null);
    }
}
