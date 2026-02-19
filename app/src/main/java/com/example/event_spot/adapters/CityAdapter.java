package com.example.event_spot.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.event_spot.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements Filterable {
    private List<City> cityList;
    private List<City> cityListFull;
    private City selectedCity;

    public City getSelectedCity()
    {
        return selectedCity;
    }

    public CityAdapter(List<City> cityList) {
        this.cityList = cityList;
        cityListFull = new ArrayList<>(cityList);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        // Set the city name to the TextView
        holder.cityName.setText(cityList.get(position).getName());

        // Set up the click listener for each item in the RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the selected city when clicked
                selectedCity = cityList.get(holder.getAdapterPosition());

                // Notify the adapter to refresh the UI and highlight the selected item
                notifyDataSetChanged();
            }
        });

        // Highlight the selected city (optional, for better UX)
        holder.itemView.setBackgroundColor(selectedCity == cityList.get(position) ? Color.LTGRAY : Color.WHITE);
    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<City> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(cityListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (City city : cityListFull) {
                        if (city.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(city);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cityList.clear();
                cityList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;

        public CityViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(android.R.id.text1);
        }
    }
}

