package com.example.tutorial.marcin.mysimpleclick;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1; // ten przycisk będzie się łączył przez jawny listener
    private Button button2; // połączenie jak wyżej ale listener tworzony w chwili wiązania do buttona
    // Button button3; // połączenie przez atrybut w xml - android:onClick="<nazwa funkcji>"
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        /*
        Tworzenie połączenia z komponentami wizualnymi
        poprzez jawne wskazanie listenera
        */
        button1 = (Button) findViewById(R.id.button1);

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getString(R.string.toast_1);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        };
        // tutaj związanie przycisku z listenerem
        button1.setOnClickListener(listener1);


        /*
        Tworzenie połącznenia z komponentem wizualnym
        poprzez utworzenie listenera w momencie jego przypisywania do buttona
         */
        button2 = (Button) findViewById(R.id.button2);
        // wiążemy button z właśnie tworzonym listenerem
        button2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String s = getString(R.string.toast_2);
                  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
              }
           });
    }

    /*
    Tutaj jest metoda, która jest wywoływana na kliknięcie w button3
    ale jego związanie jest opisane w xml dla buttona:
    android:onClick="onClickButton3"
    ważne, żeby metoda przyjmowała view jako parametr
    */
    public void onClickButton3(View v) {
        String s = getString(R.string.toast_3);
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView textView = (TextView) findViewById(R.id.textView);
        final int itemId = item.getItemId();
        Log.d("Wybrano z menu opcję: ", Integer.toString(itemId));

        // logowanie dla wszystkich casów jest takie samo, ale jakaś sensowna akcja będzie różna
        final String menu_title = item.getTitle().toString();
        switch (itemId) {
            case R.id.action1:
                Log.d("menu", menu_title);
                textView.setText(menu_title);
                break;
            case R.id.action2:
                Log.d("menu", menu_title);
                textView.setText(menu_title);
                break;
            case R.id.action_about:
                Log.d("menu", menu_title);
                textView.setText(menu_title);
                break;
        }
        return true;
    }

    public void onClickStartSecondActivity(View v) {
        editText = (EditText) findViewById(R.id.edittext1);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("passed_param_1", editText.getText().toString());
        startActivity(intent);
    }

    public void onClickStartBrowser(View v) {
        Uri uri = Uri.parse("http://google.pl");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickMenuShowIcons(MenuItem menuItem) {
        Intent intent = new Intent(this, TableLayoutActivity.class);
        startActivity(intent);
    }
}
