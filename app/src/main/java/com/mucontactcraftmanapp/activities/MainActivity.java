package com.mucontactcraftmanapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.fragments.ContratcsFragment;
import com.mucontactcraftmanapp.fragments.HomeFragment;
import com.mucontactcraftmanapp.fragments.OrdersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BottomNavigationView) findViewById(R.id.navigation))
                .setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                return navigateAccordingTo(item.getItemId());
                            }
                        });
        navigateAccordingTo(R.id.navigation_home);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_orders: return new OrdersFragment();
            case R.id.navigation_contracts: return  new ContratcsFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragmentFor(id))
                    .commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sources, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent intent = new Intent (getApplicationContext(), AboutUserActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }}

