package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {
    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_AGE = "age";

    private static final String DB_NAME = "address_book_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "names_and_addresses";

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override

        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString = "create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_AGE
                    + " text not null);";
            db.execSQL(newTableQueryString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

        }
    }
        public DataManager(Context context){
            CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
            db = helper.getWritableDatabase();
        }

        public void insert(String name, String age){
            String query = "INSERT INTO " + TABLE_N_AND_A + " (" +
                    TABLE_ROW_NAME + ", " +
                    TABLE_ROW_AGE + ") " +
                    "VALUES (" +
                    "'" + name + "'" + ", " +
                    "'" + age + "'" +
                    ")";
            Log.i("insert() = ", query);
            db .execSQL(query);
        }

        public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" + " from " +
                TABLE_N_AND_A, null);
        return c;
        }

    public String showData(Cursor c){
        AppData myData = new AppData();
        while (c.moveToNext()){
            Log.i(c.getString(1), c.getString(2)) ;
            myData.setData(c.getString(0), c.getString(1),
                    c.getString(2));
        }
        return myData.getData();
    }

    public Cursor searchName(String name){
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_NAME +
                ", " + TABLE_ROW_AGE +
                " from " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_NAME + " = '" + name + "';";
        Log.i("searchName() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public AppData editName(Cursor c)
    {
        AppData myData = new AppData();

        while (c.moveToNext()){
            myData.setName(c.getString(1));
            myData.setAge(c.getString(2));
        }
        return  myData;
    }

    public void update(String name, String age){
        String query = "UPDATE " + TABLE_N_AND_A +
                " SET " +
                TABLE_ROW_AGE + " = " +
                "'" + age + "'" +
                " WHERE " + TABLE_ROW_NAME + " = '" + name + "'";
        Log.i("update() = ", query);
        db.execSQL(query);
    }

    public void delete(String name) {
        String query = "DELETE FROM " + TABLE_N_AND_A +
                " WHERE " + TABLE_ROW_NAME +
                " = '" + name + "';";
        Log.i("delete() = ", query);
        db.execSQL(query);
    }


}
