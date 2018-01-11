package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
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

    private SharedPreferences ingName;
    private SharedPreferences ingCount;
    private static final String PREF_NAME = "prefName";
    private static final String PREF_COUNT = "prefCount";
    private static final String TAG_PRODUCTS= "products";
    private static final String TAG_NAME= "name";
    private static final String TAG_ID= "id";

    public IngredientsJSON(Context context, ArrayList<String> prodArray,ListView list) {
        this.context = context;
        this.prodArray=prodArray;
        this.list=list;
    }

    //protected void onPreExecute(){
    //}

    @Override
    protected String doInBackground(String... arg0) {
        try{
            ParserJSON jParser = new ParserJSON();
            ingName = context.getSharedPreferences(PREF_NAME , Activity.MODE_PRIVATE);
            ingCount = context.getSharedPreferences(PREF_COUNT , Activity.MODE_PRIVATE);

            String link = "http://46.242.178.181/gotowanie/list.php";
            JSONObject json = jParser.makeHttpRequest(link, "GET", null);
            Log.d("Otrzymana tablica JSON:", json.toString());
            products = json.getJSONArray(TAG_PRODUCTS);

            SharedPreferences.Editor prefNameEditor = ingName.edit();
            SharedPreferences.Editor prefCountEditor = ingCount.edit();


            for (int i = 0; i < products.length(); i++) {
                JSONObject c = products.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                String count = c.getString(TAG_ID);
                prefNameEditor.putString(Integer.toString(i),name);
                prefCountEditor.putString(Integer.toString(i),count);

                prodArray.add(name);
            }

            prefNameEditor.commit();
            prefCountEditor.commit();

            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){

    }

}
