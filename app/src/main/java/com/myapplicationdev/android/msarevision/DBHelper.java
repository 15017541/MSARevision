package com.myapplicationdev.android.msarevision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jason_lim on 14/6/2017.
 */

public class DBHelper  extends SQLiteOpenHelper{

    private static int DB_VER = 1;
    private static final String DB_NAME = "contact.db";
    private static final String DB_TABLE = "Contact";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_GENDER = "gender";
    private static final String COL_HEIGHT = "height";


/*
    id  -   INTEGER (AUTO Increment)
    name - TEXT
    height - REAL
    gender - TEXT
 */

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +  DB_TABLE + " " +
                "(" + COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                "height REAL, " +
                "gender TEXT)";

        Log.d("SQL DML - create", sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE " + DB_TABLE;
        Log.d("SQL DML - upgrade", sql);
    }

    public long insertContact(String name, String gender, double height){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_GENDER, gender);
        cv.put(COL_HEIGHT, height);
        long result = db.insert(DB_TABLE, null, cv);
        return result;
    }

    public ArrayList<String> getContactContentSQL (){
        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT ID, NAME, Gender, Height from " + DB_TABLE;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                double height = cursor.getDouble(3);
                String data = id + ", " + name + ", "+ gender + ", " + height;
                al.add(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }
}
