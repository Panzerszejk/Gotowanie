package com.example.konrad.gotowanie.Activities;

import java.util.ArrayList;

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
    private ArrayList<String> prodArray;
    private String[] ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skladniki);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.lista);

        prodArray = new ArrayList<>();
        ar=new String[14];
        /*ar = new ArrayList<>();

        for(int i=0;i<14;i++) {
            ar[i]
        }*/

        new IngredientsJSON(this,prodArray,list).execute();
    }

    public void listIngredients(View view){
        ArrayAdapter<String> adapter ;
        adapter = new IngredientsArrayAdapter(this,ar);
        list.setAdapter(adapter);
    }



}
