package com.example.konrad.gotowanie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton loggingButton,ingredientsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggingButton = findViewById(R.id.logging);
        ingredientsButton = findViewById(R.id.ingredients);
    }

    public void openIngredients(View view){
        Intent intent = new Intent(getApplicationContext(), Skladniki.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        Intent intent = new Intent(getApplicationContext(), Logowanie.class);
        startActivity(intent);
    }
}
