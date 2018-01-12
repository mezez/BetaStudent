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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    public static final String ID = "com.mezez.betastudent.ID";
    private static final String TAG = "AssignmentsDataActivity";
    DBHelper mDbHelper;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(view.getContext(), NewBookmarkActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.bookmark_swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("Swipe refresh", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        populateListView();
                        if (mySwipeRefreshLayout.isRefreshing()) {
                            mySwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                }
        );

        mListView = (ListView) findViewById(R.id.bookmark_listview);
        mDbHelper = new DBHelper(this);

        populateListView();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id = mDbHelper.getBookmarkItemIdByPosition(position);
                //toastMessage("id: "+ id );
                startActivity(new Intent(view.getContext(), BookmarkDetailActivity.class).putExtra(ID, id));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookmarks, menu);//Menu Resource, Menu

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            // Check if user triggered a refresh:
            case R.id.bookmarks_menu_refresh:
                Log.i("Refresh", "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.bookmark_swiperefresh);
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
        //Log.d(TAG, "populateListView: Displaying data in the Listview");

        // when the date time feature is added see if the string casting will have an effect on the array list


        ArrayList<String> listData = new ArrayList<>();
        //get the bookmarks and append to a list
        Cursor data = mDbHelper.getBookmarkData();
        if (data.getCount() == 0){
            toastMessage("No Bookmarks found");
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
