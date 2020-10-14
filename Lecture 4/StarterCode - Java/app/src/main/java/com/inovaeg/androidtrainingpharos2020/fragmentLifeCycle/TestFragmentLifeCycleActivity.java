package com.inovaeg.androidtrainingpharos2020.fragmentLifeCycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class TestFragmentLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment_life_cycle);
        //TODO add logs in the activity to see the fragment and activity both together

    }
}