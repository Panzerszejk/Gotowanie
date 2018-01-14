package com.example.konrad.gotowanie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.konrad.gotowanie.R;

public class Register extends AppCompatActivity {

    private EditText loginField,passwordField,confirmField,emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        loginField = findViewById(R.id.Login);
        passwordField = findViewById(R.id.Haslo);
        emailField = findViewById(R.id.Email);
        confirmField = findViewById(R.id.Confirm);

    }

    public void registerClick(View view){
        String username = loginField.getText().toString();
        String password = passwordField.getText().toString();
        String email = emailField.getText().toString();
        //new LoggingJSON(this).execute(username,password);
        Intent intent = new Intent(getApplicationContext(), LoggingActivity.class);
        startActivity(intent);
    }

}
