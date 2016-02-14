package com.example.tutorial.marcin.serverreachabledemo;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    EditText adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adress = (EditText) findViewById(R.id.adress);
        adress.setText("http://www.google.pl/");
    }

    public void onClickButton (View view) {
        EditText adress = (EditText) findViewById(R.id.adress);
        TextView status = (TextView) findViewById(R.id.status);
        status.setText(adress.getText() + " [checking...]");

        if (isServerReachable(view.getContext(), adress.getText().toString()))
        {
            status.setText(adress.getText() + " [OK]");
        }
        else
        {
            status.setText(adress.getText() + " [NOT RESPONDED]");
        }
    }

    public boolean isServerReachable(Context context, String url) {
        ConnectivityManager connMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL urlServer = new URL(url);
                HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
                urlConn.setConnectTimeout(3000); //<- 3Seconds Timeout
                urlConn.connect();
                if (urlConn.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (MalformedURLException e1) {
                Log.d("Dupa", e1.toString());
                return false;
            } catch (IOException e) {
                Log.d("Dupa", e.toString());
                return false;
            }
        }
        return false;
    }
}
