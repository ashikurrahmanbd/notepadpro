package com.example.notepadpro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import static androidx.appcompat.app.AlertDialog.*;

public class AppHome extends AppCompatActivity {

    CardView newNote, allNotes;

    SQLiteDataBaseHelper sqLiteDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        //getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_app_home);

        //Creating DataBase Helper Object

        sqLiteDataBaseHelper = new SQLiteDataBaseHelper(this);

        SQLiteDatabase sqLiteDatabase = sqLiteDataBaseHelper.getWritableDatabase();


        newNote = (CardView) findViewById(R.id.newNoteId);
        allNotes = (CardView) findViewById(R.id.allnotesId);

        //newnote Card View Listener
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHome.this, NewNoteCreate.class);

                startActivity(intent);

            }
        });

        allNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppHome.this, AllNotes.class);
                startActivity(intent);
            }
        });




    }

    //onBackpressMethod to close the App

    @Override
    public void onBackPressed() {




        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit App");

        builder.setMessage("Are you Sure to Leave This App?");


        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
//                System.exit(0);

                 finishAffinity(); //thisOne is the better solution


            }
        }).setNegativeButton("No", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                builder.setCancelable(true);

            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }


}
