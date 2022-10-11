package com.example.rabiy.styleomega14.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

public class ItemsActivity extends AppCompatActivity {

    List<Item> lstItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Products");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String category=intent.getStringExtra("Category");
        String gender=intent.getStringExtra("Gender");

        List<Item> items=Item.listAll(Item.class);

        if(items.size()==0){
            BufferedReader bufferedReader;
            try {
                Context context=getBaseContext();
                AssetManager assetManager=context.getAssets();
                InputStream inputStream=assetManager.open("products.json");
                bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                Type collectionType = new TypeToken<List<Item>>(){}.getType();
                for (Item item : lstItem = new Gson()
                        .fromJson(bufferedReader, collectionType)) {
                    item.save();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(ItemsActivity.this, "Items Available", Toast.LENGTH_LONG).show();
            lstItem=new ArrayList<>();

            for (Item item:items) {
                String c=item.getCategory();
                String g=item.getGender();
                if(c.equals(category) && g.equals(gender)){
                    lstItem.add(item);
                }
            }
        }

        RecyclerView recyclerView=findViewById(R.id.recycler_view_id);
        ItemsRecyclerAdapter myAdapter=new ItemsRecyclerAdapter(this,lstItem);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);
    }

}
