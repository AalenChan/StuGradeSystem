package com.majie.stugrade.ui.score;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.majie.stugrade.R;
import com.majie.stugrade.ui.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 */
public class ScoreMainActivity extends BaseActivity{

    private ScoreAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_main);

        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(mAdapter = new ScoreAdapter(ScoreMainActivity.this,R.layout.score_layout));
        initData();
    }

    private void initData(){
        List<ScoreEntity> entities = new ArrayList<>();
        for (int i = 0;i<10;i++) {
            ScoreEntity entity = new ScoreEntity();
            entity.setDate(System.currentTimeMillis());
            entity.setOtherScore(10);
            entity.setRestScore(13);
            entity.setStudyScore(12);
            entity.setExerciseScore(12);
            entities.add(entity);
            mAdapter.add(entity);
        }
    }

    private class ScoreAdapter extends ArrayAdapter<ScoreEntity>{

        private int resource;
        public ScoreAdapter(Context context, int resource) {
            super(context, resource);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ScoreViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ScoreViewHolder();
                convertView = LayoutInflater.from(ScoreMainActivity.this).inflate(resource,null);
                viewHolder.studyScore = (TextView) convertView.findViewById(R.id.study_score);
                viewHolder.exerciseScore = (TextView) convertView.findViewById(R.id.exercise_score);
                viewHolder.restScore = (TextView) convertView.findViewById(R.id.rest_score);
                viewHolder.otherScore = (TextView) convertView.findViewById(R.id.other_score);
                viewHolder.date = (TextView) convertView.findViewById(R.id.score_date);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ScoreViewHolder)convertView.getTag();
            }
            ScoreEntity curScore = getItem(position);
            if (curScore != null) {
                viewHolder.date.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA).format(new Date(curScore.getDate())));
                viewHolder.studyScore.setText(String.valueOf(curScore.getStudyScore()));
                viewHolder.exerciseScore.setText(String.valueOf(curScore.getExerciseScore()));
                viewHolder.restScore.setText(String.valueOf(curScore.getRestScore()));
                viewHolder.otherScore.setText(String.valueOf(curScore.getOtherScore()));
            }
            return convertView;
        }


        class ScoreViewHolder{
            TextView studyScore;
            TextView exerciseScore;
            TextView restScore;
            TextView otherScore;
            TextView date;
        }
    }
}
