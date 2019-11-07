package com.example.notepadpro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteCreate extends AppCompatActivity {

    private EditText noteTitle, noteDescription;
    private Button saveButton;

    SQLiteDataBaseHelper sqLiteDataBaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_create);

        //creating the object from database class;

        sqLiteDataBaseHelper = new SQLiteDataBaseHelper(this);

        SQLiteDatabase sqLiteDatabase = sqLiteDataBaseHelper.getWritableDatabase();

        noteTitle = (EditText) findViewById(R.id.noteTitleId);
        noteDescription = (EditText) findViewById(R.id.noteDescriptionId);
        saveButton = (Button) findViewById(R.id.noteSaveId);
        //even listener for saveButton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = noteTitle.getText().toString();
                String description = noteDescription.getText().toString();

                long rowId = sqLiteDataBaseHelper.insertData(title, description);

                if(rowId == -1){
                    Toast.makeText(NewNoteCreate.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(NewNoteCreate.this, "Data has been inserted successfully", Toast.LENGTH_SHORT).show();
                    noteTitle.setText("");
                    noteDescription.setText("");
                }


            }
        });

    }
}
