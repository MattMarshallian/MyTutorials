package com.example.tutorial.marcin.manggha1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class SingleExibitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exibit);

        Bundle bundle = getIntent().getExtras();
        final String passedExhibit = bundle.getString("passed_param_1");
        final int idExhibit = bundle.getInt("exhibit");
        final int idZone = bundle.getInt("zone"); // not used now

        Toast.makeText(getApplicationContext(), "Exhibit: " + passedExhibit, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageInToolbar = (ImageView) findViewById(R.id.prof_img_picBackground);
        String zoneStr = "";

        switch (idExhibit) {
            case R.id.exhib1:
                imageInToolbar.setBackgroundResource(R.drawable.all2_1);
                zoneStr = "zone1";
                break;

          //  case R.id.exhib21:
            case R.id.exhib2:
                imageInToolbar.setBackgroundResource(R.drawable.all2_2);
                zoneStr = "zone2";
                break;

          //  case R.id.exhib31:
            case R.id.exhib3:
                imageInToolbar.setBackgroundResource(R.drawable.all2_3);
                zoneStr = "zone3";
                break;

            case R.id.exhib4:
                imageInToolbar.setBackgroundResource(R.drawable.all2_4);
                zoneStr = "zone4";
                break;

            default:
                break;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Exhibit: " + passedExhibit, Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });

//        File SDCardRoot = Environment.getExternalStorageDirectory();
//        File file = new File(SDCardRoot,"HTML5/index.html");
        WebView webView = (WebView) findViewById(R.id.webView1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://matt.opx.pl/" + zoneStr);
    }
}
