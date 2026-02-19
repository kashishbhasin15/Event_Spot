package com.example.event_spot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectDateActivity extends AppCompatActivity {
    AppCompatButton btnDate1, btnDate2, btnDate3, btnDate4, btnDate5;
    AppCompatButton lastSelectedButton;
    AppCompatButton selectVenueButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_date);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnDate1 = findViewById(R.id.btn_date_1);
        btnDate2 = findViewById(R.id.btn_date_2);
        btnDate3 = findViewById(R.id.btn_date_3);
        btnDate4 = findViewById(R.id.btn_date_4);
        btnDate5 = findViewById(R.id.btn_date_5);


        lastSelectedButton = btnDate3;


        btnDate1.setOnClickListener(v -> selectDateButton(btnDate1));
        btnDate2.setOnClickListener(v -> selectDateButton(btnDate2));
        btnDate3.setOnClickListener(v -> selectDateButton(btnDate3));
        btnDate4.setOnClickListener(v -> selectDateButton(btnDate4));
        btnDate5.setOnClickListener(v -> selectDateButton(btnDate5));

        selectVenueButton=findViewById(R.id.selectVenueButton);
        selectVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SelectDateActivity.this,VenuesActivity.class);
                startActivity(intent);
            }
        });
    }


    private void selectDateButton(AppCompatButton selectedButton) {

        lastSelectedButton.setBackgroundResource(R.drawable.default_date_button);
        lastSelectedButton.setElevation(0);


        selectedButton.setBackgroundResource(R.drawable.date_selector_button);
        selectedButton.setElevation(8);

        lastSelectedButton = selectedButton;
    }
}