package com.example.tutorial.marcin.asyncrotask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class AsyncronoustaskAndroidExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyncronoustask_android_example);

        final Button GetServerData = (Button) findViewById(R.id.GetServerData);

        GetServerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                public void onClickView(View v) {
                    // Server Request URL
                    String serverURL = "http://androidexample.com/media/webservice/getPage.php";
                    // Create Object and call AsyncTask execute Method
                    new LongOperation().execute(serverURL);
                }
            }
        });
    }
    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, Void, Void> {
        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(AsyncronoustaskAndroidExample.this);

        TextView uiUpdate = (TextView) findViewById(R.id.output);

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //UI Element
            uiUpdate.setText("Output : ");
            Dialog.setMessage("Downloading source..");
            Dialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            try {

                // Call long running operations here (perform background computation)
                // NOTE: Don't call UI Element here.

                // Server url call by GET method
                HttpGet httpget = new HttpGet(urls[0]);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpget, responseHandler);

            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }

            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();

            if (Error != null) {

                uiUpdate.setText("Output : "+Error);

            } else {

                uiUpdate.setText("Output : "+Content);

            }
        }

    }
}

