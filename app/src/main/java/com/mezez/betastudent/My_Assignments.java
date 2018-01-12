package com.mezez.betastudent;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class My_Assignments extends AppCompatActivity {

    public static final String ID = "com.mezez.betastudent.ID";
    private static final String TAG = "AssignmentsDataActivity";
    DBHelper mDbHelper;
    long set_id;

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__assignments);

        /*
 * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
 * performs a swipe-to-refresh gesture.
 */
         final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
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



        mListView = (ListView) findViewById(R.id.assignment_listview);
        registerForContextMenu(mListView);
        mDbHelper = new DBHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), NewAssignmentActivity.class));
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateListView();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id = mDbHelper.getItemIdByPosition(position);
                setId(id);
                //toastMessage("id: "+ id );
                startActivity(new Intent(view.getContext(), AssignmentDetailActivity.class).putExtra(ID, id));
            }
        });



//        mListView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                PopupMenu p = new PopupMenu(My_Assignments.this, v);
//                p.getMenuInflater().inflate(R.menu.menu_my_assignments_popup, p.getMenu());
//                p.show();
//                return false;
//            }
//        });
    }


    public void setId(long set_id){
        this.set_id = set_id;
    }

    public long getId(){
        return this.set_id;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_assignment_detail, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                long id_1 = getId();
                toastMessage(String.valueOf(id_1));
//                Intent i = new Intent(this, EditAssignmentActivity.class).putExtra(ID, id_1);
//                finish();
//                startActivity(i);
                break;
            case R.id.menu_delete:
                long id_2 = getId();
                mDbHelper.deleteAssignment(id_2);
                Intent in = new Intent(this, My_Assignments.class);
                finish();
                startActivity(in);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if (v.getId() == R.id.assignment_listview) {
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            menu.add(0, 0, 0, "Edit");
//            menu.add(0, 1, 1, "Delete");
//        }
//    }

//    @Override
//    public boolean onContextItemSelected(MenuItem menuItem){
//        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuItem.getMenuInfo();
//        switch (menuItem.getItemId()) {
//            case 0:
//                long id = info.id ;
//                //close the current screem and open the edit screen
//                Intent i = new Intent(this, EditAssignmentActivity.class).putExtra(ID, id);
//                finish();
//                startActivity(i);
//                break;
//            case 1:
//                long id_sec = info.id ;
//                mDbHelper.deleteAssignment(id_sec);
//                //close and reopen the activity to refresh the list
//                Intent in = new Intent(this, My_Assignments.class);
//                finish();
//                startActivity(in);
//                break;
//f
//            default:
//                break;
//
//        }
//        return true;
//    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_assignments, menu);//Menu Resource, Menu

        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//
//            searchView.setSearchableInfo(
//                    searchManager.getSearchableInfo(getComponentName()));
//            searchView.setIconifiedByDefault(false);
//        }
//
        return true;
    }

    /*
 * Listen for option item selections so that we receive a notification
 * when the user requests a refresh by selecting the refresh action bar item.
 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {



            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                Log.i("Refresh", "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
                mSwipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                populateListView();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

                return true;

            case R.id.search:
                onSearchRequested();
                return true;
            default:
                //return false;
                break;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }

    //how does onclick property get the id for do delete
    public void doDelete(long id){

        mDbHelper.deleteAssignment(id);
        //close and reopen the activity to refresh the list
        Intent i = new Intent(this, My_Assignments.class);
        finish();
        startActivity(i);
    }




    public void populateListView(){
        //Log.d(TAG, "populateListView: Displaying data in the Listview");



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

    public void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
