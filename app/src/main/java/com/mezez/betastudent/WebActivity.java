package com.mezez.betastudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //get the intent that started this activity and extract the string
        //Intent intent = getIntent();
//        String url = intent.getStringExtra(AssignmentDetailActivity.ID);

//        webView = (WebView) findViewById(R.id.bookmark_webview);
//        webView.setWebViewClient(new MyBrowser());
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView = new WebView(this);
//
//        setContentView(webView);
//        webView.loadUrl(url);
       // toastMessage(url);



    }

    public void onUrlClick(final View view) {



    }

   // @Override

//    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
//
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
////If there is history, then the canGoBack method will return ‘true’//
//            return true;
//        }

//If the button that’s been pressed wasn’t the ‘Back’ button, or there’s currently no
//WebView history, then the system should resort to its default behavior and return
//the user to the previous Activity//
//        return super.onKeyDown(keyCode, event);
//    }

//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
