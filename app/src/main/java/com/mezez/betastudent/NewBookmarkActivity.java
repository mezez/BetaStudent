package com.mezez.betastudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewBookmarkActivity extends AppCompatActivity {
    DBHelper mDbHelper;
    EditText title, description, url;
    String xtitle, xdescription, xurl;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bookmark);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New Bookmark");

        mDbHelper = new DBHelper(this);

        title = (EditText) findViewById(R.id.new_bookmark_title);
        description = (EditText) findViewById(R.id.new_bookmark_description);
        url = (EditText) findViewById(R.id.new_bookmark_url);
        save = (Button) findViewById(R.id.new_bookmark_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xtitle = title.getText().toString();
                xdescription = description.getText().toString();
                xurl = url.getText().toString();

                if (xtitle != "" && xurl != ""){
                    AddData(xtitle, xdescription, xurl);
                }
            }
        });
    }

    private void AddData(String xtitle, String xdescription, String xurl) {
        boolean insertData = mDbHelper.addBookmark(xtitle, xdescription, xurl);
        if(insertData){
            title.setText("");
            description.setText("");
            toastMessage("Bookmark saved successfully");
        }else {
            toastMessage("Error saving bookmark");
        }
    }

    private void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
