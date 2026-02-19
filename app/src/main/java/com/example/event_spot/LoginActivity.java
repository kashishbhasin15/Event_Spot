package com.example.event_spot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText emailEditText,passwordEditText;
    AppCompatButton signInButton;
    GridLayout signUpText;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.(?:[a-z]+\\.)*[a-z]{2,}$";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait ....");
        progressDialog.setCancelable(false);

        emailEditText=findViewById(R.id.loginEmail);
        passwordEditText=findViewById(R.id.loginPassword);
        signInButton=findViewById(R.id.signInButton);
        signInButton.setBackgroundResource(R.drawable.gradient_button1);

        signUpText=findViewById(R.id.signUpText);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                progressDialog.dismiss();

                String email=emailEditText.getEditableText().toString();
                String password=passwordEditText.getEditableText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern))
                {
                    progressDialog.dismiss();
                    emailEditText.setError("Invalid email");
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<=6)
                {
                    progressDialog.dismiss();
                    passwordEditText.setError("Invalid Password");
                    Toast.makeText(LoginActivity.this, "Please enter more than 6 Characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Error in login. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}