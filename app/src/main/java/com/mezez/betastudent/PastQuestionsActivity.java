package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PastQuestionsActivity extends AppCompatActivity {

    public static final String ID = "com.mezez.betastudent.ID";
    private static final String TAG = "AssignmentsDataActivity";
    DBHelper mDbHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Past Questions");

        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.past_question_swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i("Swipe refresh", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        populateListView();
                        if (mySwipeRefreshLayout.isRefreshing()) {
                            mySwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                }
        );

        mListView = (ListView) findViewById(R.id.past_question_listview);
        mDbHelper = new DBHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.past_question_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NewPastQuestionActivity.class));
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id = mDbHelper.getPastQuestionItemIdByPosition(position);
                //toastMessage("id: "+ id );
                startActivity(new Intent(view.getContext(), PastQuestionDetailActivity.class).putExtra(ID, id));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_past_questions, menu);//Menu Resource, Menu
//
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            // Check if user triggered a refresh:
            case R.id.past_question_menu_refresh:
                Log.i("Refresh", "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.past_question_swiperefresh);
                mSwipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                populateListView();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                return true;

//            case R.id.search:
//                onSearchRequested();
//                return true;
            default:
                //return false;
                break;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }

    public void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the Listview");



        // when the date time feature is added see if the string casting will have an effect on the array list


        ArrayList<String> listData = new ArrayList<>();
        //get the assignments and append to a list
        Cursor data = mDbHelper.getPastQuestionData();
        if (data.getCount() == 0){
            toastMessage("No past questions found");
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
