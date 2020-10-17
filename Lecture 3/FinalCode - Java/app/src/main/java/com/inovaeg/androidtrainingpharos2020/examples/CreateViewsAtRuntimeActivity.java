package com.inovaeg.androidtrainingpharos2020.examples;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class CreateViewsAtRuntimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_views_at_runtime);

        LinearLayout rootLinearLayout = findViewById(R.id.linear_create_view_at_runtime);

        TextView newTextView = new TextView(this);
        newTextView.setText("Hello world");
        newTextView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        layoutParams.setMargins(50, 50, 50, 50);
        rootLinearLayout.addView(newTextView, layoutParams);
    }
}