package com.example.event_spot.menufiles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.event_spot.HomeActivity;
import com.example.event_spot.R;

import kotlinx.coroutines.selects.SelectClause2;

public class BaseActivity extends AppCompatActivity {

    RadioGroup radiogroup1;
    RadioButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radiogroup1=findViewById(R.id.radioGroup1);
        home=findViewById(R.id.buttonHome);

        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Intent in=null;

                if(i==R.id.buttonHome)
                {
                    in=new Intent(getBaseContext(), HomeActivity.class);
                }
                else if(i==R.id.buttonCategory)
                {
                    in=new Intent(getBaseContext(), CategoryActivity.class);
                }
                else if(i==R.id.buttonLikeEvents)
                {
                    in=new Intent(getBaseContext(),LikedEventsActivity.class);
                }
                else if(i==R.id.buttonLiveEvent)
                {
                    in=new Intent(getBaseContext(),LiveEventsActivity.class);
                }
                else if(i==R.id.buttonProfile)
                {
                    in=new Intent(getBaseContext(), UserProfileActivity.class);
                }

                if (in != null) {
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }
}