package com.example.konrad.gotowanie.JSON;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.konrad.gotowanie.Activities.LoggingActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2018-01-15.
 */

public class RegisterCheckJSON extends AsyncTask<String,Integer,String> {
    private Context context;

    private String username;
    private String password;
    private String email;
    private String returnInfo;

    private static final String TAG_PARAMS = "params";
    private static final String TAG_CERROR = "connectionError";
    private static final String TAG_QERROR = "queryError";
    private static final String TAG_LOGOCC = "loginOccupied";
    private static final String TAG_EMAOCC = "emailOccupied";

    public RegisterCheckJSON(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            username = arg0[0];
            password = arg0[1];
            email = arg0[2];
            returnInfo = "UnRegistered";
            List<NameValuePair> params = new ArrayList<>();
            ParserJSON jParser = new ParserJSON(context);
            String link = "http://46.242.178.181/gotowanie/registerDataCheck.php";
            params.add(new BasicNameValuePair("login", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("email", email));
            JSONObject json = jParser.makeHttpRequest(link, "GET", params);
            Log.d("RegisterCheckJSON", json.toString());
            if (json.has(TAG_PARAMS)){
                Log.d("RegisterCheckJSON","parametry poprawne");
                returnInfo = "Registered";

                if(json.has(TAG_CERROR)){
                    Log.d("RegisterCheckJSON",json.getString(TAG_CERROR));
                    returnInfo = "Connect";
                }
                if(json.has(TAG_QERROR)){
                    Log.d("RegisterCheckJSON",json.getString(TAG_QERROR));
                    returnInfo = "Query";
                }
                if(json.has(TAG_LOGOCC)){
                    Log.d("RegisterCheckJSON","Login zajety");
                    returnInfo = "Login";
                }
                if(json.has(TAG_EMAOCC)){
                    Log.d("RegisterCheckJSON","Email zajety");
                    returnInfo = "Email";
                }
            }
            else{
                Log.d("RegisterCheckJSON","rejestracja nieudana");
                Toast toast = Toast.makeText(context, "Wpisz poprawne dane", Toast.LENGTH_LONG);
                toast.show();
                returnInfo = "Params";
            }
            return json.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    protected void onPostExecute(String result){
        if(returnInfo.equals("Registered")){
            Log.d("RegisterCheckJSON","Zarejestrowany");
            new RegisterJSON(context).execute(username,password,email);
            Intent intent = new Intent(context, LoggingActivity.class);
            context.startActivity(intent);
        }else {
            Toast toast = Toast.makeText(context, "Taki " + returnInfo + " juz istnieje", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}

