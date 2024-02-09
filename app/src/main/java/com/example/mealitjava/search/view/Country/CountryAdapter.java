package com.example.mealitjava.search.view.Country;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealitjava.R;
import com.example.mealitjava.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final Context context;
    private List<Country> countryList;

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    private OnCountryClickListener listener;

    public CountryAdapter(Context context, List<Country> countryList, OnCountryClickListener listener) {
        this.context = context;
        this.countryList = countryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.country_search_row_layout, parent, false);
        CountryAdapter.ViewHolder vh = new CountryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // CheckSearchBy checkSearchBy = new CheckSearchBy();
        String name = countryList.get(position).getStrArea();
        holder.countryTextView.setText(countryList.get(position).getStrArea());
        holder.countryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCountry(name);
            }
        });


        //      holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClickCountry(name);
//                checkSearchBy.setType(CheckSearchBy.country);
//                checkSearchBy.setName(name);
//                Log.i("Country", "onClick: " + name);
//
//
//            }
   //     });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryTextView;
        ConstraintLayout constraintLayout;
        View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            countryTextView = v.findViewById(R.id.country_TextView_search);

            Log.i("texxxxt", "TextView " + countryTextView);
            constraintLayout = v.findViewById(R.id.country_row_layout);
        }
    }

}

