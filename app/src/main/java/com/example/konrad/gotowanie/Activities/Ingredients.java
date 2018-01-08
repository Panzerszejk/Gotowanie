package com.example.konrad.gotowanie.Activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.example.konrad.gotowanie.JSON.IngredientsJSON;
import com.example.konrad.gotowanie.R;

public class Ingredients extends AppCompatActivity {
    private ListView list ;
    private ArrayAdapter<String> adapter ;
    private ArrayList<String> prodArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skladniki);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.lista);

        prodArray = new ArrayList<>();

        new IngredientsJSON(this,prodArray).execute();
    }

    public void listIngredients(View view){
        adapter = new ArrayAdapter<String>(this, R.layout.element, prodArray);
        list.setAdapter(adapter);
    }

}
