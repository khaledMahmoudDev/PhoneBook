package com.example.phonebook.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.phonebook.R;
import com.example.phonebook.ui.fragment.ContactAddFragment;
import com.example.phonebook.ui.fragment.ContactListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main_activity,
                new ContactListFragment()).commit();


    }

    BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.menu_add_contact: {
                    selectedFragment = new ContactAddFragment();
                    break;
                }
                case R.id.menu_list_contact: {
                    selectedFragment = new ContactListFragment();
                    break;
                }

            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout_main_activity, selectedFragment).commit();
            return true;
        }
    };


}
