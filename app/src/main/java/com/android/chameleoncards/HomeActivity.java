package com.android.chameleoncards;

import android.os.Bundle;

import com.android.chameleoncards.databinding.ActivityHomeBinding;

public class HomeActivity extends MenuActivity {

    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        allocateActivityTitle("Home");
    }
}