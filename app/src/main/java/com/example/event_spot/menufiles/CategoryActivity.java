package com.example.event_spot.menufiles;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.event_spot.R;

public class CategoryActivity extends BaseActivity {

    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavBar=findViewById(R.id.bottomNavBar);
        dynamicContent=findViewById(R.id.dynamicContent);
//        View wizard=getLayoutInflater().inflate(R.layout.activity_category,null);
//        dynamicContent.addView(wizard);

        if (dynamicContent != null) { // Check if dynamicContent is not null
            View wizard = getLayoutInflater().inflate(R.layout.activity_category, null);
            dynamicContent.addView(wizard);
        } else {
            Log.e("CategoryActivity", "dynamicContent is null");
        }

        RadioGroup rg=findViewById(R.id.radioGroup1);
        RadioButton rb=findViewById(R.id.buttonCategory);

//        rb.setBackgroundColor(R.color.item_selected);
//        rb.setTextColor(Color.parseColor("#3F5185"));

        if (rb != null) {
            rb.setBackgroundColor(getResources().getColor(R.color.item_selected)); // Use getResources() for color
            rb.setTextColor(Color.parseColor("#3F5185"));
        }
    }
}