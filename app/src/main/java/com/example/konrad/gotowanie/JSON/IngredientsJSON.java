package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.konrad.gotowanie.ArrayAdapters.IngredientsArrayAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class IngredientsJSON extends AsyncTask<String,Integer,String> {
    private Context context;
    private ListView list;

    JSONArray products = null;
    private ArrayList<ArrayList<String>> productsArray;

    private SharedPreferences ingName;
    private SharedPreferences ingCount;
    private String idFromCookie;
    private static final String PREF_NAME = "prefName";
    private static final String PREF_COUNT = "prefCount";
    private static final String TAG_PRODUCTS= "products";
    private static final String TAG_NAME= "name";
    private static final String TAG_COUNT= "count";

    public IngredientsJSON(Context context, ArrayList<ArrayList<String>> productsArray,ListView list) {
        this.context = context;
        this.productsArray =productsArray;
        this.list=list;
    }

    //protected void onPreExecute(){
    //}

    @Override
    protected String doInBackground(String... arg0) {
        try{
            ParserJSON jParser = new ParserJSON(context);
            String id = arg0[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            ingName = context.getSharedPreferences(PREF_NAME , Activity.MODE_PRIVATE);
            ingCount = context.getSharedPreferences(PREF_COUNT , Activity.MODE_PRIVATE);

            String link = "http://46.242.178.181/gotowanie/fridge.php";
            params.add(new BasicNameValuePair("id", id));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("Otrzymana tablica JSON:", json.toString());
            products = json.getJSONArray(TAG_PRODUCTS);

            SharedPreferences.Editor prefNameEditor = ingName.edit();
            SharedPreferences.Editor prefCountEditor = ingCount.edit();

            prefNameEditor.clear();
            prefCountEditor.clear();

            SharedPreferences sharedPref = context.getSharedPreferences("cookies", Context.MODE_PRIVATE);
            idFromCookie = sharedPref.getString("id", null);

            if(idFromCookie!=null) {

                productsArray.clear();
                productsArray.add(new ArrayList<String>());
                productsArray.add(new ArrayList<String>());

                prefNameEditor.putString("ID", idFromCookie);
                prefCountEditor.putString("ID", idFromCookie);

                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    String name = c.getString(TAG_NAME);
                    String count = c.getString(TAG_COUNT);
                    prefNameEditor.putString(Integer.toString(i), name);
                    prefCountEditor.putString(Integer.toString(i), count);

                    productsArray.get(0).add(name);
                    productsArray.get(1).add(count);
                }

                prefNameEditor.commit();
                prefCountEditor.commit();
            }else {
            Log.d("IngredientsJSON","Cookie puste, Użytkownik nie zalogowany");
                Toast notLogged = Toast.makeText(context, "Nie jestes zalogowany", Toast.LENGTH_SHORT);
                notLogged.show();
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        ArrayAdapter<String> adapter;
        adapter = new IngredientsArrayAdapter((Activity)context,productsArray);
        list.setAdapter(adapter);
    }

}
