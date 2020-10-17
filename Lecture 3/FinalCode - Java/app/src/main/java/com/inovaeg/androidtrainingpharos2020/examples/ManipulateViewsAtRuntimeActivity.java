package com.inovaeg.androidtrainingpharos2020.examples;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class ManipulateViewsAtRuntimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulate_views_at_runtime);

        TextView textView = findViewById(R.id.tv_manipulate_views_at_runtime);
        textView.setText("Manipulated!");
        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        textView.setPadding(50, 50, 50, 50);
    }
}