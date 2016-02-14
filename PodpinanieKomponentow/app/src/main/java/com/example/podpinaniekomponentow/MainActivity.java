package com.example.podpinaniekomponentow;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button button;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.button1);
		button.setText(R.string.napis_1);

		textView = (TextView) findViewById(R.id.textView1);
		textView.setTextColor(getResources().getColor(R.color.light_red));
		textView.setText(R.string.napis_2);

	}



}
