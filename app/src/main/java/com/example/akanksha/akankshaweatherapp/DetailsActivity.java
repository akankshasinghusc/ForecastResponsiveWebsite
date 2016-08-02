package com.example.akanksha.akankshaweatherapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {
    String Resultjson, ResultCity, ResultState, ResultDegree, unit;
    JSONObject jsonObj;
    String DEGREE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("DetailsActivity");

        setButtonToggling();
        Intent intent = getIntent();


//        DetailsJSON=intent.getStringExtra("JSONDetails");
//        Detailsstate=intent.getStringExtra("stateDetails");
//        Detailscity=intent.getStringExtra("cityDetails");


        try {
            Resultjson = intent.getStringExtra("JSONDetails");
            ResultCity = intent.getStringExtra("cityDetails");
            ResultState = intent.getStringExtra("stateDetails");
            ResultDegree = intent.getStringExtra("degree");

            jsonObj = new JSONObject(Resultjson);
            JSONObject currentvalue = jsonObj.getJSONObject("currently");
            JSONObject hourlyvalue = jsonObj.getJSONObject("hourly");
            JSONArray datavalue = hourlyvalue.getJSONArray("data");

            final String timezone = jsonObj.getString("timezone");

            TextView degreetemp = (TextView) findViewById(R.id.textView14);
            //
            DEGREE  = "\u00b0";

            if (ResultDegree.equals("us")) {

                unit = "F";

            } else {

                unit = "C";

            }
            degreetemp.setText("Temp"+DEGREE+unit);
            TextView top = (TextView) findViewById(R.id.textView);
            //top.setText("More details on"+ResultCity+","+ResultState);


//            JSONObject hourlyvalue = jsonObj.getJSONObject("hourly");
//            JSONArray datavalue=hourlyvalue.getJSONArray("data");
            List<HourlyBean> beanList = new ArrayList<>();
            for (int i = 0; i < 48; i++) {


               

                beanList.add(new HourlyBean(time, datavalue.getJSONObject(i).getString("icon"), datavalue.getJSONObject(i).getString("temperature")));
            }

            ListView lv = (ListView) findViewById(R.id.listview);
            ArrayAdapter hourlyApt = new Hourly(this, R.layout.listview, beanList);
            lv.setAdapter(hourlyApt);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setButtonToggling() {
        final Button button24 = (Button) findViewById(R.id.next_24_hr_button);
        final Button button7 = (Button) findViewById(R.id.next_7_d_button);
        final Context c = this;
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button24) {
                    // reverseButtonStyle(button24);
                    // reverseButtonStyle(button7);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button7) {
                    reverseButtonStyle(button24);
                    reverseButtonStyle(button7);
                    Intent intent = new Intent(getApplicationContext(), Next7DaysActivity.class);
                    intent.putExtra("ResultState", ResultState);
                    intent.putExtra("ResultCity", ResultCity);
                    intent.putExtra("Resultjson", Resultjson);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isGray(Button btn) {
        Drawable.ConstantState blueDrawable = ContextCompat.getDrawable(this, R.drawable
                .common_google_signin_btn_icon_light_normal).getConstantState();
        Drawable.ConstantState btnDrawable = btn.getBackground().getConstantState();
        if (blueDrawable.equals(btnDrawable)) {
            return false;
        } else {
            return true;
        }
    }

    private void reverseButtonStyle(Button btn) {
        if (isGray(btn)) {
            btn.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark);
        } else {
            btn.setBackgroundResource(R.drawable.common_google_signin_btn_icon_light_normal);
        }


    }
}


