package com.example.konrad.gotowanie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Logowanie extends AppCompatActivity {

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
        new SigninActivity(this).execute(username,password);

    }
}








