package com.example.rabiy.styleomega14.Activities;

import android.content.Intent;
import android.os.HandlerThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Adapters.CartRecyclerAdapter;
import com.example.rabiy.styleomega14.Models.Cart;
import com.example.rabiy.styleomega14.Models.CartItem;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class CartActivity extends AppCompatActivity {

    TextView numItems;
    TextView totalAmount;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        numItems=findViewById(R.id.item_count_tv);
        totalAmount=findViewById(R.id.total_amount_tv);
        checkout=findViewById(R.id.checkout_btn);

        final List<CartItem> lst=new ArrayList<>();
        int total=0;
        int itemNo=0;
        String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
        List<User> users=User.findWithQuery(User.class,"Select * from User where email = ? ",userEmail);
        for (User user:users) {
            Cart cart=user.getCart();
            if(user.getCart()==null){
                Toast.makeText(CartActivity.this, "Your Cart Is Empty", Toast.LENGTH_SHORT).show();
            }else {
                if(user.getCart().getStatus().equals("pending")) {

                    List<CartItem>cartitems=CartItem.findWithQuery(CartItem.class,
                            "Select * from CART_ITEM where  cart = ?",cart.getId().toString());
                    for(CartItem item:cartitems){
                            lst.add(item);
                            total=total+item.getItem().getPrice()*item.getQuantity();
                            itemNo=itemNo+item.getQuantity();
                    }
                }
            }
        }

        RecyclerView recyclerView=findViewById(R.id.recycler_cart_items);
        CartRecyclerAdapter myAdapter=new CartRecyclerAdapter(this,lst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        numItems.setText("Item Count : "+Integer.toString(itemNo));

        totalAmount.setText("Rs."+Integer.toString(total));

        final int finalTotal = total;
        final int finalItemNo = itemNo;
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,CheckoutActivity.class);
                intent.putExtra("orderTotal", finalTotal);
                intent.putExtra("itemCount", finalItemNo);
                startActivity(intent);

            }
        });

    }


}
