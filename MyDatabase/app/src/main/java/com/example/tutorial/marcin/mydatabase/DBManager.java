package com.example.tutorial.marcin.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marcin on 01.02.2016.
 */
public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context) {
        super(context, "kontakty.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table telefony(" +
                "nr integer primary key autoincrement, " +
                "imie text," +
                "nazwisko text," +
                "telefon text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TODO: przerobić na używanie klasy DBContact DONE
/*
    public void addContact(String imie, String nazwisko, String telefon) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imie", imie);
        values.put("nazwisko", nazwisko);
        values.put("telefon", telefon);
        db.insertOrThrow("telefony", null, values);
    }
*/
    public void addContact(DBContact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imie", contact.getImie());
        values.put("nazwisko", contact.getNazwisko());
        values.put("telefon", contact.getTelefon());
        db.insertOrThrow("telefony", null, values);
    }


    //TODO: przerobić na używanie klasy DBContact (a może tego nie?) DONE
    public void deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = { Integer.toString(id) };
        db.delete("telefony", "id = ?", args);
    }

    public void deleteContact(DBContact contact) {
        deleteContact(contact.getNr());
    }

    //TODO: przerobić na używanie klasy DBContact DONE
/*
    public void updateContact(int id, String imie, String nazwisko, String telefon) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imie", imie);
        values.put("nazwisko", nazwisko);
        values.put("telefon", telefon);
        String[] args = { Integer.toString(id) };
        db.update("telefony", values, "id = ?", args);
    }
*/
    public void updateContact(DBContact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imie", contact.getImie());
        values.put("nazwisko", contact.getNazwisko());
        values.put("telefon", contact.getTelefon());
        String[] args = { Integer.toString(contact.getNr())};
        db.update("telefony", values, "id = ?", args);
    }

    public DBContact getContact(int id) {
        DBContact contact = new DBContact();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"nr", "imie", "nazwisko", "telefon"};
        String[] args = { Integer.toString(id) };
        Cursor cursor = db.query("telefony", columns, "nr = ?", args, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            contact.setNr(cursor.getInt(0));
            contact.setImie(cursor.getString(1));
            contact.setNazwisko(cursor.getString(2));
            contact.setTelefon(cursor.getString(3));
        }
        return contact;
    }

    //TODO: nie zwracać kursora tylko listę kontaktów DONE
/*
    public Cursor getAllRecords() {
        String[] columns = {"nr", "imie", "nazwisko", "telefon"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("telefony", columns, null, null, null, null, null);
        return cursor;
    }
*/
    public List<DBContact> getAllContacts() {
        String[] columns = {"nr", "imie", "nazwisko", "telefon"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("telefony", columns, null, null, null, null, null);
        List<DBContact> contactList = new LinkedList<>();

        if (cursor.moveToFirst()) {
            do {
                DBContact contact = new DBContact();
                contact.setNr(cursor.getInt(0));
                contact.setImie(cursor.getString(1));
                contact.setNazwisko(cursor.getString(2));
                contact.setTelefon(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }
}
