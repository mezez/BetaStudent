package com.mezez.betastudent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView facebook =(TextView)findViewById(R.id.info_facebook);
        TextView twitter =(TextView)findViewById(R.id.info_twitter);
        facebook.setClickable(true);
        facebook.setMovementMethod(LinkMovementMethod.getInstance());
        twitter.setMovementMethod(LinkMovementMethod.getInstance());
        String fb_text = "<a href='https://www.facebook.com/mezez.ekemam'> Like developer on facebook </a>";
        String tw_text = "<a href='https://www.twitter.com/mezez_john_doe?s=09'> Follow on twitter </a>";
        facebook.setText(Html.fromHtml(fb_text));
        twitter.setText(Html.fromHtml(tw_text));
    }

}
