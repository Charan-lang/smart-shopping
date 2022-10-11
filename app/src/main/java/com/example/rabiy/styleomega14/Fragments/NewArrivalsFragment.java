package com.example.rabiy.styleomega14.Fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Activities.ItemsActivity;
import com.example.rabiy.styleomega14.Adapters.ItemsRecyclerAdapter;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class NewArrivalsFragment extends Fragment {

    List<Item> lstItem;

    public NewArrivalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_new_arrivals, container, false);

        //get a reference to recycler view
        RecyclerView recyclerView=rootView.findViewById(R.id.recycler_view_id1);

        //set layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        List<Item> items=Item.listAll(Item.class);

            lstItem=new ArrayList<>();

            for (Item item:items) {

                if(item.isNewArrival()){
                    lstItem.add(item);
                }
            }

        //create an instance of ItemsRecyclerAdapter
        ItemsRecyclerAdapter myAdapter=new ItemsRecyclerAdapter(getActivity(),lstItem);

        //set adapter
        recyclerView.setAdapter(myAdapter);

        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }


}
