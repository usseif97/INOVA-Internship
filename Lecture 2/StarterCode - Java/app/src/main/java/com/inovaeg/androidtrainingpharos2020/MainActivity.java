package com.inovaeg.androidtrainingpharos2020;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO add log.d to see the lifecycle in logs

        findViewById(R.id.bt_take_permission).setOnClickListener(v -> {
            //TODO check for Camera permission
            //TODO take camera permission to start it
        });

        findViewById(R.id.bt_start_camera).setOnClickListener(v -> {
            //TODO check for camera permission
            //TODO start camera intent to take picture
        });
        findViewById(R.id.bt_check_location).setOnClickListener(v -> {
            //TODO check for location permission
            //TODO start location listener
            //TODO when you get location cancel the listener and start map intent
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO add log.d to see the lifecycle in logs
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO add log.d to see the lifecycle in logs
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO add log.d to see the lifecycle in logs
    }

    @Override
    protected void onStop() {
        super.onStop();
        //TODO add log.d to see the lifecycle in logs
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO add log.d to see the lifecycle in logs
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //TODO add log.d to see the lifecycle in logs
    }
    
}