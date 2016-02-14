package com.example.tutorial.marcin.myfolderviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String dir = "/storage/emulated/0/Music/";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        String[] dirElements = new File(dir).list();

        for (String dirElement : dirElements) {
            File f = new File(dir + dirElement);
            if (f.canRead()) dirElements[i] = dirElements[i] + " R";
            if (f.canWrite()) dirElements[i] = dirElements[i] + " W";
            Date d = new Date();
            d.setTime(f.lastModified());
            dirElements[i] = dirElements[i] + " (S: " + f.length() / 1024 + " KB, lm: " + d + ")";
            i++;
        }

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.addAll(Arrays.asList(dirElements));

        adapter = new ArrayAdapter<String>(this, R.layout.single_element, stringArrayList);
        listView.setAdapter(adapter);

    }
}
