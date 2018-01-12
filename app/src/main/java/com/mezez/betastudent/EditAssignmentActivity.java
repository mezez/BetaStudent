package com.mezez.betastudent;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class EditAssignmentActivity extends AppCompatActivity {
    public static final String ID = "com.mezez.betastudent.ID";
    DBHelper mDbHelper;
    EditText title, description, datePicker;
//    DatePicker date;
    TimePicker time;
    private Button cancel, save;
    int RQS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        //get the intent that started this activity and extract the string
        Intent intent = getIntent();

        //there is a plus one at the end of this line because the listview id starts at index 0
        final long id = intent.getLongExtra(My_Assignments.ID, 0)  ;

        //Capture the layout textview and set the string as its text
        title = (EditText) findViewById(R.id.edit_assignment_title);
        description = (EditText) findViewById(R.id.edit_assignment_description);
        datePicker = (EditText) findViewById(R.id.edit_assignment_datePicker);
        time = (TimePicker) findViewById(R.id.edit_assignment_timePicker);
        cancel = (Button) findViewById(R.id.edit_assignment_cancel);
        save = (Button) findViewById(R.id.edit_assignment_save);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditAssignmentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //title.setText(" "+ id +"");

        //get the data from the helper class
        mDbHelper = new DBHelper(this);
        Cursor data = mDbHelper.getItem(id);
        if (data.getCount() < 1){
            toastMessage("Error retrieving assignment details");
        }else if (data.getCount() > 0) {
            //toastMessage( "data");
            while (data.moveToNext()){
                String title_data = data.getString(1);
                String description_data = data.getString(2);
                //String date_data = data.getString(3);
                RQS = data.getInt(5);


               // int year = Integer.parseInt(date_data.substring(0,4));
                //int month = Integer.parseInt(date_data.substring(5,7));
                //int day = Integer.parseInt(date_data.substring(8,10));

                //String time_data = data.getString(4);

                // toastMessage( title_data);

                title.setText(title_data);
                description.setText(description_data);
                //date.updateDate( year, month,day);

                save.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        cancelAlarm(RQS);
                        String new_title = title.getText().toString();
                        String new_description = description.getText().toString();
                        String new_date = datePicker.getText().toString();
                        String new_time = "" + time.getHour()+ ":" + time.getMinute()+"";
                        Random rand = new Random();
                        int new_RQS = rand.nextInt(1000);

                        if(new_title.length() != 0 && new_description.length() != 0){

                            if (mDbHelper.updateAssignment(new_title, new_description, new_time, new_date, new_RQS, id)){
                                Calendar current = Calendar.getInstance();
                                myCalendar.set(Calendar.HOUR_OF_DAY, time.getHour());
                                myCalendar.set(Calendar.MINUTE, time.getMinute());

                                if(myCalendar.compareTo(current) <= 0){
                                    //The set Date/Time already passed
                                    Toast.makeText(getApplicationContext(),
                                            "Invalid Date/Time",
                                            Toast.LENGTH_LONG).show();
                                }else{

                                    setAlarm(myCalendar, new_RQS);
                                }
                                //close the current screem and open the edit screen
                                Intent i = new Intent(view.getContext(), My_Assignments.class);
                                finish();
                                startActivity(i);
                                toastMessage("Assignmnent updated successfully");
                            }else {
                                //error updating the assignment
                                toastMessage("Oops, an Error occured while updating the assignment");
                            }



                        }else {
                            toastMessage("You must have a title and a description before you save");
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent i = new Intent(v.getContext(), AssignmentDetailActivity.class);
                        finish();
                        // startActivity(i);
                    }
                });

            }



        }



    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        Calendar mySecCalendar = Calendar.getInstance();

        datePicker.setText(sdf.format(mySecCalendar.getTime()));
    }

    private void setAlarm(Calendar targetCal, int RQS){



        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        toastMessage("Alarm/Reminder set for " + targetCal);
        String intentString = pendingIntent.toString();

        //toastMessage(intentString);

//        ComponentName receiver = new ComponentName(getBaseContext(), AlarmReceiver.class);
//        PackageManager pm = getBaseContext().getPackageManager();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);

//        AlarmManager alarmMgr = (AlarmManager)getBaseContext().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
//        PendingIntent alarmIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);

// Set the alarm to start at 8:30 a.m.
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 8);
//        calendar.set(Calendar.MINUTE, 30);

    }

    private void cancelAlarm(int RQS){
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        AlarmManager alarmMgr = (AlarmManager)getBaseContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS, intent, 0);
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(pendingIntent);
            pendingIntent.cancel();

            ComponentName receiver = new ComponentName(getBaseContext(), AlarmReceiver.class);
            PackageManager pm = getBaseContext().getPackageManager();

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }

    }



    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
