package com.example.notepadpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABSE_NAME = "Notestore.db";
    private static final int VERSION = 1;

    //create TABLE and fields
    private static final String TABLE_NAME = "notes";
    private static final String ID = "_id";
    private static final String NOTE_TITLE = "note_title";
    private static final String NOTE_DESCRIPTION = "note_description";

    //query for creating table
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NOTE_TITLE+" varchar(255), "+NOTE_DESCRIPTION+" text);";



    public SQLiteDataBaseHelper(@Nullable Context context) {
        super(context, DATABSE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Toast.makeText(context, "OnCreate Method has Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(context, "onUpgrade Method is Called", Toast.LENGTH_SHORT).show();

    }

    public long insertData(String noteTitle, String noteDescription){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE_TITLE, noteTitle);
        contentValues.put(NOTE_DESCRIPTION, noteDescription);
        long rowId =  sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return  rowId;

    }

    //Retrive Data from Database
    public Cursor getData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        return cursor;

    }

    //deleteRow From table

//    public int deleteData(String id){
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        return sqLiteDatabase.delete(TABLE_NAME, ID+"=?", new String[]{id});
//
//    }


    //single item Query

    public Cursor singleItem(long id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ID+" = "+id, null);

        return  cursor;
    }

}
