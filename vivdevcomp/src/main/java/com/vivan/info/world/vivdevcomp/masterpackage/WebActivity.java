package com.vivan.info.world.vivdevcomp.masterpackage;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.vivan.info.world.vivdevcomp.R;

import java.util.concurrent.Callable;

public class WebActivity extends AdsClass {

    WebView wv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        loadInterastialAds();

        String myurl = getIntent().getStringExtra("url");

        wv1=(WebView)findViewById(R.id.webview);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(myurl);

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onBackPressed() {
        showInterstitialAds(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                finish();
                return null;
            }
        });
    }

}
