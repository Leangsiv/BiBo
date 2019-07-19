package com.example.bibo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends AppCompatActivity {

    //DataType of splashScreen time
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Remove Action Bar
        if(getSupportActionBar()!=null)
            this.getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        },SPLASH_SCREEN_TIME_OUT);
    }
}
