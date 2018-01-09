package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.konrad.gotowanie.Activities.IngredientsArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class IngredientsJSON extends AsyncTask<String,Integer,String> {
    private Context context;
    private ListView list;

    JSONArray products = null;
    private ArrayList<String> prodArray;


    private static final String TAG_PRODUCTS= "products";
    private static final String TAG_NAME= "name";

    public IngredientsJSON(Context context, ArrayList<String> prodArray,ListView list) {
        this.context = context;
        this.prodArray=prodArray;
        this.list=list;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            ParserJSON jParser = new ParserJSON();
            String link = "http://46.242.178.181/gotowanie/list.php";
            JSONObject json = jParser.makeHttpRequest(link, "GET", null);
            Log.d("Otrzymana tablica JSON:", json.toString());
            products = json.getJSONArray(TAG_PRODUCTS);

            for (int i = 0; i < products.length(); i++) {
                JSONObject c = products.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                prodArray.add(name);
            }

            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        ArrayAdapter<String> adapter ;
        adapter = new IngredientsArrayAdapter((Activity)context,prodArray);
        list.setAdapter(adapter);
    }

}
