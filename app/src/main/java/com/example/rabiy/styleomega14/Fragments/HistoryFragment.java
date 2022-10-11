package com.example.rabiy.styleomega14.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Activities.CartActivity;
import com.example.rabiy.styleomega14.Activities.HistoryItemsActivity;
import com.example.rabiy.styleomega14.Models.Cart;
import com.example.rabiy.styleomega14.Models.CartItem;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {



    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_history, container, false);

        final ListView lvHistory=rootView.findViewById(R.id.listview_history);
        String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
        List<User> users
                =User.findWithQuery(User.class,"Select * from User where email = ? ",userEmail);
        Long uid=0L;
        for(User user:users){
            uid=user.getId();
        }

        List<Cart>carts=Cart.listAll(Cart.class);
        int i=0;
        if(carts.size()>0){
            String[] arr=new String[carts.size()];
            Arrays.fill(arr, "");
            for(Cart cart:carts){
                long userid=cart.getUser().getId();
                if(userid==uid){
                    if((cart.getStatus().equals("purchased"))){
                        String cartId="Cart ID : "+Long.toString(cart.getId())+" ";
                        String date="Purchased Date : "+cart.getDate();
                        String total="Total : Rs."+Integer.toString(cart.getTotalAmount());
                        arr[i]=cartId+"\n"+date+"\n"+total;
                        i++;
                    }else{
                        String[] noCarts=new String[1];
                        noCarts[0]="You haven't made any Purchases!";
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, noCarts);

                        lvHistory.setAdapter(adapter);
                    }
                }
            }

            if(arr[0]!=null ){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, arr);

                lvHistory.setAdapter(adapter);

                lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // ListView Clicked item index
                        int itemPosition= position;

                        // ListView Clicked item value
                        String  itemValue= (String) lvHistory.getItemAtPosition(position);
                        String[] arrSplit=itemValue.split(" ");
                        String cartid=arrSplit[3];

                        Intent intent = new Intent(getActivity(), HistoryItemsActivity.class);
                        intent.putExtra("cartID",cartid);
                        startActivity(intent);

                    }
                });
            }
        }else {
            String[] noCarts=new String[1];
            noCarts[0]="You haven't made any Purchases!";
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, noCarts);

            lvHistory.setAdapter(adapter);
        }
        return rootView;
    }

}
