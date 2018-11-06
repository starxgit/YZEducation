package com.fstech.yzedutc.activity;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

import com.fstech.yzedutc.R;


public class WebAboutActivity extends AppCompatActivity {
    private WebView webView;
    private TextView tv_title;
    private String url, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webabout);
        initView();
        initData();
        initWeb();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        tv_title = (TextView) findViewById(R.id.tv_title);
        url="http://mp.weixin.qq.com/s/Ns-yIWatEEm8Mk9kxlsNKw";
        title="关于我们";
    }

    private void initData() {
        Intent i = getIntent();
        url = i.getStringExtra("url");
        title = i.getStringExtra("title");
        tv_title.setText(title);
    }

    private void initWeb() {
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void back(View v) {
        finish();
    }

}
