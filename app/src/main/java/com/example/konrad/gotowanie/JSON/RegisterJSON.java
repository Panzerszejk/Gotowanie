package com.example.konrad.gotowanie.JSON;

/**
 * Created by Konrad on 2018-01-08.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RegisterJSON extends AsyncTask<String,Integer,String> {
    private Context context;

    private static final String TAG_PARAMS = "params";
    private static final String TAG_CERROR = "connectionError";
    private static final String TAG_QERROR = "queryError";

    public RegisterJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String username = arg0[0];
            String password = arg0[1];
            String email = arg0[2];
            List<NameValuePair> params = new ArrayList<>();
            ParserJSON jParser = new ParserJSON(context);
            String link = "http://46.242.178.181/gotowanie/register.php";
            params.add(new BasicNameValuePair("login", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("email", email));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("RegisterJSON", json.toString());
            if (json.has(TAG_PARAMS)){
                Log.d("RegisterJSON","parametry poprawne");
                if(json.has(TAG_CERROR)){
                    Log.d("RegisterJSON",json.getString(TAG_CERROR));
                }
                if(json.has(TAG_QERROR)){
                    Log.d("RegisterJSON",json.getString(TAG_QERROR));
                }
            }
            else{
                Log.d("RegisterJSON","rejestracja nieudana");
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    protected void onPostExecute(String result){

    }
}
