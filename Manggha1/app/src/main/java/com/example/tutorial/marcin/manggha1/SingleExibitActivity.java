package com.example.tutorial.marcin.manggha1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class SingleExibitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exibit);

        Bundle bundle = getIntent().getExtras();
        final String passedExibit = bundle.getString("passed_param_1");

        Toast.makeText(getApplicationContext(), "Exibit: " + passedExibit, Toast.LENGTH_SHORT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Exibit: " + passedExibit, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
