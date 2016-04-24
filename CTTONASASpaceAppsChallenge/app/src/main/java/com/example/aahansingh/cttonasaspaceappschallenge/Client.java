package com.example.aahansingh.cttonasaspaceappschallenge;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aahansingh on 24/04/16.
 */
class Client extends AsyncTask<String,Void,String> {
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder str = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    str.append(line).append("\n");
                }
                br.close();
                return toString();
            } finally {
                conn.disconnect();
                System.out.print(toString()+"\n");
                return "finally";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "fail";
    }
    protected void onPostExecute(String p) {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

