package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LectureDetailActivity extends AppCompatActivity {
    Long id;
    DBHelper mDbHelper;
    TextView day, course, start_time, end_time, venue;
    String browser_url;
    public static final String ID = "com.mezez.betastudent.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_detail);
        getSupportActionBar().setTitle("Lecture Details");
        Intent intent = getIntent();

        id = intent.getLongExtra(LectureTimeTableDetailActivity.ID, 0);
        //toastMessage(""+id+"");

        day = (TextView) findViewById(R.id.lecture_detail_day);
        course = (TextView) findViewById(R.id.lecture_detail_course);
        start_time = (TextView) findViewById(R.id.lecture_detail_start_time);
        end_time = (TextView) findViewById(R.id.lecture_detail_end_time);
        venue = (TextView) findViewById(R.id.lecture_detail_venue);

        mDbHelper = new DBHelper(this);
        Cursor data = mDbHelper.getLectureItem(id);
        if (data.getCount() < 1){
            toastMessage("Error retrieving lecture details");
        }else if (data.getCount() > 0) {
            //toastMessage( "data");
            while (data.moveToNext()){
                String day_data = data.getString(1);
                String course_data = data.getString(2);
                String start_time_data = data.getString(3);
                String end_time_data = data.getString(4);
                String venue_data = data.getString(5);

                day.setText("Day: " + day_data);
                course.setText("Course: " + course_data);
                start_time.setText("Start time: " + start_time_data);
                end_time.setText("End time: " + end_time_data);
                venue.setText("Venue: " + venue_data);
            }



        }

    }

    public void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
