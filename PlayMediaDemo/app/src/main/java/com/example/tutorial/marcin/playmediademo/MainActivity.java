package com.example.tutorial.marcin.playmediademo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageButton buttonPlayPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayPause = (ImageButton) findViewById(R.id.ButtonTestPlayPause);
        // buttonPlayPause.setOnClickListener((View.OnClickListener) this);

        mediaPlayer = new MediaPlayer();
        // mediaPlayer.setOnBufferingUpdateListener((MediaPlayer.OnBufferingUpdateListener) this);
        // mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.ButtonTestPlayPause) {
            /** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
            try {
                // mediaPlayer.setDataSource("http://10.0.2.101:8080/myapps/Janosik.mp3");
                mediaPlayer.setDataSource("c:\\temp\\battleofdamascus-short.mp3");
                mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                buttonPlayPause.setImageResource(R.drawable.button_pause);
            } else {
                mediaPlayer.pause();
                buttonPlayPause.setImageResource(R.drawable.button_play);
            }

        }
    }
}
