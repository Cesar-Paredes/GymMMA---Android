package com.example.mma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ExampleViewHolder> {

    private ArrayList<ScheduleBlock> ScheduleList;

    ///
    public ScheduleAdapter(ArrayList<ScheduleBlock> scheduleList){
        ScheduleList = scheduleList;
    };

    ///
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_block,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    ////
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ScheduleBlock currentItem = ScheduleList.get(position);
        holder.ImageView.setImageResource(currentItem.getImage());
        holder.TextView.setText(currentItem.getText());
    }

    /////
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public android.widget.ImageView ImageView;
        public android.widget.TextView TextView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView = itemView.findViewById(R.id.imageView);
            TextView = itemView.findViewById(R.id.textView);
        }
    }

    ////////
    @Override
    public int getItemCount() {
        return ScheduleList.size();
    }
    ///
}