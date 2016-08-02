


package com.example.akanksha.akankshaweatherapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import java.util.List;

/**
 * Created by AKANKSHA on 12/10/2015.
 */
public class Hourly extends ArrayAdapter<HourlyBean> {

    public Hourly(Context context, int resourceId, List<HourlyBean> listOfBean) {
        super(context, resourceId, listOfBean);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater hourlyInflater = LayoutInflater.from(getContext());
        View hourlyView = hourlyInflater.inflate(R.layout.listview, parent, false);

        HourlyBean eachBean = getItem(position);
        ImageView imageView = (ImageView) hourlyView.findViewById(R.id.hourly_icon);
        TextView textViewTime = (TextView) hourlyView.findViewById(R.id.hourly_time);
        TextView textViewTemp = (TextView) hourlyView.findViewById(R.id.hourly_temp);
        ImageView displayIcon = (ImageView) hourlyView.findViewById(R.id.imageView);
        String img = eachBean.getImageId();




//        return super.getView(position, convertView, parent);
        return hourlyView;
    }

}
