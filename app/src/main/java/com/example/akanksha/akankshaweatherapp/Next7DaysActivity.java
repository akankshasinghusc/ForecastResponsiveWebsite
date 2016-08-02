package com.example.akanksha.akankshaweatherapp;

/**
 * Created by AKANKSHA on 12/10/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Next7DaysActivity extends AppCompatActivity {
    String Resultjson, ResultCity, ResultState;
    JSONObject jsonObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next7days);

        getSupportActionBar().setTitle("DetailsActivity");

        setButtonToggling();
        Intent intent = getIntent();

       Resultjson=intent.getStringExtra("Resultjson");
        ResultCity=intent.getStringExtra("ResultCity");
        ResultState=intent.getStringExtra("ResultState");






            }
        });

    }

}

