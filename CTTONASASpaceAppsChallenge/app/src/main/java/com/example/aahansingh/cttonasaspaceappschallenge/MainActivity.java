package com.example.aahansingh.cttonasaspaceappschallenge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    String weatherInfo="";
    String flightInfo="";
    String locationInfo="";
    String timezoneInfo="";
    JSONObject fl,we,loc;
    int val=1;
    String we_key = "e6a50a16eee18b905108ecbc1f5c7335";
    String appid="7f121ed9";
    String fl_key="c8f3f7b3a558e0f46ae10bb39c2fc98d";
    String src;
    String des;
    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    String fl_no="";
    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;
    String flights[],departureTime[],arrtime[],time;
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser ;
    String lat,lon,gmt_offset;
    String tmpf,dwpf,reth,sknt,alth;//F,F,*100,*0.868976,*0.0295301
    String server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        /***********************************
         *
         *
         *
         * INIT SERVER
         *
         *
         */
        server="www.www.www";


        /** Capture our View elements */
        pDisplayDate = (TextView) findViewById(R.id.displayDate);
        pPickDate = (Button) findViewById(R.id.pickDate);

        /** Listener for click event of the button */
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);

            }
        });

        /** Get the current date */
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        /** Display the current date in the TextView */
        updateDisplay();
        Button select = (Button)findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_no=((EditText)findViewById(R.id.fl_no)).getText().toString();
                src=((EditText)findViewById(R.id.Source)).getText().toString();
                System.out.print(src+"\n");
                getLocn();
            }
        });
    }
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }

    class Fetcher extends AsyncTask<String,Void,String>{
        protected String doInBackground (String... urls){
            //String api = "e6a50a16eee18b905108ecbc1f5c7335cal";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                try {
                    if(val==1){
                        System.out.println("****************WEATHER*************");
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder str = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            str.append(line).append("\n");
                        }
                        br.close();
                        weatherInfo=str.toString();}
                    if (val == 0){
                        System.out.println("****************FLIGHT*************");
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder str = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            str.append(line).append("\n");
                        }
                        br.close();
                        flightInfo = str.toString();}
                    if(val==3){
                        System.out.println("****************LOCN************");
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder str = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            str.append(line).append("\n");
                        }
                        br.close();
                        locationInfo = str.toString();
                        /*String x1="",x2="",y1="",y2="",orientationLat="",orientationLon="";
                        gmt_offset="";
                        lat="";
                        lon="";
                        String s=str.toString();
                        System.out.println(s);
                        int i;
                        i=s.indexOf("&lt;LatitudeDegree&gt;");
                        System.out.print(i+"********\n");
                        for(int j=i+"&lt;LatitudeDegree&gt;".length();s.charAt(j)!='&';j++){
                            x1+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;LatitudeMinute&gt;");
                        for(int j=i+"&lt;LatitudeMinute&gt;".length();s.charAt(j)!='&';j++){
                            y1+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;LongitudeDegree&gt;");
                        for(int j=i+"&lt;LongitudeDegree&gt;".length();s.charAt(j)!='&';j++){
                            x2+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;LongitudeMinute&gt;");
                        for(int j=i+"&lt;LongitudeMinute&gt;".length();s.charAt(j)!='&';j++){
                            y2+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;LatitudeNpeerS&gt;");
                        for(int j=i+"&lt;LatitudeNpeerS&gt;".length();s.charAt(j)!='&';j++){
                            orientationLat+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;LongitudeEperW&gt;");
                        for(int j=i+"&lt;LongitudeEperW&gt;".length();s.charAt(j)!='&';j++){
                            orientationLon+=s.charAt(j);
                        }
                        i=s.indexOf("&lt;GMTOffset&gt;");
                        for(int j=i+"&lt;GMTOffset&gt;".length();s.charAt(j)!='&';j++){
                            gmt_offset+=s.charAt(j);
                        }
                        System.out.println("x1:" + x1 + "\ny1:" + y1 + "\nx2:" + x2 + "\ny2:" + y2 + "\nGMT:" + gmt_offset);
                        float y = Float.parseFloat(y1);
                        y = y / 60;
                        y1=String.valueOf(y);
                        if(orientationLat.equalsIgnoreCase("S"))
                            lat="-";

                        y = Float.parseFloat(y2);
                        y = y/60;
                        y2 = String.valueOf(y);
                        if(orientationLon.equalsIgnoreCase("W")) {
                            lon = "-";
                            gmt_offset="-"+gmt_offset;
                        }
                        if(orientationLon.equalsIgnoreCase("E")) {
                            gmt_offset = gmt_offset.substring(1, gmt_offset.length());
                            gmt_offset="+"+gmt_offset;
                        }
                        System.out.println(gmt_offset);
                        int dot;
                        switch (gmt_offset.length()){
                            case 2:
                                gmt_offset=gmt_offset.charAt(0)+"0"+gmt_offset+"00";
                                break;
                            case 3:
                                gmt_offset=gmt_offset.charAt(0)+gmt_offset+"00";
                                break;
                            case 4:
                                 dot = gmt_offset.indexOf('.');
                                if(dot==2)
                                {
                                    char k = gmt_offset.charAt(dot+1);
                                    double rem = ((k-48) /10.0)*60;
                                    System.out.print(rem+"\n");
                                    gmt_offset=gmt_offset.charAt(0)+"0"+gmt_offset.charAt(1)+String.valueOf(rem).substring(0,2);
                                }
                                break;
                            case 5:
                                 dot = gmt_offset.indexOf('.');
                                if(dot==2)
                                {
                                    String k = gmt_offset.substring(3);
                                    double rem = (Float.parseFloat(k)/10)*60;
                                    gmt_offset=gmt_offset.charAt(0)+"0"+gmt_offset.charAt(1)+String.valueOf(rem).substring(0,2);
                                }
                                else
                                {
                                    char k = gmt_offset.charAt(dot+1);
                                    double rem = ((k-42) /10)*60;
                                    gmt_offset=gmt_offset.charAt(0)+gmt_offset.substring(1, 2)+String.valueOf(rem).substring(0,2);
                                }
                        }*/
                    }
                    if(val==4){
                        System.out.println("****************LOCN************");
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder str = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            str.append(line).append("\n");
                        }
                        br.close();
                        timezoneInfo = str.toString();
                    }

                } finally {
                    conn.disconnect();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }

        protected void onPostExecute(String p) {
            try {
                if (val == 1) {
                    we = new JSONObject(weatherInfo);
                    JSONObject a = we.getJSONObject("currently");
                    tmpf=a.getString("temperature");
                    dwpf=a.getString("dewPoint");
                    reth=a.getString("humidity");
                    reth=reth.substring(2);
                    double tmp=Double.parseDouble(a.getString("windSpeed"));
                    tmp*=0.868976;
                    int tmp2=(int)tmp;
                    sknt=String.valueOf(tmp2);
                    tmp=Double.parseDouble(a.getString("pressure"));
                    tmp*=0.0295301;
                    tmp2=(int)tmp;
                    alth=String.valueOf(tmp2);
                    System.out.println(tmpf);
                    System.out.println(dwpf);
                    System.out.println(reth);
                    System.out.println(sknt);
                    System.out.println(alth);
                    Toast.makeText(getApplicationContext(),tmpf+"F",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),dwpf+"F",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),reth,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),sknt+"Knots",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),alth+"Inch",Toast.LENGTH_LONG).show();

                    String url=server+"?tmpf="+tmpf+"&dwpf="+dwpf+"&reth="+reth+"&sknt="+sknt+"&alth="+alth;
                    String r= String.valueOf(new Client().execute(url));
                    TextView e=(TextView)findViewById(R.id.Result);
                    e.setText(r);
                }
                if(val==0) {
                    fl = new JSONObject(flightInfo);
                    count();
                    fl_no=((EditText)findViewById(R.id.fl_no)).getText().toString();
                    time=getDepartureTime(fl_no);
                    Toast.makeText(getApplicationContext(),time,Toast.LENGTH_LONG).show();
                    if (!(time.equals("NA"))) {
                        time = time + gmt_offset;
                        getWeather();
                    }

                }
                if(val==3){
                    //System.out.print(locationInfo+"\n");
                    loc = new JSONObject(locationInfo);
                    JSONArray x=loc.getJSONArray("results");
                    System.out.print(x.getJSONObject(0)+"\n");
                    JSONObject l=x.getJSONObject(0);
                    JSONObject l1=l.getJSONObject("geometry");
                    JSONObject l2=l1.getJSONObject("location");
                    lat=l2.getString("lat");
                    lon=l2.getString("lng");
                    System.out.print(lat+"  "+lon);
                    getGMT();
                }
                if(val==4){
                    JSONObject tmp=new JSONObject(timezoneInfo);
                    System.out.print(tmp+"\n");
                    gmt_offset=String.valueOf(tmp.getDouble("gmtOffset"));
                    System.out.print(gmt_offset+"\n\n");
                    if(!(gmt_offset.charAt(0)=='-'))
                        gmt_offset="+"+gmt_offset;
                    System.out.print(gmt_offset+"\n\n");
                    try {
                        int dot;
                        switch (gmt_offset.length()) {
                            case 2:
                                gmt_offset = gmt_offset.charAt(0) + "0" + gmt_offset + "00";
                                break;
                            case 3:
                                gmt_offset = gmt_offset.charAt(0) + gmt_offset + "00";
                                break;
                            case 4:
                                dot = gmt_offset.indexOf('.');
                                if (dot == 2) {
                                    char k = gmt_offset.charAt(dot + 1);
                                    double rem = ((k - 48) / 10.0) * 60;
                                    System.out.print(rem + "\n");
                                    gmt_offset = gmt_offset.charAt(0) + "0" + gmt_offset.charAt(1) + String.valueOf(rem).substring(0, 2);
                                }
                                break;
                            case 5:
                                dot = gmt_offset.indexOf('.');
                                if (dot == 2) {
                                    String k = gmt_offset.substring(3);
                                    double rem = (Float.parseFloat(k) / 10) * 60;
                                    gmt_offset = gmt_offset.charAt(0) + "0" + gmt_offset.charAt(1) + String.valueOf(rem).substring(0, 2);
                                } else {
                                    char k = gmt_offset.charAt(dot + 1);
                                    double rem = ((k - 42) / 10) * 60;
                                    gmt_offset = gmt_offset.charAt(0) + gmt_offset.substring(1, 2) + String.valueOf(rem).substring(0, 2);
                                }
                        }
                    }
                    catch (Exception e){

                    }
                    getFlight();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };

    /** Updates the date in the TextView */
    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear).append(" "));
    }

    /** Displays a notification when the date is updated */
    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate.getText()),  Toast.LENGTH_SHORT).show();

    }

    public void getGMT(){
        val=4;
        String url="http://api.geonames.org/timezoneJSON?lat="+lat+"&lng="+lon+"&username=demo";
        new Fetcher().execute(url, null, null);
    }
    public void getLocn(){
        val=3;
        System.out.println("*****in getLocn********");
        String url="https://maps.googleapis.com/maps/api/geocode/json?address="+src+"&key=AIzaSyCJMV-IClVGlkf8UtkimKq9c4wSvkWm1tA";
        System.out.print(url+"\n");
        new Fetcher().execute(url, null, null);
    }

     public void getWeather(){
         val=1;
         System.out.print(time);
         time=time.substring(0,time.length()-5);
         String url= "https://api.forecast.io/forecast/"+we_key+"/"+lat+","+lon+","+time+"?exclude=alerts,flags,minutely,hourly,daily";
         new Fetcher().execute(url, null, null);
     }
    public void getFlight(){
        val=0;
        src = ((EditText)findViewById(R.id.Source)).getText().toString();
        des =  ((EditText)findViewById(R.id.Dest)).getText().toString();
        String date = String.valueOf(pYear)+String.valueOf(pMonth+1)+String.valueOf(pDay);
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
        String url = "http://developer.goibibo.com/api/search/?app_id="+appid+"&app_key="+fl_key+"&format=json&source="+src.toUpperCase()+"&destination="+des.toUpperCase()+"&dateofdeparture="+date+"&seatingclass=E&adults=1&children=0&infants=0";
        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        System.out.println(url);
        new Fetcher().execute(url, null, null);
    }

    protected String getDepartureTime(String flightno){
        System.out.println("***********FL_NO="+flightno+"**********");
        String tmp;
        for(int i=0;i< flights.length ;i++){
            System.out.println(flights[i]);
            if(flightno.equalsIgnoreCase(flights[i])) {
                tmp = "2016-04-26T" + arrtime[i] + ":00"+gmt_offset;
                return tmp;
            }
        }
        return "NA";
    }
    protected String getArrivalTime(String flightno){
        System.out.println("***********FL_NO="+flightno+"**********");
        String tmp;
        for(int i=0;i< flights.length ;i++){
            System.out.println(flights[i]);
            if(flightno.equalsIgnoreCase(flights[i])) {
                tmp = "2016-04-26T" + arrtime[i] + ":00"+gmt_offset;
                return tmp;
            }
        }
        return "NA";
    }
    protected void count() throws JSONException {
        JSONObject data;
        data = fl.getJSONObject("data");
        JSONArray onward=data.getJSONArray("onwardflights");
        int no_of_fl=onward.length();
        JSONObject fl_id;
        flights=new String[no_of_fl];
        String flightcode[]=new String[no_of_fl];
        String carrierid[]=new String[no_of_fl];//arrtime
        departureTime=new String[no_of_fl];
        arrtime=new String[no_of_fl];
        for(int i=0;i<no_of_fl;i++) {
            fl_id = onward.getJSONObject(i);
            flightcode[i] = fl_id.getString("flightcode");
            carrierid[i] = fl_id.getString("carrierid");
            departureTime[i] = fl_id.getString("deptime");
            arrtime[i]=fl_id.getString("arrtime");
            flights[i] = carrierid[i] + flightcode[i];
        }
    }
 }

