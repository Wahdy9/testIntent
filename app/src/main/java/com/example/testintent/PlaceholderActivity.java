package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//this activity uses placeholder of contraint layout + 2 layouts, one for potraite and one for landscape
public class PlaceholderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
    }
}
