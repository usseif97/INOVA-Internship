package com.inovaeg.androidtrainingpharos2020;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.communicator.CommunicatorActivity;
import com.inovaeg.androidtrainingpharos2020.dataStorage.StorageExamplesActivity;
import com.inovaeg.androidtrainingpharos2020.dynamicFragment.DynamicActivity;
import com.inovaeg.androidtrainingpharos2020.staticFragment.StaticFragmentActiviyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.static_activity).setOnClickListener(v -> {
            Intent staticActivity = new Intent(this, StaticFragmentActiviyActivity.class);
            startActivity(staticActivity);
        });
        findViewById(R.id.dynamic_activity).setOnClickListener(v -> {
            Intent staticActivity = new Intent(this, DynamicActivity.class);
            startActivity(staticActivity);
        });
        findViewById(R.id.test_life_cycle_activity).setOnClickListener(v -> {
            Intent staticActivity = new Intent(this, DynamicActivity.class);
            startActivity(staticActivity);
        });
        findViewById(R.id.bt_communicator_activity).setOnClickListener(v -> {
            Intent staticActivity = new Intent(this, CommunicatorActivity.class);
            startActivity(staticActivity);
        });
        findViewById(R.id.bt_storage).setOnClickListener(v -> {
                    Intent staticActivity = new Intent(this, StorageExamplesActivity.class);
                    startActivity(staticActivity);
                }
        );
    }
}