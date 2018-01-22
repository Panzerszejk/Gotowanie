package com.example.konrad.gotowanie.JSON;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.konrad.gotowanie.Activities.LoggingActivity;
import com.example.konrad.gotowanie.Activities.MainActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2018-01-20.
 */

public class RecipeAddJSON extends AsyncTask<String,Integer,String> {
    private Context context;

    private static final String TAG_PARAMS = "params";
    private static final String TAG_CERROR = "connectionerror";
    private static final String TAG_QERROR = "queryerror";


    public RecipeAddJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String title = arg0[0];
            String category = arg0[1];
            String description = arg0[2];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            ParserJSON jParser = new ParserJSON(context);
            String link = "http://46.242.178.181/gotowanie/recipeAdd.php";
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("cat", category));
            params.add(new BasicNameValuePair("desc", description));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("logs", json.toString());
            if (json.has(TAG_PARAMS)){
                Log.d("RecipeAddJSON","parametry poprawne");
                if(json.has(TAG_CERROR)){
                    Log.d("RecipeAddJSON",json.getString(TAG_CERROR));
                }
                if(json.has(TAG_QERROR)){
                    Log.d("RecipeAddJSON",json.getString(TAG_QERROR));
                }
            }
            else{
                Log.d("RecipeAddJSON","parametry nie poprawne");
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //TODO sprawdzenie powodzenia
        Toast toast = Toast.makeText(context, "Przepis dodany", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        Log.d("RecipeAddJSON","dodano przepis");
    }
}
