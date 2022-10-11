package com.example.rabiy.styleomega14.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rabiy.styleomega14.Adapters.ListRecyclerAdapter;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.R;

import java.util.ArrayList;
import java.util.List;


public class WomensCategoriesFrag extends Fragment {

    List<Item> lstItem;




    public WomensCategoriesFrag(){
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_categories, container, false);

        //get a reference to recycler view
        RecyclerView recyclerView=rootView.findViewById(R.id.recycler_categories);

        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //the data for the recycler view
        lstItem=new ArrayList<>();
        lstItem.add(new Item("Coats & Jackets","https://i.pinimg.com/564x/fe/fc/e1/fefce1c8250e7679cf45486b5bbd2d45.jpg","female"));
        lstItem.add(new Item("Dresses","https://i.pinimg.com/564x/6b/89/de/6b89de8d4ff4512702e8941031067617.jpg","female"));
        lstItem.add(new Item("T-shirts & Blouses","https://i.pinimg.com/564x/ef/96/e7/ef96e7377f168d72c8ab1b5be3185e77.jpg","female"));
        lstItem.add(new Item("Bottoms","https://i.pinimg.com/564x/ca/54/e6/ca54e62ec5bde58006fd91a959d9c472.jpg","female"));
        lstItem.add(new Item("Skirts","https://i.pinimg.com/564x/e5/aa/e6/e5aae6177ef46954621e3e7c827c9c93.jpg","female"));
        lstItem.add(new Item("Accessories","https://i.pinimg.com/564x/c0/86/70/c08670fa037abf12e6ae883a658c32d5.jpg","female"));
        lstItem.add(new Item("Kids","https://i.pinimg.com/564x/87/f5/85/87f585fca09a9e9e4da8bb39358b45f8.jpg","female"));

        //create an instance of ItemsRecyclerAdapter
        ListRecyclerAdapter myAdapter=new ListRecyclerAdapter(getActivity(),lstItem);

        //set adapter
        recyclerView.setAdapter(myAdapter);

        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;

    }


}
