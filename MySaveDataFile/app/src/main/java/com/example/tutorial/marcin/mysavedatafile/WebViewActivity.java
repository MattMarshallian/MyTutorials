package com.example.tutorial.marcin.mysavedatafile;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

import java.io.File;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }


    public void onClickButtonTest(View view) {

        hideKeyboard();

        File SDCardRoot = Environment.getExternalStorageDirectory();
        File file = new File(SDCardRoot,"HTML5/index.html");

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(file.getAbsolutePath());

        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file://" + file.getAbsolutePath());

    }


    private void hideKeyboard() {
        EditText editText = (EditText) findViewById(R.id.editText);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.clearFocus();
    }
}
