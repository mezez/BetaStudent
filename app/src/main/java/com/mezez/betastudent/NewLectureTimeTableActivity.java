package com.mezez.betastudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewLectureTimeTableActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBHelper mDbHelper;
    EditText day, course, start_time, end_time, venue;
    String xday, xcourse, xstart_time, xend_time, xvenue;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lecture_time_table);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New Lecture");

        mDbHelper = new DBHelper(this);

        Spinner spinner = (Spinner) findViewById(R.id.new_lecture_day);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //day = (EditText) findViewById(R.id.new_lecture_day);
        course = (EditText) findViewById(R.id.new_lecture_course);
        start_time = (EditText) findViewById(R.id.new_lecture_start_time);
        end_time = (EditText) findViewById(R.id.new_lecture_end_time);
        venue = (EditText) findViewById(R.id.new_lecture_venue);
        save = (Button) findViewById(R.id.new_lecture_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xday = day.getText().toString().toLowerCase();
                xcourse = course.getText().toString();
                xstart_time = start_time.getText().toString();
                xend_time = end_time.getText().toString();
                xvenue =  venue.getText().toString();

                if (xday != "" && xcourse != "" && xstart_time != "" && xend_time != ""){
                    AddData(xday, xcourse, xstart_time, xend_time, xvenue);
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        xday = parent.getItemAtPosition(pos).toString().toLowerCase();
        setXday(xday);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        xday = "monday";
        setXday(xday);
    }

    private void AddData(String xday, String xcourse, String xstart_time, String xend_time, String xvenue) {
        boolean insertData = mDbHelper.addLecture(xday, xcourse, xstart_time, xend_time, xvenue);
        if(insertData){
            //day.setText("");
            course.setText("");
            start_time.setText("");
            end_time.setText("");
            venue.setText("");
            toastMessage("Lecture saved successfully");
        }else {
            toastMessage("Error saving lecture");
        }
    }

    public void setXday(String xday){
        this.xday = xday;

    }

    public String getXday(){
        return this.xday;
    }

    private void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
