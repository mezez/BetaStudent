package com.mezez.betastudent;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class BookmarkDetailActivity extends AppCompatActivity {
    //private WebView webView;
    DBHelper mDbHelper;
    TextView title, description, url;
    String browser_url;
    public static final String ID = "com.mezez.betastudent.ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail);
        getSupportActionBar().setTitle("Bookmark Details");

        //get the intent that started this activity and extract the string
        final Intent intent = getIntent();

        long id = intent.getLongExtra(BookmarksActivity.ID, 0)  ;

        title = (TextView) findViewById(R.id.bookmark_detail_title);
        description = (TextView) findViewById(R.id.bookmark_detail_description);
        url = (TextView) findViewById(R.id.bookmark_detail_url);
        //url.setPaintFlags(url.getPaintFlags());
        //webView = (WebView) findViewById(R.id.bookmark_webview);
//        webView.setWebViewClient(new MyBrowser());
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mDbHelper = new DBHelper(this);
        Cursor data = mDbHelper.getBookmarksItem(id);
        if (data.getCount() < 1){
            toastMessage("Error retrieving bookmark details");
        }else if (data.getCount() > 0) {
            //toastMessage( "data");
            while (data.moveToNext()){
                String title_data = data.getString(1);
                String description_data = data.getString(2);
                String url_data = data.getString(3);

                title.setText("Title: " + title_data);
                description.setText("Description: " + description_data);
                url.setText(url_data);
            }



        }

//        url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                browser_url = url.getText().toString();
//                startActivity(new Intent(v.getContext(), WebActivity.class).putExtra(ID, browser_url));
//            }
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookmark_detail, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Check if user triggered an edit:
            case R.id.bookmark_menu_edit:
                Log.i("Edit", "Edit menu item selected");
                //get the intent that started this activity and extract the string
                Intent intent = getIntent();
                long id = intent.getLongExtra(BookmarksActivity.ID, 0)  ;


                //close the current screem and open the edit screen
                Intent i = new Intent(this, EditBookmarkActivity.class).putExtra(ID, id);
                finish();
                startActivity(i);

                break;
            case R.id.bookmark_menu_delete:
                Log.i("Delete", "Delete menu item selected");

                Intent intent_2 = getIntent();
                long id_2 = intent_2.getLongExtra(BookmarksActivity.ID, 0) ;
                mDbHelper.deleteBookmark(id_2);
                //close the current screem and open the edit screen
                Intent j = new Intent(this, BookmarksActivity.class);
                finish();
                startActivity(j);

                break;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;




        }
        return super.onOptionsItemSelected(item);


    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

//        public void onUrlClick(final View view) {
//            String sUrl = String.valueOf(url.getText());
//            webView.loadUrl(sUrl);
//
//        }
//
//        private class MyBrowser extends WebViewClient {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        }

}


