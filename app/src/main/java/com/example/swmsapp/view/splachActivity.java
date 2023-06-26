package com.example.swmsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Date;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.swmsapp.R;

public class splachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);


        // remove action bar from splash screen
      //  getSupportActionBar().hide();




        ImageView splashImage=findViewById(R.id.splash_App_imageView);

        ProgressBar progressBar=findViewById(R.id.progressBar);





        // rotate the splash screen image by 360 degree for 0.5 s

          splashImage.animate().rotation(360).setDuration(500);






          // lunch to the login activity  after 1.3 s

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                progressBar.setProgress(100);

                // start login activity

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        },1300);


    }
}