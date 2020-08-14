package com.example.saloon.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saloon.HomeScreen;
import com.example.saloon.R;
import com.example.saloon.pojo.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends Fragment {
    private FirebaseAuth mAuth;
    private EditText username, email, password;
    private Button signup;

    public signup() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        mAuth = FirebaseAuth.getInstance();

        username = v.findViewById(R.id.username);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        signup = v.findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
//            Toast.makeText(getContext(), "Already Registerd", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {
        final String mname = username.getText().toString().trim();
        final String memail = email.getText().toString().trim();
        final String mpassword = password.getText().toString().trim();

        if (mname.isEmpty()) {
            username.setError("Empty Name");
            username.requestFocus();
            return;
        }

        if (memail.isEmpty()) {
            email.setError("Email is empty");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(memail).matches()) {
            email.setError("Wrong Email Address");
            email.requestFocus();
            return;
        }

        if (mpassword.isEmpty()) {
            password.setError("Password is empty");
            password.requestFocus();
            return;
        }

        if (mpassword.length() < 6) {
            password.setError("Weak Password");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(memail,mpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            user u  = new user(mname,memail,mpassword);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(getContext(), "Successfylly Registerd", Toast.LENGTH_SHORT).show();
                                        Intent home = new Intent(signup.getContext(), HomeScreen.class);
                                        startActivity(home);
                                        finisActivity();
                                    }else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                private void finisActivity() {
                                    if(getActivity() != null) {
                                        getActivity().finish();
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}