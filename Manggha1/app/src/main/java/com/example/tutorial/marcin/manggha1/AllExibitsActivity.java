package com.example.tutorial.marcin.manggha1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AllExibitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exibits);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickEmpty (View view) {
        Snackbar.make(view, "Powiedz mi czego chcesz", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void onSelectExibition (View view) {

        Intent intent = new Intent(this, SingleExibitActivity.class);

        switch (view.getId()) {
            case R.id.exhib1:
                intent.putExtra("passed_param_1", "exhib_1");
                break;

            case R.id.exhib21:
            case R.id.exhib2:
                intent.putExtra("passed_param_1", "exhib_2");
                break;

            case R.id.exhib31:
            case R.id.exhib3:
                intent.putExtra("passed_param_1", "exhib_3");
                break;

            case R.id.exhib4:
                intent.putExtra("passed_param_1", "exhib_4");
                break;

            default:
                break;
        }

        startActivity(intent);

    }

}
