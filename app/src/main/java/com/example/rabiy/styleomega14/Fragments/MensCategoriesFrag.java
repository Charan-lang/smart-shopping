package com.example.rabiy.styleomega14.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

public class MensCategoriesFrag extends Fragment {

    List<Item> lstItem;

    public MensCategoriesFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_categories, container, false);

        //get a reference to recycler view
        RecyclerView recyclerView=rootView.findViewById(R.id.recycler_categories);

        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //the data for the recycler view
        lstItem=new ArrayList<>();
        lstItem.add(new Item("Coats & Jackets","https://i.pinimg.com/564x/39/35/fe/3935fea1623bf67e56a70b2bebb57be0.jpg","male"));
        lstItem.add(new Item("T-shirts & Shirts","https://i.pinimg.com/564x/6b/00/31/6b0031f359d4eb51f90030d6800779b2.jpg","male"));
        lstItem.add(new Item("Bottoms","https://i.pinimg.com/564x/80/32/2e/80322eea09128a705a6781c7df781c4d.jpg","male"));
        lstItem.add(new Item("Hoodies","https://i.pinimg.com/564x/e7/7c/df/e77cdf1619aa0230bffdcd8b86e781a6.jpg","male"));
        lstItem.add(new Item("Accessories","https://i.pinimg.com/564x/25/4f/2e/254f2e723aea9f1af20da16755dca2fe.jpg","male"));
        lstItem.add(new Item("Kids","https://i.pinimg.com/564x/41/48/74/414874d4f350ad4c5bee634275fc4776.jpg","male"));

        //create an instance of ItemsRecyclerAdapter
        ListRecyclerAdapter myAdapter=new ListRecyclerAdapter(getActivity(),lstItem);

        //set adapter
        recyclerView.setAdapter(myAdapter);

        //set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

}
