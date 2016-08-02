package com.example.akanksha.akankshaweatherapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Result extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    String Resultjson, ResultCity,ResultState,ResultDegree;

    public void moredetails(View view){
        Intent intent=new Intent(this,DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


            FacebookSdk.sdkInitialize(getApplicationContext());
            // Initialize the SDK before executing any other operations,
            // especially, if you're using Facebook UI elements.

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        //shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });








      Intent intent=getIntent();
//
         Resultjson=intent.getStringExtra("jsonvalue");
         ResultCity=intent.getStringExtra("city");
       ResultState=intent.getStringExtra("state");
        ResultDegree=intent.getStringExtra("degree");







        String PrecIntensity1="null";
        Integer rainvalue;
        String rainvaluedisplay;
        final Integer tvariable;
        final Integer tvariable1;
        final Integer tvariable2;
        final String tempunit;
        final String unit;
        final String windunit;
        final String visiunit;
        final String pressure_unit;


        final String DEGREE  = "\u00b0";

        if(ResultDegree.equals("us")){
            tempunit="F";
            unit="°F";
            windunit="mph";
            visiunit="mi";
            pressure_unit="mb";
        }
        else
        {
            tempunit="C";
            unit="°C";
            windunit="m/s";
            visiunit="km";
            pressure_unit="hPa";
        }








try {



    if(image.equals("clear-day"))
        tempImgURL+="clear.png";
    else if (image.equals("clear-night"))
        tempImgURL+="clear_night.png";
    else if (image.equals("partly-cloudy-day"))
        tempImgURL+="cloud_day.png";
    else if(image.equals("partly-cloudy-night"))
        tempImgURL+="cloud_night.png";
    else
        tempImgURL+=image+".png";

    final String imgURL=tempImgURL;


    final String summary=currentvalue.getString("summary");

    SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
    df.setTimeZone(TimeZone.getTimeZone(timezone));
    Long time1=Long.parseLong(datavalue.getJSONObject(0).getString("sunsetTime"));
    Date d= new Date(time1*1000L);
    final String sunset=df.format(d);
    Long time2=Long.parseLong(datavalue.getJSONObject(0).getString("sunriseTime"));
    d= new Date(time2*1000L);
    final String sunrise=df.format(d);


    tvariable=Math.round(Float.parseFloat(currentvalue.getString("temperature")));
    String temperatureText=tvariable.toString()+unit;

    tvariable1=Math.round(Float.parseFloat(datavalue.getJSONObject(0).getString("temperatureMin")));
    String temperatureMin=tvariable1.toString()+DEGREE;

    tvariable2=Math.round(Float.parseFloat(datavalue.getJSONObject(0).getString("temperatureMax")));
    String temperatureMax=tvariable2.toString()+DEGREE;












    Float PrecIntensity= Float.parseFloat(currentvalue.getString("precipIntensity"));
    if(PrecIntensity>=0 && PrecIntensity <0.002)
    {
        //String PrecIntensityvalue=PrecIntensity.toString();
        PrecIntensity1="None";
    }
    else if(PrecIntensity>=0.002 && PrecIntensity <0.017)
    {
        String PrecIntensityvalue=PrecIntensity.toString();
        PrecIntensity1="Very Light";
    }
    else if(PrecIntensity>=0.017 && PrecIntensity <0.1)
    {
        String PrecIntensityvalue=PrecIntensity.toString();
        PrecIntensity1="Light";
    }
    else if(PrecIntensity>=0.1 && PrecIntensity <0.4)
    {
        String PrecIntensityvalue=PrecIntensity.toString();
         PrecIntensity1="Moderate";
    }
    else if(PrecIntensity>=0.4)
    {
        String PrecIntensityvalue=PrecIntensity.toString();
        PrecIntensity1="Heavy";
    }

    rainvalue=Math.round(Float.parseFloat(currentvalue.getString("precipProbability")));
    rainvalue=rainvalue*100;
    rainvaluedisplay=rainvalue+"%";







    //=======================================================================




    TextView sumdisplay=(TextView) findViewById(R.id.textView8);
    TextView tempdisplay= (TextView) findViewById(R.id.textView9);
    TextView minmaxdisplay=(TextView) findViewById(R.id.textView10);
    TextView precipitationdisplay=(TextView)findViewById(R.id.textView2);
    TextView raindisplay=(TextView)findViewById(R.id.textView4);
    TextView winddisplay=(TextView)findViewById(R.id.textView6);

    TextView dewdisplay=(TextView)findViewById(R.id.textView12);

    TextView humidisplay=(TextView)findViewById(R.id.textView14);

    TextView visidisplay=(TextView)findViewById(R.id.textView16);

    TextView sunrisedisplay=(TextView)findViewById(R.id.textView18);
    TextView sunsetdisplay=(TextView)findViewById(R.id.textView20);








    ImageView imageicon = (ImageView) findViewById(R.id.imageView3);
            if(image.equals("partly-cloudy-night"))
            {
                imageicon.setImageResource(R.drawable.cloud_night);
            }
    else if(image.equals("clear-day"))
    {
        imageicon.setImageResource(R.drawable.clear);
    }
    else if(image.equals("clear-night"))
    {
        imageicon.setImageResource(R.drawable.clear_night);
    }
    else if(image.equals("rain"))
    {
        imageicon.setImageResource(R.drawable.rain);
    }
    else if (image.equals("snow"))
    {
        imageicon.setImageResource(R.drawable.snow);
    }
    else if(image.equals("sleet"))
    {
        imageicon.setImageResource(R.drawable.sleet);
    }
    else if (image.equals("wind"))
    {
        imageicon.setImageResource(R.drawable.wind);
    }
    else if(image.equals("fog"))
    {
        imageicon.setImageResource(R.drawable.fog);
    }

    else if(image.equals("cloudy"))
    {
        imageicon.setImageResource(R.drawable.cloudy);
    }

    else if(image.equals("partly-cloudy-day"))
    {
        imageicon.setImageResource(R.drawable.cloud_day);
    }
    else if(image.equals("partly-cloudy-night"))
    {
        imageicon.setImageResource(R.drawable.cloud_night);
    }


    //==============================Display Values=======================

    sumdisplay.setText(summary+" in "+ ResultCity+" , "+ResultState);
    tempdisplay.setText(temperatureText);
    minmaxdisplay.setText("L:"+temperatureMin+"| H:"+temperatureMax);

    precipitationdisplay.setText(PrecIntensity1);
    raindisplay.setText(rainvaluedisplay);

    winddisplay.setText(wind);

    dewdisplay.setText(dewvalue);

    humidisplay.setText(humivalue);

    visidisplay.setText(visivalue);
sunrisedisplay.setText(sunrise);
    sunsetdisplay.setText(sunset);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getApplicationContext(), sum, Toast.LENGTH_SHORT).show();




        }

    public void openMoreDetails(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("stateDetails", ResultState);
        intent.putExtra("cityDetails", ResultCity);
        intent.putExtra("JSONDetails", Resultjson);
        intent.putExtra("degree",ResultDegree);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    }

