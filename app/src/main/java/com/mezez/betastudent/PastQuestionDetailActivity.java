package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class PastQuestionDetailActivity extends AppCompatActivity {
    DBHelper mDbHelper;

    TextView title, description;
    ImageButton imageButton;

    public static final String ID = "com.mezez.betastudent.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_question_detail);
        getSupportActionBar().setTitle("Past Question Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the intent that started this activity and extract the string
        Intent intent = getIntent();
        long id = intent.getLongExtra(PastQuestionsActivity.ID, 0)  ;

        title = (TextView) findViewById(R.id.past_question_detail_title);
        description = (TextView) findViewById(R.id.past_question_detail_description);
        imageButton = (ImageButton)findViewById(R.id.past_question_detail_imageButton);

        //get the data from the helper class
        mDbHelper = new DBHelper(this);
        Cursor data = mDbHelper.getPastQuestionItem(id);
        if (data.getCount() < 1){
            toastMessage("Error retrieving past question details");
        }else if (data.getCount() > 0) {

            while (data.moveToNext()){

                String title_data = data.getString(1);
                String description_data = data.getString(2);
                String image_data = data.getString(4);

                title.setText(title_data);
                description.setText(description_data);

                File file = new File(image_data);

                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());

                imageButton.setImageBitmap(bmp);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastMessage("Keep calm, it's work in progress");
                    }
                });
            }



        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
