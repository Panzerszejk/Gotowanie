package com.example.konrad.gotowanie.Activities;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import com.example.konrad.gotowanie.JSON.IngredientsJSON;
import com.example.konrad.gotowanie.R;

public class Ingredients extends AppCompatActivity {
    private ListView list ;
    private ArrayList<ArrayList<String>> productsArray;
    private SharedPreferences ingPrefName;
    private SharedPreferences ingPrefCount;
    private static final String PREF_NAME = "prefName";
    private static final String PREF_COUNT = "prefCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skladniki);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.lista);

        productsArray = new ArrayList<>();
        productsArray.add(new ArrayList<String>());
        productsArray.add(new ArrayList<String>());

        getSharedPrefValues();

        ArrayAdapter<String> adapter;
        adapter = new IngredientsArrayAdapter(this,productsArray);
        list.setAdapter(adapter);
    }

    public void listIngredients(View view){
        new IngredientsJSON(this,productsArray, list).execute("1"); //zamiast 1 to cookie z sesji
    }

    public void getSharedPrefValues(){
        ingPrefName = this.getSharedPreferences(PREF_NAME , Activity.MODE_PRIVATE);
        ingPrefCount = this.getSharedPreferences(PREF_COUNT , Activity.MODE_PRIVATE);

        Map<String,?> nameMap = ingPrefName.getAll();
        Map<String,?> countMap = ingPrefCount.getAll();

        for(int i=0;i<nameMap.size();i++){
            String textFromPreferences = (String)nameMap.get(Integer.toString(i));
            String countFromPreferences = (String)countMap.get(Integer.toString(i));

            productsArray.get(0).add(textFromPreferences);
            productsArray.get(1).add(countFromPreferences);
        }

    }


}
