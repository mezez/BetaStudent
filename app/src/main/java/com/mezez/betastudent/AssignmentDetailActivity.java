package com.mezez.betastudent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AssignmentDetailActivity extends AppCompatActivity {
    DBHelper mDbHelper;
    TextView title, description, date, time;
    public static final String ID = "com.mezez.betastudent.ID";
    int RQS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the intent that started this activity and extract the string
        Intent intent = getIntent();

        long id = intent.getLongExtra(My_Assignments.ID, 0)  ;

        //Capture the layout textview and set the string as its text
        title = (TextView) findViewById(R.id.ass_det_title);
        description = (TextView) findViewById(R.id.ass_det_desc);
        date = (TextView) findViewById(R.id.ass_det_date);
        time = (TextView) findViewById(R.id.ass_det_time);

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
                String date_data = data.getString(3);
                String time_data = data.getString(4);
                RQS = data.getInt(5);


               // toastMessage( title_data);

                title.setText("Title: " + title_data);
                description.setText("Description: " + description_data);
                date.setText(date_data);
                time.setText(time_data);
            }



        }



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assignment_detail, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // check if user triggered disble alarm
            case R.id.menu_disable_alarm:
                Log.i("Disable", "Disable menu item selected");
                cancelAlarm(RQS);

                break;
            // Check if user triggered an edit:
            case R.id.menu_edit:
                Log.i("Edit", "Edit menu item selected");
                //get the intent that started this activity and extract the string
                Intent intent = getIntent();
                long id = intent.getLongExtra(My_Assignments.ID, 0)  ;


                //close the current screem and open the edit screen
                Intent i = new Intent(this, EditAssignmentActivity.class).putExtra(ID, id);
                finish();
                startActivity(i);

                break;
            case R.id.menu_delete:
                Log.i("Delete", "Delete menu item selected");

                Intent intent_2 = getIntent();
                long id_2 = intent_2.getLongExtra(My_Assignments.ID, 0) ;
                mDbHelper.deleteAssignment(id_2);
                //close the current screem and open the edit screen
                Intent j = new Intent(this, My_Assignments.class);
                finish();
                startActivity(j);

                break;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;




        }
        return super.onOptionsItemSelected(item);


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
            toastMessage("Alarm cancelled");
        }

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
