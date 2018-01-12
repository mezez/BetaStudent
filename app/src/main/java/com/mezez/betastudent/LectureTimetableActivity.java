package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LectureTimetableActivity extends AppCompatActivity {

    public static final String DAY = "com.mezez.betastudent.ID";
    private static final String TAG = "LecturesDataActivity";
    public String day, final_day;
    DBHelper mDbHelper;
    long set_id;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_timetable);

        mListView = (ListView) findViewById(R.id.lecture_listview);
        //registerForContextMenu(mListView);
        mDbHelper = new DBHelper(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NewLectureTimeTableActivity.class));
            }
        });
        populateListView();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //id = mDbHelper.getLectureItemIdByPosition(position);
                //setId(id);
                //toastMessage("id: "+ id );
                id = mListView.getItemIdAtPosition(position);
                //int newId = (int)id;
               // toastMessage(""+ newId + "");
                switch ((int) id) {

                    case 0:
                        day = "monday";
                        break;
                    case 1:
                        day = "tuesday";
                        break;
                    case 2:
                        day = "wednesday";
                        break;
                    case 3:
                        day = "thursday";
                        break;
                    case 4:
                        day = "friday";break;
                    case 5:
                        day = "saturday";break;
                    case 6:
                        day = "sunday";break;
                    default:
                        //return false;
                        break;
                }
                final_day = day.toLowerCase();
                startActivity(new Intent(view.getContext(), LectureTimeTableDetailActivity.class).putExtra(DAY, day));
            }
        });
    }

    public void populateListView(){
        //Log.d(TAG, "populateListView: Displaying data in the Listview");



        // when the date time feature is added see if the string casting will have an effect on the array list


        ArrayList<String> listData = new ArrayList<>();
        //get the assignments and append to a list
//        Cursor data = mDbHelper.getLectureData();
//        if (data.getCount() == 0){
//            toastMessage("No lectures found");
//        }else {
//
//
//
//            while (data.moveToNext()){
//                listData.add(data.getString(1));
//            }
//
//            //create a list adapter set the adapter and append the data to a list
//            ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
//            mListView.setAdapter(adapter);
//        }
        listData.add("Monday");
        listData.add("Tuesday");
        listData.add("Wednesday");
        listData.add("Thursday");
        listData.add("Friday");
        listData.add("Saturday");
        listData.add("Sunday");

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

    }
    public void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
