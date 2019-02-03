package com.mesbahsoft.namazghaza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: fateme
 * Date: 11/10/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */

public class db_helper extends SQLiteOpenHelper {

        // Logcat tag
    private static final String LOG = "NamazDbHelper";
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "namazManager";
    // Table Names
    private static final String TABLE_NAMAZ = "namaz";
    private static final String TABLE_GHAZA = "tblghaza";
    // private static final String TABLE_TAG = "tags";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_ROKAT = "rokat";
    private static final String KEY_GHAZA_ROKAT = "GHAZArokat";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_NAMAZ = "CREATE TABLE "
            + TABLE_NAMAZ + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ROKAT
            + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    private static final String CREATE_TABLE_GHAZA = "CREATE TABLE "
            + TABLE_GHAZA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_GHAZA_ROKAT
            + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    private String Select_Sum_NAMAZ_Rokat = "SELECT SUM ("
            + KEY_ROKAT
            + ") FROM " + TABLE_NAMAZ ;

    private String Select_Sum_NAMAZ_GHAZA = "SELECT SUM ("
            + KEY_GHAZA_ROKAT
            + ") FROM " + TABLE_GHAZA ;

    private String Select_Sum_Namaz_Rokat_LastDay = "SELECT SUM ("
            + KEY_ROKAT + ") FROM "+ TABLE_NAMAZ + " WHERE "
            + KEY_CREATED_AT + " >= DATE('now','-1 days') ";

    public db_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_NAMAZ);
        db.execSQL(CREATE_TABLE_GHAZA);
//        db.execSQL(CREATE_TABLE_TAG);
//        db.execSQL(CREATE_TABLE_TODO_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMAZ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GHAZA);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_TAG);

        // create new tables
        onCreate(db);
    }

    public int getCountKolAda(){
        SQLiteDatabase db = getReadableDatabase();


        //SELECT SUM(column_name) FROM table_name;

        Cursor cursor = db.rawQuery(
                Select_Sum_NAMAZ_Rokat, null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }

        return 0;
    }

    public int getCountKolAdaLastDay(){
        SQLiteDatabase db = getReadableDatabase();


        //SELECT SUM(column_name) FROM table_name;

        Cursor cursor = db.rawQuery(
                Select_Sum_Namaz_Rokat_LastDay, null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }

        return 0;
    }

    public int getCountKolGhaza(){
        SQLiteDatabase db = getReadableDatabase();


        //SELECT SUM(column_name) FROM table_name;

        Cursor cursor = db.rawQuery(
                Select_Sum_NAMAZ_GHAZA, null);
        if(cursor.moveToFirst()) {
            return cursor.getInt(0);
        }

        return 0;
    }

    public String getlast2records(){
        SQLiteDatabase db = getReadableDatabase();
        int a=0,b=0;
        String recent="";
        String sqlquery="SELECT "
                + KEY_ROKAT
                + " FROM " + TABLE_NAMAZ + " ORDER BY "+ KEY_ID +" DESC LIMIT 2";
        Cursor c=db.rawQuery(sqlquery,null);
        if(c.moveToFirst()) {
            int curSize=c.getCount();  // return no of rows
            if(curSize>1) {
                a =c.getInt( c.getColumnIndexOrThrow(KEY_ROKAT));
                c.moveToNext();
                b =c.getInt( c.getColumnIndexOrThrow(KEY_ROKAT));
            } else {
                c.moveToFirst();
            }
            recent = Integer.toString(b)+ " + " +Integer.toString(a);
            return recent;
        }

        return "recent";
    }


    public String getlast3records(){
        SQLiteDatabase db = getReadableDatabase();
        int aa=0,bb=0,cc=0;
        String recent="";
        String sqlquery="SELECT "
                + KEY_ROKAT
                + " FROM " + TABLE_NAMAZ + " ORDER BY "+ KEY_ID +" DESC LIMIT 3";
        Cursor c=db.rawQuery(sqlquery,null);
        if(c.moveToFirst()) {
            int curSize=c.getCount();  // return no of rows
            if(curSize>2) {
                aa =c.getInt( c.getColumnIndexOrThrow(KEY_ROKAT));
                c.moveToNext();
                bb =c.getInt( c.getColumnIndexOrThrow(KEY_ROKAT));
                c.moveToNext();
                cc =c.getInt( c.getColumnIndexOrThrow(KEY_ROKAT));
            } else {
                c.moveToFirst();
            }
            recent = Integer.toString(cc)+ " + " +Integer.toString(bb)+ " + " +Integer.toString(aa);
            return recent;
        }

        return "recent";
    }

    public boolean addAdaRokatRow(int rokat){
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues values = new ContentValues();
        values.put(KEY_ROKAT, rokat); // Contact Name
        values.put(KEY_CREATED_AT, dateFormat.format(date)); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_NAMAZ, null, values);
        return false;
    }

    public boolean addGhazaRokatRow(int rokat){
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues values = new ContentValues();
        values.put(KEY_GHAZA_ROKAT, rokat); // Contact Name
        values.put(KEY_CREATED_AT, dateFormat.format(date)); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_GHAZA, null, values);
        return false;
    }




}