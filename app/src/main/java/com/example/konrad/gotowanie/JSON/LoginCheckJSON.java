package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-14.
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.konrad.gotowanie.Activities.LoggingActivity;
import com.example.konrad.gotowanie.Activities.MainActivity;


public class LoginCheckJSON extends AsyncTask<String,Integer,String>{
    private Context context;
    private boolean loginOk = false;

    private static final String TAG_PARAMS = "params";
    private static final String TAG_CERROR = "connectionError";
    private static final String TAG_QERROR = "queryError";
    private static final String TAG_LOGGED = "logged";

    public LoginCheckJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String id = arg0[0];
            List<NameValuePair> params = new ArrayList<>();
            ParserJSON jParser = new ParserJSON(context);
            String link = "http://46.242.178.181/gotowanie/loginCheck.php";
            params.add(new BasicNameValuePair("id", id));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("logs", json.toString());
            if (json.has(TAG_PARAMS)){
                Log.d("LoginCheckJSON:","logowanie udane");
                if(json.has(TAG_LOGGED)){
                    Log.d("LoginCheckJSON:","login ok");
                    loginOk = true;
                }
                else{
                    Log.d("LoginCheckJSON:","login nie ok");
                }
                if(json.has(TAG_CERROR)){
                    Log.d("LoginCheckJSON:",json.getString(TAG_CERROR));
                }
                if(json.has(TAG_QERROR)){
                    Log.d("LoginCheckJSON:",json.getString(TAG_QERROR));
                }
            }
            else{
                Log.d("LoginCheckJSON:","logowanie nieudane");
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        if(loginOk){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else{
            Intent intent = new Intent(context, LoggingActivity.class);
            context.startActivity(intent);
        }
    }
}
