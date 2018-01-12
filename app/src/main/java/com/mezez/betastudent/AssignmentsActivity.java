package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AssignmentsActivity extends AppCompatActivity {

    public static final String ID = "com.mezez.betastudent.ID";
    private static final String TAG = "AssignmentsDataActivity";
    DBHelper mDbHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        mListView = (ListView) findViewById(R.id.assignment_listview);
        mDbHelper = new DBHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NewAssignmentActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id = parent.getSelectedItemId();
                startActivity(new Intent(view.getContext(), AssignmentDetailActivity.class).putExtra(ID, id));
//                Intent intent = new Intent(AssignmentsActivity.this, AssignmentDetailActivity.class);
//                intent.putExtra(ID, id);
//                startActivity(intent);
            }
        });
    }

    public void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the Listview");



        // when the date time feature is added see if the string casting will have an effect on the array list


        ArrayList<String> listData = new ArrayList<>();
        //get the assignments and append to a list
        Cursor data = mDbHelper.getData();
        if (data.getCount() == 0){
            toastMessage("No assignments found");
        }else {



            while (data.moveToNext()){
                listData.add(data.getString(1));
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
