package com.example.konrad.gotowanie.JSON;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ParserJSON {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    Context context;

    // constructor
    public ParserJSON(Context context) {
        this.context = context;
    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> params) {

        // Making HTTP request
        try {
            // check for request method
            if(method.equals("POST")){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

                CookieStore cookieStore = httpClient.getCookieStore();
                List<Cookie> cookies = cookieStore.getCookies();
                if(!cookies.isEmpty()){
                    SharedPreferences sharedPref = context.getSharedPreferences("cookies", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    for (Cookie cookie: cookies) {
                        editor.putString(cookie.getName(), cookie.getValue());
                        editor.apply();
                    }
                }

            }else if(method.equals("GET")){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                if(params!=null) { //jeśli nie ma parametrów to musi być ten warunek bo inaczej nie chce isc dalej
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

                CookieStore cookieStore = httpClient.getCookieStore();
                List<Cookie> cookies = cookieStore.getCookies();
                if(!cookies.isEmpty()){
                    SharedPreferences sharedPref = context.getSharedPreferences("cookies", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    for (Cookie cookie: cookies) {
                        editor.putString(cookie.getName(), cookie.getValue());
                        editor.apply();
                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}