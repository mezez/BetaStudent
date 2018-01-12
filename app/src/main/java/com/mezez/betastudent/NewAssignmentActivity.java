package com.mezez.betastudent;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NewAssignmentActivity extends AppCompatActivity {
    DBHelper mDbHelper;
    private EditText xtitle, xdescription, xdatepicker;
    private DatePicker xdate;
    private TimePicker xtime;
    private Button save, cancel;

//    final static int RQS_1 = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDbHelper = new DBHelper(this);



        save = (Button) findViewById(R.id.my_assignment_save);
        cancel = (Button) findViewById(R.id.my_assignment_cancel);
        xtitle = (EditText) findViewById(R.id.my_assignment_title);
        xdescription = (EditText) findViewById(R.id.my_assignment_description);
        xdatepicker = (EditText) findViewById(R.id.my_assignment_datepicker);
//        xdate = (DatePicker) findViewById(R.id.my_assignment_datePicker);
        xtime = (TimePicker) findViewById(R.id.my_assignment_timePicker);

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar);
            }

        };

        xdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewAssignmentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String title = xtitle.getText().toString();
                String description = xdescription.getText().toString();
                //String date = xdate.toString();
                String date = xdatepicker.getText().toString();
                String time = "" + xtime.getHour()+ ":" + xtime.getMinute()+"";
                Random rand = new Random();
                int RQS = rand.nextInt(1000);
                //save the alarm id to the alarm for the view and edit

                if(xtitle.length() != 0 && xdescription.length() != 0){

                    Calendar current = Calendar.getInstance();
//
//                    Calendar cal = Calendar.getInstance();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        cal.set(xdate.getYear(),
//                                xdate.getMonth(),
//                                xdate.getDayOfMonth(),
//                                xtime.getHour(),
//                                xtime.getMinute(),
//                                00);
//                    }
                    myCalendar.set(Calendar.HOUR_OF_DAY, xtime.getHour());
                    myCalendar.set(Calendar.MINUTE, xtime.getMinute());

                    if(myCalendar.compareTo(current) <= 0){
                        //The set Date/Time already passed
                        Toast.makeText(getApplicationContext(),
                                "Invalid Date/Time",
                                Toast.LENGTH_LONG).show();
                    }else{

                        setAlarm(myCalendar, RQS);
                    }
                    AddData(title, description, date, time, RQS);

                    //after adding the data, set an alarm or reminder for the submission day
                    //remember to check later for if the time and date selected has already past



                    xtitle.setText("");
                    xdescription.setText("");
                }else {
                    toastMessage("You must have a title and a description before you save");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), My_Assignments.class);
                finish();
                startActivity(i);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private void updateLabel(Calendar myCalendar) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        //Calendar mySecCalendar = Calendar.getInstance();

        xdatepicker.setText(sdf.format(myCalendar.getTime()));
    }

    public void AddData(String title, String description, String date, String time, int RQS){
        //recall that the add data method in the helper returns true or false
        boolean insertData = mDbHelper.addAssignment(title, description, date, time, RQS);
        if(insertData){
            toastMessage("Assignment saved successfully");
        }else {
            toastMessage("Error saving assignment");
        }
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

    //this should to edit and view assignments
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

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




}
