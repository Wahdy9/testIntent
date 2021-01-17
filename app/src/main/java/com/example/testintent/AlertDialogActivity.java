package com.example.testintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

//demonstrate how to make custom alertDialog by customizing adapter
public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);


    }

    public void showAlert(View view) {
        final Item[] items = {
                new Item("Email", android.R.drawable.ic_menu_add),
                new Item(" ", R.drawable.google_signin),
                new Item("no Image", 0),//no icon for this one
        };

        ListAdapter adapter = new ArrayAdapter<Item>(
                this,
                android.R.layout.select_dialog_item,
                android.R.id.text1,
                items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                TextView tv = v.findViewById(android.R.id.text1);

                //Put the image on the TextView, and scale it
                int dp50 = (int) (50 * getResources().getDisplayMetrics().density + 0.5f);
                int dp300 = (int) (300 * getResources().getDisplayMetrics().density + 0.5f);
                if(items[position].icon == 0){
                    tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);
                }else{
                    Drawable dr = getResources().getDrawable(items[position].icon);
                    Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                    Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, dp300, dp50, true));
                    tv.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                }



                // Add margin between image and text (support various screen densities)
                int dp5 = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);
                tv.setCompoundDrawablePadding(dp5);

                return v;
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("You need to log in:")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(AlertDialogActivity.this, "Click 0", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(AlertDialogActivity.this, "Click 1", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(AlertDialogActivity.this, "Click 2", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).show();
    }
}
