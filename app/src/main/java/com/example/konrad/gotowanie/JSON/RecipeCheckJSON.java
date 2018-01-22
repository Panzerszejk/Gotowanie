package com.example.konrad.gotowanie.JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2018-01-20.
 */

public class RecipeCheckJSON extends AsyncTask<String,Integer,String> {
    private Context context;
    private ListView list;

    JSONArray products = null;
    private ArrayList<String> resultsArray = null;

    private static final String TAG_TITLE = "title";

    public RecipeCheckJSON(Context context, ListView list) {
        this.context = context;
        this.list = list;
    }

    //protected void onPreExecute(){
    //}

    @Override
    protected String doInBackground(String... arg0) {
        try {
            ParserJSON jParser = new ParserJSON(context);
            String title = arg0[0];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String link = "http://46.242.178.181/gotowanie/search.php";
            params.add(new BasicNameValuePair("title", title));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("Otrzymana tablica JSON", json.toString());

            if (json.has(TAG_TITLE)) {
                Log.d("RecipeCheckJSON", "nazwa przepisu zwrocona");
                resultsArray = new ArrayList<>();
                JSONArray titleArray=json.getJSONArray("title");
                for (int i = 0; i < titleArray.length(); i++) {
                    String recTitle = titleArray.getString(i);
                    Log.d("RecipeCheckJSON",recTitle);
                    resultsArray.add(recTitle);
                }
            }
            return json.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(resultsArray==null) {
            resultsArray = new ArrayList<>();
            resultsArray.add("Brak wyników wyszukiwania");
        }
        list.setAdapter(new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                resultsArray
        ));
    }
}