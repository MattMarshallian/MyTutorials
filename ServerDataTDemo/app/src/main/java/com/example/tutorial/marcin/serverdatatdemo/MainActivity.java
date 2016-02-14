package com.example.tutorial.marcin.serverdatatdemo;

import android.app.ProgressDialog;
import android.media.MediaActionSound;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WebServiceHandler().execute("http://damianchodorek.com/wsexample/");
            }
        });

    }

    private class WebServiceHandler extends AsyncTask<String, Void, String>
    {
        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);


        /*
        metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        mamy w niej dostęp do elementów UI
        */
        protected void onPreExecute()
        {
            // wyświetlenie okna dialogowego
            progressDialog.setMessage("Czekaj tutaj...");
            progressDialog.show();
        }

        /*
        główna operacja, która wykona się w osobnym wątku
        nie ma w niej dostępu do elementów UI
        Musi być implementowana jeśli mamy AsyncTask
        */
        @Override
        protected String doInBackground(String... urls) {

            try {
                // dla uproszczenia niech to będzie jeden URL
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do Input Stream
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                // przekonwertować na string a wynik będzie przekazany do onPostExecute()
                return streamToString(inputStream);

            } catch (Exception e)
            {
                // obsługujemy obowiązkowy wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }

            return null;
        }

        /*
        metoda wykonuje się po zakończeniu metody głównej,
        której wynik będzie przekazany;
        w tej metodzie mamy dostęp do UI
        */

        protected void onPostExecute(String results)
        {
            // po pierwsze schować okno dialogowe
            progressDialog.dismiss();

            try
            {
                // reperezentacja obiektu JSON w Javie
                JSONObject json = new JSONObject(results);

                // pobranie pól obiektu JSON
                ((TextView) findViewById(R.id.response_id)).setText("id: " + json.optString("id"));
                ((TextView) findViewById(R.id.response_name)).setText("name: " + json.optString("name"));

            }
            catch (Exception e)
            {
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
        }

        private String streamToString(InputStream inputStream)
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            try
            {
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line + "\n");
                }
                bufferedReader.close();

            }
            catch (IOException e)
            {
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }

            return stringBuilder.toString();
        }
    }

}
