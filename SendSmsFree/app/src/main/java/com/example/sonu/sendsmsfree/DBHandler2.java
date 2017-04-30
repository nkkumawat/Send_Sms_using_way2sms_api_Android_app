package com.example.sonu.sendsmsfree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonu on 29/4/17.
 */

public class DBHandler2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "way1";
    private static final String WAY_2 = "way1";
    String notFound = "notFound";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_MOB = "mob";
    private static final String KEY_PASS = "pass";

    public DBHandler2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + WAY_2 + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MOB + " TEXT,"+ KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WAY_2);
        onCreate(db);
    }
    public void insert(String mob , String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MOB, mob);
        values.put(KEY_PASS, pass);
        db.insert(WAY_2, null, values);
        db.close();
    }
    public String getDataMobile(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WAY_2, new String[]{KEY_ID,  KEY_MOB, KEY_PASS}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return notFound;
        return  cursor.getString(1);
    }
    public String getDataPass(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WAY_2, new String[]{KEY_ID, KEY_MOB, KEY_PASS}, KEY_ID + "=?",  new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return notFound;
        return  cursor.getString(2);
    }
    public int getUser() {
        String countQuery = "SELECT  * FROM " + WAY_2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }

}
