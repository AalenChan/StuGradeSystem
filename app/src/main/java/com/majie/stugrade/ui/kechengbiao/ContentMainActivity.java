package com.majie.stugrade.ui.kechengbiao;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.majie.stugrade.R;
import com.majie.stugrade.ui.BaseActivity;
import com.majie.stugrade.ui.kechengbiao.data.DataManager;
import com.majie.stugrade.ui.kechengbiao.data.bean.Course;
import com.majie.stugrade.ui.kechengbiao.data.bean.Notebook;
import com.majie.stugrade.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Created by Maniac on 16/1/10.
 */
public class ContentMainActivity extends BaseActivity{

    private ArrayList<LinearLayout> linearLayoutList ;
    //分别表示周一到周日
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private LinearLayout ll5;
    private LinearLayout ll6;
    private LinearLayout ll7;
    private List<Course> courseList;

    private int colors[] = {
            Color.rgb(0xee,0xff,0xff),
//			Color.rgb(200,232,145),
//			Color.rgb(103,192,210),
//			Color.rgb(239,61,97),
//			Color.rgb(167,150,194),
//			Color.rgb(247,203,44),
//			Color.rgb(86,87,186),
//			Color.rgb(21,69,138),
//			Color.rgb(89,181,183),
//			Color.rgb(206,73,51),
//			Color.rgb(223,162,35),
//			Color.rgb(153,182,56),
//			Color.rgb(114,10,102)

            Color.rgb(237,85,101),
            Color.rgb(218,68,63),
            Color.rgb(252,110,81),
            Color.rgb(233,87,63),
            Color.rgb(246,187,66),
            Color.rgb(140,193,82),
            Color.rgb(160, 212, 104),
            Color.rgb(72,207,173),
            Color.rgb(55,188,155),
            Color.rgb(74,137,220),
            Color.rgb(236,135,192),
            Color.rgb(215,112,173),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);

        ll1 = (LinearLayout)findViewById(R.id.ll1);
        ll2 = (LinearLayout)findViewById(R.id.ll2);
        ll3 = (LinearLayout)findViewById(R.id.ll3);
        ll4 = (LinearLayout)findViewById(R.id.ll4);
        ll5 = (LinearLayout)findViewById(R.id.ll5);
        ll6 = (LinearLayout)findViewById(R.id.ll6);
        ll7 = (LinearLayout)findViewById(R.id.ll7);

        linearLayoutList = new ArrayList<LinearLayout>();

        linearLayoutList.add(ll1);
        linearLayoutList.add(ll2);
        linearLayoutList.add(ll3);
        linearLayoutList.add(ll4);
        linearLayoutList.add(ll5);
        linearLayoutList.add(ll6);
        linearLayoutList.add(ll7);

        courseList = new ArrayList<Course>();
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("课程表");
        refreshCurriculumByDB();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 设置课程的方法
     * @param ll
     * @param course 课程
     */
    void setClass(LinearLayout ll, Course course)
    {
        View view = LayoutInflater.from(this).inflate(R.layout.course_item, null);
        view.setMinimumHeight(dip2px(this,course.getClasses() * 48));
        view.setBackgroundColor(colors[course.getColor()]);
        ((TextView)view.findViewById(R.id.course_item_title)).setText(course.getTitle());
        ((TextView)view.findViewById(R.id.course_item_place)).setText(course.getPlace());
        //((TextView)view.findViewById(R.id.course_item_last)).setText(String.valueOf(course.getWeekly()));
        ((TextView)view.findViewById(R.id.course_item_teacher)).setText(String.valueOf(course.getTeacher()));

        //为课程View设置点击的监听器
        view.setOnClickListener(new OnClickClassListener());
        TextView blank1 = new TextView(this);
        TextView blank2 = new TextView(this);
        view.setId(course.getId());
        blank1.setHeight(dip2px(this,course.getClasses()));
        blank2.setHeight(dip2px(this,course.getClasses()));
        ll.addView(blank1);
        ll.addView(view);
        ll.addView(blank2);
    }
    /**
     * 设置无课（空白）
     * @param ll
     * @param classes 无课的节数（长度）
     * @param color
     * @param course
     */
    void setNoClass(LinearLayout ll,int classes, int color,Course course)
    {
        TextView blank = new TextView(this);
        if(color == 0)
            blank.setMinHeight(dip2px(this,classes * 48));
        blank.setBackgroundColor(colors[color]);
        TextView blank1 = new TextView(this);
        TextView blank2 = new TextView(this);
        blank1.setHeight(dip2px(this,classes));
        blank2.setHeight(dip2px(this,classes));
        ll.addView(blank2);
        ll.addView(blank);
        ll.addView(blank1);
        blank.setTag(course);
        blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                Course c = (Course) textView.getTag();
                Intent mIntent = new Intent(ContentMainActivity.this,CourseActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("course", c);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });

    }
    //点击课程的监听器
    class OnClickClassListener implements View.OnClickListener {

        public void onClick(View v) {
            showShort(ContentMainActivity.this,"点击课程监听器");
//            int id = v.getId();
//            final Course c = DataManager.getDataManager(ContentMainActivity.this).getCourseByID(id);
//            final String [] menu = getResources().getStringArray(R.array.course_menu);
//            UIUtils.showAlertWindowWithList(this, getResources().getString(R.string.course_menu_title), new ArrayAdapter<String>(this, R.layout.alert_window_list_text_item, menu), new AlertWindow.OnListItemClickListener() {
//                @Override
//                public void onItemClick(View view, int which) {
//                    UIUtils.dismissAlertWindow();
//                    switch (which) {
//                        case 0:
//                            break;
//                        case 1:
//                            break;
//                        case 2:
//                            Intent intent = new Intent(ContentMainActivity.this,CourseActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("course", c);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
//                            break;
//                        case 3:
//                            DataManager.getDataManager(ContentMainActivity.this).deleteCourse(c);
//                            refreshCurriculumByDB();
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            });
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);} /** * 根据手机的分辨率从 px(像素) 的单位 转成为 dp */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);}


    private void refreshCurriculumByDB(){

        for(int i = 1; i <= 7; i++){
            int count = linearLayoutList.get(i-1).getChildCount();
            // 这里不能清空linearLayout，因为要保留linearLayout上面MON……这些TextView。
            while(count > 2)
                linearLayoutList.get(i-1).removeViewAt(--count);
        }

        this.courseList = DataManager.getDataManager(ContentMainActivity.this).getAllCourse();
        setTimetableByDb();
        if(CollectionUtil.isListEmpty(courseList))
            setAllNoClass();
        else
            orderClasses();

    }

    private void orderClasses() {
        for(int i = 1; i <= 7; i++){
            for(int j = 1; j <= 14; j++){
                if(DataManager.getDataManager(this).getTimetable()[i-1][j-1] == -1){
                    //				if(course == null){
                    Course blankCourse = new Course();
                    blankCourse.setWeekly(i);
                    blankCourse.setStartClass(j);
                    blankCourse.setBlank(true);
                    setNoClass(linearLayoutList.get(i-1),1,0,blankCourse);
                }
                else{
                    Course course = getCourceByIDFromlist(DataManager.getDataManager(this).getTimetable()[i-1][j-1]);
                    setClass(linearLayoutList.get(i-1), course);
                    j += (course.getClasses()-1);
                }
            }
        }
    }

    //全部设成无课
    private void setAllNoClass(){
        for(int i = 1; i <= 7; i++){
            for(int j = 1; j <= 14; j++){
                Course blankCourse = new Course();
                blankCourse.setWeekly(i);
                blankCourse.setStartClass(j);
                blankCourse.setBlank(true);
                setNoClass(linearLayoutList.get(i-1),1,0,blankCourse);
            }
        }
    }

    private Course getCourceByWeekAndClass(int week,int _class){
        for (Iterator<Course> courseIterator = courseList.iterator(); courseIterator.hasNext();) {
            Course course = courseIterator.next(); // line 1
            if(course.getWeekly() == week && (course.getStartClass() <= _class) && ((course.getClasses()+course.getStartClass()-1) >= _class))
                return course;
        }
        return null;
    }

    private Course getCourceByIDFromlist(int id){
        for (Iterator<Course> courseIterator = courseList.iterator(); courseIterator.hasNext();) {
            Course course = courseIterator.next(); // line 1
            if(course.getId() == id)
                return course;
        }
        return null;
    }

    private void setTimetableByDb(){
        makeTimeTableToZero();
        for (Iterator<Course> courseIterator = courseList.iterator(); courseIterator.hasNext();) {
            Course course = courseIterator.next(); // line 1
            if(course != null){
                for(int i = 0; i < course.getClasses(); i++){
                    DataManager.getDataManager(this).getTimetable()[course.getWeekly()-1][course.getStartClass()+i-1] = course.getId();
                }
            }
        }
    }

    private void makeTimeTableToZero(){
        DataManager.getDataManager(this).makeTimeTableToZero();
    }
}
