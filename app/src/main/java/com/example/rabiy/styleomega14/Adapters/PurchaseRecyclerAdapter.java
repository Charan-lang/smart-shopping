package com.example.rabiy.styleomega14.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Models.CartItem;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.Models.Review;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PurchaseRecyclerAdapter extends RecyclerView.Adapter<PurchaseRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<CartItem> mData;

    public PurchaseRecyclerAdapter(Context mContext, List<CartItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.purchased_items_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

        final Item item=mData.get(position).getItem();
        final int code=item.getCode();
        viewHolder.tv_purchaseditem.setText(item.getName());
        String url=item.getimage();
        Picasso.get().load(url).into(viewHolder.img_purchaseditem);
        viewHolder.tv_purchasedsize.setText("Size : "+mData.get(position).getSize());
        viewHolder.tv_purchasedcolor.setText("Color : "+mData.get(position).getColor());
        int currentQuantity=mData.get(position).getQuantity();
        viewHolder.tv_purchasedquantity.setText(Integer.toString(currentQuantity));
        viewHolder.tv_purchasedprice.setText("Rs."+Integer.toString(item.getPrice()*currentQuantity));

        viewHolder.btn_submitreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review=viewHolder.et_writereview.getText().toString();

                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                List<User> users=User.listAll(User.class);
                for (User user:users) {
                    if (user.getEmail().equals(userEmail)) {
                        List<Item> items=Item.listAll(Item.class);
                        for(Item item:items) {
                            if (item.getCode() == code) {
                                Review review1 = new Review(review, user, item);
                                review1.save();
                                Toast.makeText(mContext,
                                        "Your Review Has Been Sent",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_purchaseditem;
        TextView tv_purchasedsize;
        TextView tv_purchasedcolor;
        TextView tv_purchasedprice;
        TextView tv_purchasedquantity;
        ImageView img_purchaseditem;
        EditText et_writereview;
        Button btn_submitreview;



        public MyViewHolder(View itemView){
            super(itemView);

            tv_purchaseditem=itemView.findViewById(R.id.tv_purchased_item);
            tv_purchasedsize=itemView.findViewById(R.id.tv_purchased_size);
            tv_purchasedcolor=itemView.findViewById(R.id.tv_purchased_color);
            tv_purchasedprice=itemView.findViewById(R.id.tv_purchased_price);
            tv_purchasedquantity=itemView.findViewById(R.id.tv_purchased_quantity);
            img_purchaseditem=itemView.findViewById(R.id.image_purchased);
            et_writereview=itemView.findViewById(R.id.et_review);
            btn_submitreview=itemView.findViewById(R.id.btn_send_review);
        }
    }

}
