package com.example.covid19tracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter for the centre class
 */

public class CentreAdapter extends RecyclerView.Adapter<CentreAdapter.ViewHolder> {

    ArrayList<Centre> centres = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccination_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Centre currentItem = centres.get(position);
        holder.centreName.setText(currentItem.centerName);
        holder.centreAddress.setText(currentItem.centerAddress);
        holder.vaccineName.setText("Vaccine Available : " + currentItem.vaccineName);
        holder.fromTime.setText("Timings From : " + currentItem.centerFromTiming);
        holder.toTime.setText("To : " + currentItem.centerToTiming);
        holder.fees.setText("Fees : " + currentItem.fees);
        String ageLimit = String.valueOf(currentItem.ageLimit);
        holder.ageLimit.setText("Age Limit : " + ageLimit);
        holder.availability.setText("Availability : " + currentItem.availability);
        holder.book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.cowin.gov.in/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(holder.itemView.getContext(), Uri.parse(url));
            }
        });

    }

    @Override
    public int getItemCount() {
        return centres.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView centreName, centreAddress, vaccineName, fromTime, toTime, fees, ageLimit, availability;
        Button book_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.book_btn = itemView.findViewById(R.id.book_btn);
            this.centreName = itemView.findViewById(R.id.centre_name_tv);
            this.centreAddress = itemView.findViewById(R.id.centre_add_tv);
            this.vaccineName = itemView.findViewById(R.id.vaccine_name_tv);
            this.fees = itemView.findViewById(R.id.fees_tv);
            this.fromTime = itemView.findViewById(R.id.fromTime_tv);
            this.toTime = itemView.findViewById(R.id.toTime_tv);
            this.ageLimit = itemView.findViewById(R.id.ageLimit_tv);
            this.availability = itemView.findViewById(R.id.availability_tv);
        }
    }

    public void updateCentre(ArrayList<Centre> updateCentre) {
        centres.clear();
        centres.addAll(updateCentre);
        notifyDataSetChanged();
    }

}
