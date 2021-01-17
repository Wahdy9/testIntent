package com.example.testintent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.drm.DrmStore;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(); //load the chosen language
        setContentView(R.layout.activity_main);

        //change actionbar title, if not changed it will be the default value your system is.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        //actionBar.setTitle(R.string.app_name);//same thing

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        //NetworkDisconnectedReciever reciever = new NetworkDisconnectedReciever();
        //registerReceiver(reciever, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));


        final Snackbar snackbar =Snackbar.make(coordinatorLayout, "You Lost Connection", Snackbar.LENGTH_INDEFINITE).setAction("Go to Wifi", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent != null){
                    Log.e("Action ",intent.getAction());
                    if(intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")){
                        if(!isNetworkAvailable()) {
                            //Toast.makeText(context, "Connection Changed!!!", Toast.LENGTH_LONG).show();
                            snackbar.show();
                        }else{
                            if(snackbar.isShown())
                                snackbar.dismiss();
                        }
                    }
                }
            }
            private boolean isNetworkAvailable() {
                ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null;
            }
        };
       // registerReceiver(br, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));


    }


    public void youtubeBtn(View view) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com"));
        startActivity(browserIntent);
    }

    public void webviewBtn(View view) {
        startActivity(new Intent(this, WebviewActivity.class));
    }

    public void showAlert(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("this is message!")
                .setCancelable(false) //so if user click anywhere on screen it will not cancel
                .setPositiveButton("go to wifi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProgressDialog pd = new ProgressDialog(getApplicationContext());
                        pd.setMessage("loading...");
                        pd.show();
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        pd.dismiss();
                    }
                })
                //.setNegativeButton(android.R.string.no, null) //to make the cancel to cancel the dialog
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "cancel toast!", Toast.LENGTH_SHORT).show();
                    }
                }).show();

    }

    public void textLayout(View view) {
        startActivity(new Intent(this, TextLayoutActivity.class));

    }

    public void alertActivity(View view) {
        startActivity(new Intent(this, AlertDialogActivity.class));
    }

    public void badgeActivity(View view) {
        startActivity(new Intent(this, BadgeActivity.class));
    }

    public void changeLang(View view) {
        //show alert dialog to display list of language
        showLanguageDialog();
    }

    private void showLanguageDialog() {
        String[] options = {"English", "Arabic"};
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Choose a language");
        builder.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which ==0){
                            setLocale("en");
                            recreate();
                        }else if(which ==1){
                            setLocale("ar");
                            recreate();
                        }
                        //dismiss dialog when item selected
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setLocale(String lang) {
        System.out.println("//////////////*** Inside setLocale");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        System.out.println("////Inside setLocale()  lang=" + lang);
        editor.apply();
    }

    //load language saved in shared preferences
    public void loadLocale(){
        System.out.println("//////////////*** Inside loadLocale");
        SharedPreferences pref = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = pref.getString("My_Lang","");
        setLocale(language);
    }

    public void btmNav(View view) {
        startActivity(new Intent(this, BottomActivity.class));
    }

    public void locationAct(View view) {
        startActivity(new Intent(this, LocationAtivity.class));
    }
}
