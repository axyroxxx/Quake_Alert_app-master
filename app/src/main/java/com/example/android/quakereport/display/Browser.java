package com.example.android.quakereport.display;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.quakereport.R;

import java.net.URL;

public class Browser extends AppCompatActivity {

    public SwipeRefreshLayout swipe;
    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webRefresh();
            }
        });
            webRefresh();
    }

    public void webRefresh(){
        webView =(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        Intent intent=getIntent();
        final String Address= intent.getStringExtra("given_url");
        webView.loadUrl(Address);
        swipe.setRefreshing(true);

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl(Address);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
