package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBookmarkActivity extends AppCompatActivity {
    DBHelper mDbHelper;
    EditText title, description, url;
    String xtitle, xdescription, xurl;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bookmark);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Bookmark");

        //get the intent that started this activity and extract the string
        Intent intent = getIntent();

        //there is a plus one at the end of this line because the listview id starts at index 0
        final long id = intent.getLongExtra(BookmarksActivity.ID, 0)  ;

        mDbHelper = new DBHelper(this);

        title = (EditText) findViewById(R.id.edit_bookmark_title);
        description = (EditText) findViewById(R.id.edit_bookmark_description);
        url = (EditText) findViewById(R.id.edit_bookmark_url);
        save = (Button) findViewById(R.id.edit_bookmark_save);

        Cursor data = mDbHelper.getBookmarksItem(id);
        if (data.getCount() < 1){
            toastMessage("Error retrieving bookmark details");
        }else if (data.getCount() > 0) {
            //toastMessage( "data");
            while (data.moveToNext()){
                String title_data = data.getString(1);
                String description_data = data.getString(2);
                String url_data = data.getString(3);


                // toastMessage( title_data);

                title.setText(title_data);
                description.setText(description_data);
                url.setText(url_data);

            }



        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_title = title.getText().toString();
                String new_description = description.getText().toString();
                String new_url = url.getText().toString();

                if(new_title.length() != 0 && new_description.length() != 0 && new_url.length() != 0){

                    if (mDbHelper.updateBookmark(new_title, new_description, new_url, id)){
                        //close the current screem and open the edit screen
                        Intent i = new Intent(view.getContext(), BookmarksActivity.class);
                        finish();
                        startActivity(i);
                        toastMessage("Bookmark updated successfully");
                    }else {
                        //error updating the bookmark
                        toastMessage("Oops, an Error occured while updating the bookmark");
                    }



                }else {
                    toastMessage("You must have a title, description and url before you save");
                }
            }
        });
    }


    private void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
