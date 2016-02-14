package com.example.tutorial.marcin.webviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbInternet;
    private RadioButton rbIntranet;
    private RadioButton rbLocalhost;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbInternet = (RadioButton) findViewById(R.id.rbInternet);
        rbIntranet = (RadioButton) findViewById(R.id.rbIntranet);
        rbLocalhost = (RadioButton) findViewById(R.id.rbLocalhost);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onRBClick (View v) {

        hideKeyboard();

        switch (v.getId()) {
            case R.id.rbInternet:
                rbIntranet.setChecked(false);
                rbLocalhost.setChecked(false);
                editText.setText("http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3");
                break;
            case R.id.rbIntranet:
                rbInternet.setChecked(false);
                rbLocalhost.setChecked(false);
                editText.setText("http://192.168.13.101:8080/myapps/");
                break;
            case R.id.rbLocalhost:
                rbInternet.setChecked(false);
                rbIntranet.setChecked(false);
                editText.setText("http://127.0.0.1:8080/myapps/");
                break;
        }
    }

    public void onClickButtonOpen (View v) {

        hideKeyboard();

        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(editText.getText().toString());
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.clearFocus();
    }

}
