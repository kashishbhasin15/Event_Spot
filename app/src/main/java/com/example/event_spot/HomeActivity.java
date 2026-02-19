package com.example.event_spot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;

import androidx.activity.EdgeToEdge;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.event_spot.menufiles.BaseActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private LinearLayout homeLayout;

    FirebaseStorage storage;
    StorageReference storageReference;
    public ImageSlider sliderView;
    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;
    TextView location;
    CardView card1;
    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeLayout = findViewById(R.id.homeLayout);
        bottomNavBar=findViewById(R.id.bottomNavBar);
        dynamicContent=findViewById(R.id.dynamicContent);

        if (dynamicContent != null) {
            View wizard = getLayoutInflater().inflate(R.layout.activity_home, null);
            dynamicContent.addView(wizard);
        } else {
            Log.e("HomeActivity", "dynamicContent is null");
        }

        RadioGroup rg=findViewById(R.id.radioGroup1);
        RadioButton rb=findViewById(R.id.buttonHome);

        if (rb != null) {
            rb.setBackgroundColor(getResources().getColor(R.color.item_selected));
            rb.setTextColor(Color.parseColor("#3F5185"));
        }

//        location = findViewById(R.id.location);
//        Intent in = getIntent();
//        String selectedCity = in.getStringExtra("selected_city");
//
//        if (selectedCity != null && !selectedCity.isEmpty()) {
//            location.setText(selectedCity);
//        }


        sliderView = findViewById(R.id.imageSlider);

        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.event1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.event2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.event3, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.event4, ScaleTypes.CENTER_CROP));

        if (sliderView != null) {
            sliderView.setImageList(imageList);
        }
        //just checking connectivity
        card1=findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this,MoviesActivity.class);
                startActivity(intent);
            }
        });
    }
}