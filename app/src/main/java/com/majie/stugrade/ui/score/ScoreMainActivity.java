package com.majie.stugrade.ui.score;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.majie.stugrade.R;
import com.majie.stugrade.ui.BaseActivity;
import com.majie.stugrade.ui.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
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
        new getDataAsync().execute("10000");
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
                viewHolder.totalScore = (TextView) convertView.findViewById(R.id.total_score);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ScoreViewHolder)convertView.getTag();
            }
            ScoreEntity curScore = getItem(position);
            if (curScore != null) {
                viewHolder.date.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA).format(new Date(curScore.getDate())));
                viewHolder.studyScore.setText(String.valueOf(curScore.getStudyTime()));
                viewHolder.exerciseScore.setText(String.valueOf(curScore.getSportTime()));
                viewHolder.restScore.setText(String.valueOf(curScore.getRestTime()));
                viewHolder.otherScore.setText(String.valueOf(curScore.getOtherTime()));
                viewHolder.totalScore.setText("总分：" + curScore.getScore());
            }
            return convertView;
        }


        class ScoreViewHolder{
            TextView studyScore;
            TextView exerciseScore;
            TextView restScore;
            TextView otherScore;
            TextView date;
            TextView totalScore;
        }
    }

    private class getDataAsync extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder json = new StringBuilder();

            StringBuilder url = new StringBuilder();
            url.append(Constants.IP).append("DataServlet?account=").append(params[0]);
            try {
                URL oracle = new URL(url.toString());
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                in.close();
                String StrJson = json.toString();
                System.out.println("原始数据:");
                System.out.println(StrJson);
                return StrJson;
            } catch (Throwable e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (!TextUtils.isEmpty(s)) {
                JSONArray.parse(s);
                List<ScoreEntity> list = JSON.parseArray(s, ScoreEntity.class);
                for (ScoreEntity scoreEntity : list) {
                    mAdapter.add(scoreEntity);
                }
            }
            super.onPostExecute(s);
        }
    }
}
