package com.example.aahansingh.cttonasaspaceappschallenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity{


    String jdata="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather("abc", "def");
            }
        });
    }


     public void getWeather(String city,String time){
         String api = "e6a50a16eee18b905108ecbc1f5c7335cal";
         String url= "https://api.forecast.io/forecast/e6a50a16eee18b905108ecbc1f5c7335/11.13,75.95";
         new Fetcher().execute(url,null,null);
         TextView t=(TextView)findViewById(R.id.txt);
         t.setText(jdata);

     }



    class Fetcher extends AsyncTask<String,Void,Void>{
        protected Void doInBackground (String... urls){
            //String api = "e6a50a16eee18b905108ecbc1f5c7335cal";
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
                    jdata=str.toString();
                } finally {
                    conn.disconnect();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
 }
