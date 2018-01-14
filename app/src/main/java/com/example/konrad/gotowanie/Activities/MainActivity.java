package com.example.konrad.gotowanie.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.konrad.gotowanie.JSON.LoginCheckJSON;
import com.example.konrad.gotowanie.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton loggingButton,ingredientsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggingButton = findViewById(R.id.logging);
        ingredientsButton = findViewById(R.id.ingredients);


        SharedPreferences sharedPref = getSharedPreferences("cookies", Context.MODE_PRIVATE);
        String idFromCookie = sharedPref.getString("id", "Brak cookie");
        if(idFromCookie.equals("Brak cookie")){
            Intent intent = new Intent(getApplicationContext(), Logging.class);
            startActivity(intent);
        }
        else{
            new LoginCheckJSON(this).execute(idFromCookie);
        }
        Log.d("Stan cookie", idFromCookie);
    }

    public void openIngredients(View view){
        Intent intent = new Intent(getApplicationContext(), Ingredients.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        Intent intent = new Intent(getApplicationContext(), Logging.class);
        startActivity(intent);
    }
}
