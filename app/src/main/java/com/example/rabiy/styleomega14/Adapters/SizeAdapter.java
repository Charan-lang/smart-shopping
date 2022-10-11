package com.example.rabiy.styleomega14.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabiy.styleomega14.Models.Size;
import com.example.rabiy.styleomega14.R;
import java.util.ArrayList;


public class SizeAdapter extends ArrayAdapter<Size> {

    public SizeAdapter(Context context, ArrayList<Size> sizeList){
        super(context,0,sizeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.size_spinner_row,parent,false);
        }

        TextView txtViewSize=convertView.findViewById(R.id.txt_view_size);

        Size currentSize=getItem(position);

        if(currentSize!=null) {
            String size=currentSize.getSize();
            txtViewSize.setText(size);
        }
        return convertView;
    }

}
