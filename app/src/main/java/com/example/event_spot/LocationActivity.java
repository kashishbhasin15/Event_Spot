package com.example.event_spot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager; // For RecyclerView
import java.util.ArrayList; // For using ArrayList
import java.util.List; // For using List

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.event_spot.adapters.CityAdapter;
import com.example.event_spot.model.City;

public class LocationActivity extends AppCompatActivity {

    AppCompatButton confirmLocation;
    private RecyclerView cityRecyclerView;
    private CityAdapter cityAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityRecyclerView = findViewById(R.id.cityRecyclerView);
        cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<City> cityList = new ArrayList<>();
        cityList.add(new City("Ahmedabad"));
        cityList.add(new City("Bangalore"));
        cityList.add(new City("Bhopal"));
        cityList.add(new City("Chandigarh"));
        cityList.add(new City("Chennai"));
        cityList.add(new City("Delhi"));
        cityList.add(new City("Hyderabad"));
        cityList.add(new City("Indore"));
        cityList.add(new City("Jaipur"));
        cityList.add(new City("Kanpur"));
        cityList.add(new City("Kolkata"));
        cityList.add(new City("Lucknow"));
        cityList.add(new City("Mumbai"));
        cityList.add(new City("Nagpur"));
        cityList.add(new City("Nashik"));
        cityList.add(new City("Patna"));
        cityList.add(new City("Pune"));
        cityList.add(new City("Surat"));
        cityList.add(new City("Thane"));
        cityList.add(new City("Visakhapatnam"));

        // Add more cities as needed

        // Set up the adapter
        cityAdapter = new CityAdapter(cityList);
        cityRecyclerView.setAdapter(cityAdapter);

        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cityAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        confirmLocation=findViewById(R.id.confirmLocation);
//        confirmLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                City selectedCity = cityAdapter.getSelectedCity(); // Assuming you add this method to get the selected city.
//                if (selectedCity != null) {
//                    Intent intent = new Intent(LocationActivity.this, HomeActivity.class);
//                    intent.putExtra("selected_city", selectedCity.getName()); // Pass the city name
//                    startActivity(intent);
//                }
//
//            }
//        });
    }
}