package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.konrad.gotowanie.Activities.MainActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



public class LoggingJSON extends AsyncTask<String,Integer,String> {
    private TextView statusField,roleField;
    private Context context;

    private static final String TAG_PARAMS = "params";
    private static final String TAG_CERROR = "connectionerror";
    private static final String TAG_QERROR = "queryerror";
    private static final String TAG_LOGGED = "logged";

    private boolean loginOK = false;

    public LoggingJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String username = arg0[0];
            String password = arg0[1];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            ParserJSON jParser = new ParserJSON(context);
            String link = "http://46.242.178.181/gotowanie/logging.php";
            params.add(new BasicNameValuePair("login", username));
            params.add(new BasicNameValuePair("password", password));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("logs", json.toString());
            if (json.has(TAG_PARAMS)){
                Log.d("LoggingJSON","logowanie udane");
                if(json.has(TAG_LOGGED)){
                    Log.d("LoggingJSON","login ok");
                    loginOK = true;
                }
                else{
                    Log.d("LoggingJSON","login nie ok");
                }
                if(json.has(TAG_CERROR)){
                    Log.d("LoggingJSON",json.getString(TAG_CERROR));
                }
                if(json.has(TAG_QERROR)){
                    Log.d("LoggingJSON",json.getString(TAG_QERROR));
                }
            }
            else{
                Log.d("LoggingJSON","logowanie nieudane");
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (loginOK) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else{
            //TODO: Informacja o błędnych danych logowania.
            SharedPreferences sharedPref = context.getSharedPreferences("cookies", Context.MODE_PRIVATE);
            sharedPref.edit().clear().apply();
        }
    }
}
