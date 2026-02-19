package com.example.event_spot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.event_spot.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    GridLayout signInText;
    EditText regName,regEmail,regPhone,regPassword,regConfirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.(?:[a-z]+\\.)*[a-z]{2,}$";
    String phonePattern ="[0-9]{10}";
    AppCompatButton signUpButton;
    String imgUri;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();

        regName=findViewById(R.id.regName);
        regEmail=findViewById(R.id.regEmail);
        regPhone=findViewById(R.id.regPhone);
        regPassword=findViewById(R.id.regPassword);
        regConfirmPassword=findViewById(R.id.regConfirmPassword);


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait ....");
        progressDialog.setCancelable(false);

        signInText=findViewById(R.id.signInText);

        signUpButton=findViewById(R.id.signUpButton);
        signUpButton.setBackgroundResource(R.drawable.gradient_button1);
        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                progressDialog.show();


                String name=regName.getText().toString();
                String email=regEmail.getText().toString();
                String password=regPassword.getText().toString();
                String confirmpass=regConfirmPassword.getText().toString();
                String phone=regPhone.getText().toString();

                //invalid cases
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) ||  TextUtils.isEmpty(password) ||  TextUtils.isEmpty(confirmpass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please enter some valid data !", Toast.LENGTH_SHORT).show();
                }
                else if(!phone.matches(phonePattern))
                {
                    progressDialog.dismiss();
                    regPhone.setError("Invalid phone number");
                    Toast.makeText(RegisterActivity.this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern))
                {
                    progressDialog.dismiss();
                    regEmail.setError("Invalid email");
                    Toast.makeText(RegisterActivity.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<=6)
                {
                    progressDialog.dismiss();
                    regPassword.setError("Invalid password ");
                    Toast.makeText(RegisterActivity.this, "Please enter more than 6 character", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirmpass))
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {

                                DatabaseReference reference=database.getReference().child("users").child(auth.getCurrentUser().getUid());


                                StorageReference storageReference=storage.getReference().child("upload").child(auth.getCurrentUser().getUid());

                                imgUri="https://firebasestorage.googleapis.com/v0/b/event-spot-8a377.appspot.com/o/profilepic.jpeg?alt=media&token=e303b001-e576-460a-b169-8837b8588cac";

                                Users users=new Users(auth.getCurrentUser().getUid(),name,email,imgUri);

                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                            progressDialog.dismiss();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        }
                                        else
                                        {
                                            progressDialog.dismiss();
                                            Toast.makeText(RegisterActivity.this, "Error in creating new user", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signInText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}