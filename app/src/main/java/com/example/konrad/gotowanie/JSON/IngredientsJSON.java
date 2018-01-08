package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class IngredientsJSON extends AsyncTask<String,Integer,String> {
    private Context context;

    JSONArray products = null;
    ArrayList<String> prodArray;

    private static final String TAG_PRODUCTS= "products";
    private static final String TAG_NAME= "name";

    public IngredientsJSON(Context context, ArrayList<String> prodArray) {
        this.context = context;
        this.prodArray=prodArray;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            JSONParser jParser = new JSONParser();
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

    /*
    @Override
    protected void onPostExecute(String result){
        this.statusField.setText("Login Successful");
        this.roleField.setText(result);
    }*/

}
