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

        //dummy data, inserted once during the creation of the database only
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "Jack Ma");
        cv.put(COL_GENDER, "Male");
        cv.put(COL_HEIGHT, 1.65);
        db.insert(DB_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Mary Poppins");
        cv.put(COL_GENDER, "Female");
        cv.put(COL_HEIGHT, 1.62);
        db.insert(DB_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Jackie Chan");
        cv.put(COL_GENDER, "Male");
        cv.put(COL_HEIGHT, 1.61);
        db.insert(DB_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Mary Jane");
        cv.put(COL_GENDER, "Female");
        cv.put(COL_HEIGHT, 1.56);
        db.insert(DB_TABLE, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Tarzan Jane");
        cv.put(COL_GENDER, "Female");
        cv.put(COL_HEIGHT, 1.7);
        db.insert(DB_TABLE, null, cv);
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
        db.close();
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

    public ArrayList<String> getContactContent(){
        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String [] cols = {COL_ID, COL_NAME, COL_GENDER, COL_HEIGHT};

        Cursor cursor = db.query(DB_TABLE, cols, null, null, null, null, null);
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


    public ArrayList<String> getContactContentByGender(String genderSelection){
        ArrayList<String> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String [] cols = {COL_ID, COL_NAME, COL_GENDER, COL_HEIGHT};
        String condition = "gender = ?";
        String [] args = {genderSelection};

        Cursor cursor = db.query(DB_TABLE, cols, condition, args, null, null, null);
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


    public ArrayList<Contact> getContactsByGender (String genderSelection, String namePrefix){
        ArrayList<Contact> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String [] cols = {COL_ID, COL_NAME, COL_GENDER, COL_HEIGHT};
        String condition = "gender = ? and name like ?";
        String [] args = {genderSelection, namePrefix + "%"};

        Cursor cursor = db.query(DB_TABLE, cols, condition, args, null, null, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                double height = cursor.getDouble(3);
                Contact data = new Contact(name, id, height, gender );
                al.add(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> al = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String [] cols = {COL_ID, COL_NAME, COL_GENDER, COL_HEIGHT};

        Cursor cursor = db.query(DB_TABLE, cols, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                double height = cursor.getDouble(3);
                Contact data = new Contact(name, id, height, gender );
                al.add(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }

    public void updateContact(Contact data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, data.getName());
        cv.put(COL_GENDER, data.getGender());
        cv.put(COL_HEIGHT, data.getHeight());
        int id = data.getId();
        String condition = COL_ID + " = ?";
        String [] args = {id+""};
        db.update(DB_TABLE, cv, condition, args);
        db.close();
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COL_ID + " = ?";
        String [] args = {id+""};
        db.delete(DB_TABLE, condition, args);
        db.close();
    }
}
