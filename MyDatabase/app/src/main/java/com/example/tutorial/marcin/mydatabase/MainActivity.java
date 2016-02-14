package com.example.tutorial.marcin.mydatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textview1);

        DBManager dbManager = new DBManager(this);

        DBContact newcontact = new DBContact();
        newcontact.setImie("Jan");
        newcontact.setNazwisko("Kowalski");
        newcontact.setTelefon("112");
        dbManager.addContact(newcontact);

        newcontact.setImie("Janusz");
        newcontact.setNazwisko("Cebulak");
        newcontact.setTelefon("997");
        dbManager.addContact(newcontact);

        newcontact.setImie("Paweł");
        newcontact.setNazwisko("Nowak");
        newcontact.setTelefon("998");
        dbManager.addContact(newcontact);

        // dbManager.addContact("Jan", "Kowalski", "112");
        // dbManager.addContact("Janusz", "Cebulak", "997");
        // dbManager.addContact("Paweł", "Nowak", "998");

/*
        DBContact contact = dbManager.getContact(2);
        textView.setText(String.format("%s %d %s %s %s\n", textView.getText(),
                contact.getNr(),
                contact.getImie(),
                contact.getNazwisko(),
                contact.getTelefon()));
*/

        /*
        Cursor cursor = dbManager.getAllRecords();
        while (cursor.moveToNext()) {
            int nr = cursor.getInt(0);
            String imie = cursor.getString(1);
            String nazwisko = cursor.getString(2);
            String telefon = cursor.getString(3);

            textView.setText(String.format("%s %d %s %s %s\n", textView.getText(), nr, imie, nazwisko, telefon));
        }
        */
        //TODO: wyświetlić wszytkie rekordy iterując po liście kontaktów DONE
        for ( DBContact contact : dbManager.getAllContacts() ) {
            textView.setText(String.format("%s %d %s %s %s\n", textView.getText(),
                    contact.getNr(),
                    contact.getImie(),
                    contact.getNazwisko(),
                    contact.getTelefon()));
            Log.d("Rekord z bazy danych: ",  contact.getNr() + " " +
                    contact.getImie() + " " +
                    contact.getNazwisko() + " " +
                    contact.getTelefon() );
        }
    }
}
