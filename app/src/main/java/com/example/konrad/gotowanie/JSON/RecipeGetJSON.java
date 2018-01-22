package com.example.konrad.gotowanie.JSON;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.example.konrad.gotowanie.Activities.RecipeActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2018-01-20.
 */

public class RecipeGetJSON extends AsyncTask<String,Integer,String> {
    private Context context;

    private static final String TAG_TITLE = "title";
    private static final String TAG_CAT = "cat";
    private static final String TAG_DESC = "desc";
    private static final String TAG_PARAMS = "params";

    String recipeTitle;
    String recipeCategory;
    String recipeDescription;

    public RecipeGetJSON(Context context) {
        this.context = context;
    }

    //protected void onPreExecute(){
    //}

    @Override
    protected String doInBackground(String... arg0) {
        try {
            ParserJSON jParser = new ParserJSON(context);
            String title = arg0[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            String link = "http://46.242.178.181/gotowanie/recipeGet.php";
            params.add(new BasicNameValuePair("title", title));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("Otrzymana tablica JSON:", json.toString());
            if (json.has(TAG_PARAMS)) {
                recipeTitle = json.getString(TAG_TITLE);
                recipeCategory = json.getString(TAG_CAT);
                recipeDescription = json.getString(TAG_DESC);
            }
            return json.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent= new Intent(context, RecipeActivity.class);
        intent.putExtra("title", recipeTitle);
        intent.putExtra("cat", recipeCategory);
        intent.putExtra("desc", recipeDescription);
        context.startActivity(intent);
    }
}