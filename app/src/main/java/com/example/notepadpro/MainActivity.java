package com.example.notepadpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //code Start here
        progressBar = (ProgressBar) findViewById(R.id.progressBarid);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                runThread();
                appHome();

            }
        });
        thread.start();
        //calling the appHome method to go the home Screen



    }

    //method for increasing the progressbar
    private void runThread() {

        for(progress = 20; progress <=100; progress = progress+20){
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //appHome Method
    private void appHome() {

        Intent intent = new Intent(MainActivity.this, AppHome.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.finish();
    }
}
