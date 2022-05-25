package com.example.mma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrivateBookingAdapter extends RecyclerView.Adapter<PrivateBookingAdapter.MyViewHolder> {

    Context context;

    ArrayList<PrivateBooking> list;


    public PrivateBookingAdapter(Context context, ArrayList<PrivateBooking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.privatebooking_block,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PrivateBooking user = list.get(position);
        holder.course.setText(user.getCourse());
        holder.date.setText(user.getDate());
        holder.requests.setText(user.getRequests());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView course, date, requests;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            course = itemView.findViewById(R.id.textViewCoursePB);
            date = itemView.findViewById(R.id.textViewDatePB);
            requests = itemView.findViewById(R.id.textViewRequestsPB);

        }
    }
//
}