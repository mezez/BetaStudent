package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LectureTimeTableDetailActivity extends AppCompatActivity {

    public static final String ID = "com.mezez.betastudent.ID";
    private static final String TAG = "LecturesDataActivity";
    DBHelper mDbHelper;
    long set_id;
    String day, header;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_time_table_detail);
        Intent intent = getIntent();
        day = intent.getStringExtra(LectureTimetableActivity.DAY);

        //capitalize the day
        header = day.substring(0, 1).toUpperCase() + day.substring(1);

        getSupportActionBar().setTitle(header + "'s courses");


        //day = intent.getStringExtra(LectureTimetableActivity.DAY);


        mListView = (ListView) findViewById(R.id.lecture_time_table_listview);
        //registerForContextMenu(mListView);
        mDbHelper = new DBHelper(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id = mDbHelper.getLectureItemIdByPosition(position, day);
                setId(id);
                //toastMessage("id: "+ id );
                startActivity(new Intent(view.getContext(), LectureDetailActivity.class).putExtra(ID, id));
            }
        });
    }

    public void setId(long set_id){
        this.set_id = set_id;
    }

    public long getId(){
        return this.set_id;
    }


    public void populateListView(){
        //Log.d(TAG, "populateListView: Displaying data in the Listview");



        // when the date time feature is added see if the string casting will have an effect on the array list


        ArrayList<String> listData = new ArrayList<>();
        //get the assignments and append to a list
        Cursor data = mDbHelper.getLectureData(day);
        //Cursor data = mDbHelper.getAllLectureData();
        if (data.getCount() == 0){
            toastMessage("No lectures found");
        }else {



            while (data.moveToNext()){
                //if((data.getString(1) == "monday")) {
                    listData.add(data.getString(2));
                //}
            }

            //create a list adapter set the adapter and append the data to a list
            ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);
        }

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
