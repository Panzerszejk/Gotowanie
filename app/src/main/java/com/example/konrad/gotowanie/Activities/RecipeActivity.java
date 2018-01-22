package com.example.konrad.gotowanie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.konrad.gotowanie.R;

public class RecipeActivity extends AppCompatActivity {

    TextView title,category,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        title = findViewById(R.id.title);
        category = findViewById(R.id.category);
        description = findViewById(R.id.description);

        Intent intent = getIntent();

        String recipeTitle = intent.getStringExtra("title");
        String recipeCategory = intent.getStringExtra("cat");
        String recipeDescription = intent.getStringExtra("desc");

        title.setText(recipeTitle);
        category.setText(recipeCategory);
        description.setText(recipeDescription);
    }
}
