package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


//show badge(counter or notification) on cart
//also you can use https://github.com/nikartm/Image-Support
public class BadgeActivity extends AppCompatActivity {

    TextView textCartItemView;//this TV is inside the layout of the item_cart
    int mCartItemCount = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.item_cart);

        //get TV inside layout of item_cart
        View actionView = menuItem.getActionView();
        textCartItemView =actionView.findViewById(R.id.cart_badge);

        setUpBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something when click on it
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_cart: {
                Toast.makeText(this, "Shopping Cart", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpBadge() {

        if(textCartItemView != null){
            if(mCartItemCount ==0){
                if(textCartItemView.getVisibility() != View.GONE){
                    textCartItemView.setVisibility(View.GONE);
                }
            }else{
                textCartItemView.setText(String.valueOf(Math.min(mCartItemCount, 99)));//if more than 99, make it show 99
                if (textCartItemView.getVisibility() != View.VISIBLE) {
                    textCartItemView.setVisibility(View.VISIBLE);
                }
            }

        }

    }
}
