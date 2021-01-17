package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class TextLayoutActivity extends AppCompatActivity {

    private TextInputLayout emailET, usernameET, passET;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_layout);

        emailET = findViewById(R.id.emailET);
        usernameET = findViewById(R.id.usernameET);
        passET = findViewById(R.id.passwordET);

    }

    boolean validateEmail() {
        String email = emailET.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Field can not be empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Not a valid email address!");
            return false;
        } else {
            emailET.setError(null);
            // emailET.setErrorEnabled(false);//to remove the space for error
            return true;
        }
    }

    boolean validateUsername() {
        String username = usernameET.getEditText().getText().toString().trim();

        if (username.isEmpty()) {
            usernameET.setError("Field can not be empty!");
            return false;
        } else if (username.length() > 15) {
            usernameET.setError("Username too long");
            return false;
        } else {
            usernameET.setError(null);
            return true;
        }
    }

    boolean validatePassword() {
        String password = passET.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            passET.setError("Field can not be empty!");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passET.setError("Password too weak");
            return false;
        } else {
            passET.setError(null);
            return true;
        }
    }

    public void confirmBtn(View view) {
        //so all method will called, and show the error msgs
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }
        String input = "Email: " + emailET.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + usernameET.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + passET.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

    }
}
