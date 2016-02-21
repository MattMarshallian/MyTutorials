package com.example.tutorial.marcin.manggha1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AllExibitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exibits);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        WebView webView = (WebView) findViewById(R.id.webViewAll);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(false);
        webView.loadUrl("http://matt.opx.pl");
    }

    public void onClickEmpty (View view) {
        Snackbar.make(view, "Powiedz mi czego chcesz", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void onSelectExibition (View view) {

        Intent intent = new Intent(this, SingleExibitActivity.class);

        switch (view.getId()) {
            case R.id.exhib1:
                intent.putExtra("passed_param_1", "Ślad");
                intent.putExtra("zone", 1);
                break;

           // case R.id.exhib21:
            case R.id.exhib2:
                intent.putExtra("passed_param_1", "Takayuki Hara");
                intent.putExtra("zone", 2);
                break;

           // case R.id.exhib31:
            case R.id.exhib3:
                intent.putExtra("passed_param_1", "Estetyka QR kodu");
                intent.putExtra("zone", 3);
                break;

            case R.id.exhib4:
                intent.putExtra("passed_param_1", "Aktorzy, lalki i gra cieni");
                intent.putExtra("zone", 4);
                break;

            default:
                break;
        }

        intent.putExtra("exhibit", view.getId());
        startActivity(intent);

    }

}
