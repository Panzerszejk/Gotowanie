package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-07.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
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


    public LoggingJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            boolean success;
            String error;
            String username = arg0[0];
            String password = arg0[1];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            ParserJSON jParser = new ParserJSON();
            String link = "http://46.242.178.181/gotowanie/logging.php";
            params.add(new BasicNameValuePair("login", username));
            params.add(new BasicNameValuePair("password", password));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("logs", json.toString());
            success = json.getBoolean(TAG_PARAMS);
            if (success){
                Log.d("tag_params","logowanie udane");
            }
            else{
                Log.d("tag_params","logowanie nieudane");
            }
            success = json.getBoolean(TAG_LOGGED);
            if(success){
                Log.d("tag_logged","login ok");
            }
            else{
                Log.d("tag_logged","login nie ok");
            }
            error=json.getString(TAG_CERROR);
            if(error.equals("OK")){
                Log.d("tag_cerror",error);
            }
            error=json.getString(TAG_QERROR);
            if(error.equals("OK")){
                Log.d("tag_querror",error);
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
