package com.winnipegapp.examples;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mauricio on 2016-05-30.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CustomViewHolder>{

    List<Events> events;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_event, viewGroup, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.eventName.setText(events.get(position).eventName);
        holder.eventLocation.setText(events.get(position).eventLocation);
        holder.eventDescription.setText(events.get(position).eventDescription);
        holder.eventImage.setImageResource(events.get(position).eventImage);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView eventName;
        TextView eventLocation;
        TextView eventDescription;
        ImageView eventImage;

        CustomViewHolder (View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardview);
            eventName = (TextView)itemView.findViewById(R.id.eventName);
            eventLocation = (TextView)itemView.findViewById(R.id.eventLocation);
            eventDescription = (TextView)itemView.findViewById(R.id.eventDescription);
            eventImage = (ImageView)itemView.findViewById(R.id.eventImage);

        }

    }

    RVAdapter (List<Events> events) {
        this.events = events;

    }

}