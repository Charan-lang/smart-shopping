package com.example.rabiy.styleomega14.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rabiy.styleomega14.Activities.ItemsActivity;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> mData;

    public ListRecyclerAdapter(Context mContext, List<Item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.card_vertical_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

        viewHolder.tv_category_name.setText(mData.get(position).getCategory());
        String url=mData.get(position).getimage();
        Picasso.get().load(url).into(viewHolder.img_category);
        //viewHolder.img_category.setImageResource(mData.get(position).getThumbnail());

        viewHolder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ItemsActivity.class);
                intent.putExtra("Category",mData.get(position).getCategory());
                intent.putExtra("Gender",mData.get(position).getGender());
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_category_name;
        ImageView img_category;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_category_name=itemView.findViewById(R.id.tv_category_id);
            img_category=itemView.findViewById(R.id.image_vertical_id);

        }
    }

}
