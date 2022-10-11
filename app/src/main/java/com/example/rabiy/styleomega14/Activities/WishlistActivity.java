package com.example.rabiy.styleomega14.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Adapters.ItemsRecyclerAdapter;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.Models.WishlistItem;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    List<Item> lstItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Wishlist");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
        List<User> users=User.findWithQuery(User.class,"Select * from User where email = ? ",userEmail);
        for (User user:users) {
            long uid=user.getId();
            List<WishlistItem> wishlist=WishlistItem.listAll(WishlistItem.class);
            if(wishlist.size()>0){
                lstItem=new ArrayList<>();
                for (WishlistItem wishitem:wishlist) {
                    long wishUID=wishitem.getUser().getId();
                    if(uid==wishUID){
                        lstItem.add(wishitem.getItem());
                    }else {
                        Toast.makeText(this, "No Items in your Wishlist", Toast.LENGTH_SHORT).show();
                    }
                }
                RecyclerView recyclerView=findViewById(R.id.recycler_view_wishlist);
                ItemsRecyclerAdapter myAdapter=new ItemsRecyclerAdapter(this,lstItem);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                recyclerView.setAdapter(myAdapter);

            }else{
                Toast.makeText(this, "No Items in your Wishlist", Toast.LENGTH_SHORT).show();
            }

        }



    }
}
