package com.example.akanksha.akankshaweatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;

import com.google.android.gms.maps.MapFragment;
import com.hamweather.aeris.communication.AerisEngine;

import org.json.JSONObject;

public class MapsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
        AerisEngine.initWithKeys("kKOoi0EH1DgdkTecKfqWq","t8YmdJ0dQTi43TxTe1TYKkAYQLHNAX6VwrSp5wcR", "com.example.akanksha.akankshaweatherapp");
        Bundle b = getIntent().getExtras();
        String jsondata=b.getString("jsondata");
        double lat=0.0, lng=0.0;
        JSONObject jObj=null;
        try
        {
            jObj=new JSONObject(jsondata);
            lat = jObj.getDouble("latitude");
            lng = jObj.getDouble("longitude");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}