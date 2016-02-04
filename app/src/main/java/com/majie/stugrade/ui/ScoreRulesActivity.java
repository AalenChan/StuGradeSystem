package com.majie.stugrade.ui;

import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.majie.stugrade.R;

import java.io.File;
import java.io.IOException;

/**
 */
public class ScoreRulesActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_rule);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfview);
        try {
            getAssets().open("rule.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfView.fromAsset("rule.pdf")
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int i) {

                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int i, int i1) {

                    }
                })
                .load();
    }
}
