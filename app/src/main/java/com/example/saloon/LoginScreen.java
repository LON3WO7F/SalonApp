package com.example.saloon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saloon.fragment.login;
import com.example.saloon.fragment.signup;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{
     TextView signin,signup;
     RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.singup);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
        relativeLayout = findViewById(R.id.relative);
        fragments();
            }



            public void fragments(){

                Fragment fragment = new login();
                FragmentManager fragmentManager   = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,fragment);
                fragmentTransaction.commit();
            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin:
               fragments();


                break;

            case R.id.singup:
                Fragment signupFragment = new signup();
                FragmentManager signupFragmentManager   = getSupportFragmentManager();
                FragmentTransaction signupFragmentTransaction = signupFragmentManager.beginTransaction();
                signupFragmentTransaction.replace(R.id.fragment,signupFragment).addToBackStack(null);
                signupFragmentTransaction.commit();

                break;
        }

    }
}