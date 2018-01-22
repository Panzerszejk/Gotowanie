package com.example.konrad.gotowanie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.konrad.gotowanie.JSON.RecipeAddJSON;
import com.example.konrad.gotowanie.R;

public class RecipeAddActivity extends AppCompatActivity {

    EditText recipeTitleField,recipeCategoryField,recipeDescriptionField;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add);

        recipeTitleField = findViewById(R.id.titleField);
        recipeCategoryField = findViewById(R.id.categoryField);
        recipeDescriptionField = findViewById(R.id.descriptionField);
        submitButton = findViewById(R.id.submit);

    }

    public void submit(View view){
        String title = recipeTitleField.getText().toString();
        String category = recipeCategoryField.getText().toString();
        String description = recipeDescriptionField.getText().toString();

        new RecipeAddJSON(this).execute(title, category, description);
    }

}
