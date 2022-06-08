package com.example.socialmedia.screen;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.socialmedia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeLayout extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        bottomNavigationView = findViewById(R.id.BottomNavBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(navLestner);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLay,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLestner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    System.out.println(item.getItemId());
                    switch (item.getItemId()){
                        case R.id.Home_screen_bottom:
                            System.out.println("Done Home");
                            fragment = new HomeFragment();
                            break;
                        case R.id.uploadFragment:
                            System.out.println("Done upload");
                            fragment = new UploadFragment();
                            break;
                        case R.id.Favorite_screen_bottom:
                            System.out.println("Done Favorite");
                            fragment = new FavoriteFragment();
                            break;

                        case R.id.Setting_screen_bottom:
                            System.out.println("Done Profile ");
                            fragment = new Profile();


                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.FrameLay,fragment).commit();

                    return true;
                }
            };
}

