package com.example.saloon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.saloon.fragment.CartFragment;
import com.example.saloon.fragment.FavouriteFragment;
import com.example.saloon.fragment.HomeFragment;
import com.example.saloon.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment).commit();

        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;

            switch(item.getItemId()){
                case R.id.home:
                    selectedFrag = new HomeFragment();
                    break;
                case R.id.cart:
                    selectedFrag = new CartFragment();
                    break;

                case R.id.favourite:
                    selectedFrag = new FavouriteFragment();
                    break;

                case R.id.profile:
                    selectedFrag = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,selectedFrag).commit();
            return true;

        }

    };

}