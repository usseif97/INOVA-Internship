package com.inovaeg.androidtrainingpharos2020.dynamicFragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class DynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        findViewById(R.id.bt_add_fragment)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFragment();
                    }
                });

    }

    private void addFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new DynamicFragment())
                .commitNowAllowingStateLoss();
    }
}