package com.example.notepadpro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SingleNoteView extends AppCompatActivity {

    private TextView noteTitleText, noteDescriptionText;

    SQLiteDataBaseHelper sqLiteDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note_view);

        sqLiteDataBaseHelper = new SQLiteDataBaseHelper(this);

        SQLiteDatabase sqLiteDatabase = sqLiteDataBaseHelper.getWritableDatabase();

        noteTitleText = (TextView) findViewById(R.id.titleId);
        noteDescriptionText = (TextView) findViewById(R.id.notefullTextsId);




        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            try{

                long rowid = bundle.getLong("noteTitleId");
                Cursor cursor = sqLiteDataBaseHelper.singleItem(rowid);

                if (cursor != null) {
                    // move cursor to first row
                    if (cursor.moveToFirst()) {
                        do {
                            // Get version from Cursor
                            String noteTitleeee = cursor.getString(cursor.getColumnIndex("note_title"));
                            String noteDescriptionnn = cursor.getString(cursor.getColumnIndex("note_description"));

                            // add the bookName into the bookTitles ArrayList
                            noteTitleText.setText(noteTitleeee);
                            noteDescriptionText.setText(noteDescriptionnn);

                            // move to next row
                        } while (cursor.moveToNext());
                    }
                }


            }catch (Exception e){

                noteDescriptionText.setText(" "+e);
            }




        }




    }
}
