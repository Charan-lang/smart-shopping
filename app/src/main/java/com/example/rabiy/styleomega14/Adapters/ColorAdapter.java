package com.example.rabiy.styleomega14.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rabiy.styleomega14.Models.Color;
import com.example.rabiy.styleomega14.R;

import java.util.ArrayList;

import static android.graphics.Color.parseColor;

public class ColorAdapter extends ArrayAdapter<Color> {

    public ColorAdapter(Context context, ArrayList<Color> colorList){
        super(context,0,colorList);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.color_spinner_row,parent,false);
        }

        ImageView imgViewColor=convertView.findViewById(R.id.img_view_color);
        //TextView txtViewColor=convertView.findViewById(R.id.txt_view_color);

        Color currentColor=getItem(position);

        if(currentColor!=null) {
            String color=currentColor.getColor();
            //convertView.setBackgroundColor(parseColor(color));
            imgViewColor.setBackgroundColor(parseColor(color));
            //txtViewColor.setText(color);
        }
        return convertView;
    }


}
