package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;


//it has no xml file, the lib will create one for us
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withHeaderText("Header")
                .withFooterText("Footer")
                .withBeforeLogoText("Before Logo Text")
                .withAfterLogoText("After Logo Text")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
