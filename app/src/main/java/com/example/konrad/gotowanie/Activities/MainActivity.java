package com.example.konrad.gotowanie.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.konrad.gotowanie.JSON.LoginCheckJSON;
import com.example.konrad.gotowanie.JSON.RecipeCheckJSON;
import com.example.konrad.gotowanie.JSON.RecipeGetJSON;
import com.example.konrad.gotowanie.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton loggingButton,ingredientsButton,searchButton;
    private EditText searchField;
    private ListView searchResults;
    String idFromCookie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggingButton = findViewById(R.id.logging);
        ingredientsButton = findViewById(R.id.ingredients);
        searchButton = findViewById(R.id.searchButton);
        searchField = findViewById(R.id.searchField);
        searchResults = findViewById(R.id.serarchResult);

        SharedPreferences sharedPref = getSharedPreferences("cookies", Context.MODE_PRIVATE);
        idFromCookie = sharedPref.getString("id", null);
        if(idFromCookie==null){
            Log.d("MainActivity: Cookie:","Uzytkownik nie zalogowany");
        }else {
            Log.d("MainActivity: Cookie:",idFromCookie);
        }

    }

    public void search(View view){
        final String search = searchField.getText().toString();
        if(search.equals("")){
            searchResults.setAdapter(new ArrayAdapter<String>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1
                    ));
        }else{
            new RecipeCheckJSON(this,searchResults).execute(search);
        }
        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos,   long id) {
                String clickedTitle = (String) searchResults.getItemAtPosition(pos);
                new RecipeGetJSON(MainActivity.this).execute(clickedTitle);
            }
        });
    }

    public void openIngredients(View view){
        Intent intent = new Intent(getApplicationContext(), IngredientsActivity.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        if(idFromCookie == null){
            Log.d("MainActivity: Cookie:","Nie ma cookie");
            Intent intent = new Intent(getApplicationContext(), LoggingActivity.class);
            startActivity(intent);
        }
        else{
            Log.d("MainActivity: Cookie:","Sprawdzanie cookie");
            new LoginCheckJSON(this).execute(idFromCookie);
        }

    }

    public void openRecipeAdd(View view){
        Intent intent = new Intent(getApplicationContext(), RecipeAddActivity.class);
        startActivity(intent);
    }

}
