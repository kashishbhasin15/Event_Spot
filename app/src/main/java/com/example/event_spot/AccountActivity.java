package com.example.event_spot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends AppCompatActivity {

    AppCompatButton introsignIn, introsignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        introsignIn=findViewById(R.id.signInTextView);
        introsignUp=findViewById(R.id.signUpTextView);

        introsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AccountActivity", "Sign In Button Clicked");
                Intent intent=new Intent(AccountActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        introsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AccountActivity", "Sign Up Button Clicked");
                Intent intent=new Intent(AccountActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}