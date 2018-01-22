package com.example.konrad.gotowanie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.konrad.gotowanie.JSON.LoggingJSON;
import com.example.konrad.gotowanie.R;

public class LoggingActivity extends AppCompatActivity {

    private EditText loginField,passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        loginField = findViewById(R.id.login);
        passwordField = findViewById(R.id.password);

    }

    public void login(View view){
        String username = loginField.getText().toString();
        String password = passwordField.getText().toString();
        new LoggingJSON(this).execute(username,password);

    }

    public void rejestracja(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}








