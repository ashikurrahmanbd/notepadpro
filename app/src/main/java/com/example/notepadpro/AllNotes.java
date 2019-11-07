package com.example.notepadpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class AllNotes extends AppCompatActivity {

    private ListView noteListView;

    SQLiteDataBaseHelper sqLiteDataBaseHelper;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    public int listItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        noteListView = (ListView) findViewById(R.id.ListViewNoteId);

        sqLiteDataBaseHelper = new SQLiteDataBaseHelper(this);

        SQLiteDatabase sqLiteDatabase =  sqLiteDataBaseHelper.getWritableDatabase();

        listItem = new ArrayList<>();

        final Cursor cursor = sqLiteDataBaseHelper.getData();


        if(cursor.getCount() == 0){

            return;

        }else{

            while(cursor.moveToNext()){


//                listItem.add(cursor.getString(0)+ ". "+cursor.getString(1) + ""+cursor.getString(2) );
                listItem.add(cursor.getString(1));


            }

            adapter = new ArrayAdapter(this, simple_list_item_1, listItem);

            noteListView.setAdapter(adapter);





        }//if close here

        //onItemClick Listener
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(cursor!=null){

                    if(cursor.moveToFirst()){
                        cursor.moveToPosition(i);


                        long rowId = cursor.getLong(cursor.getColumnIndex("_id"));

                        Intent intent = new Intent(getApplicationContext(), SingleNoteView.class);

                        //Toast.makeText(AllNotes.this, " Row Id is "+rowId, Toast.LENGTH_SHORT).show();

                      intent.putExtra("noteTitleId", rowId);

                      startActivity(intent);




                    }
                }

            }
        });


    }

}
