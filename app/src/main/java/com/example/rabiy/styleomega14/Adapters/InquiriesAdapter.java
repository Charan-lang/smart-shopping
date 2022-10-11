package com.example.rabiy.styleomega14.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rabiy.styleomega14.Models.Inquiry;
import com.example.rabiy.styleomega14.R;

import java.util.List;

public class InquiriesAdapter extends RecyclerView.Adapter<InquiriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Inquiry> mData;
    private List <String> stringList;

    public InquiriesAdapter(Context mContext, List<Inquiry> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public InquiriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.inquiries_list,parent,false);
        return new InquiriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InquiriesAdapter.MyViewHolder viewHolder, final int position) {

        String id=Integer.toString(position+1);
        String userName=mData.get(position).getUser().getfName()+" "+mData.get(position).getUser().getlName();
        viewHolder.tv_inquiry_deets.setText(id+") "+userName+" : "+mData.get(position).getInquiry());
        String reply="";
        if (mData.get(position).getReply() == null) {
            reply = "StyleOmega : No reply yet";
        } else {
            reply = "StyleOmega : " + mData.get(position).getReply();
        }

        viewHolder.tv_reply_deets.setText(reply);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_inquiry_deets;
        TextView tv_reply_deets;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_inquiry_deets=itemView.findViewById(R.id.tv_inquiry);
            tv_reply_deets=itemView.findViewById(R.id.tv_inquiry_reply);

        }
    }

}
