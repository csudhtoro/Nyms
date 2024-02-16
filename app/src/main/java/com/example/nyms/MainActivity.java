package com.example.nyms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nyms.fragments.GameFragment;

public class MainActivity extends AppCompatActivity {

    //initialize fragments
    GameFragment gameFragment = new GameFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, gameFragment)
                .addToBackStack(null)
                .commit();

    }
}