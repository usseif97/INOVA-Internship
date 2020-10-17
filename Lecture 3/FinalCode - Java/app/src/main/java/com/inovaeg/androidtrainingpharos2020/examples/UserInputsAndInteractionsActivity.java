package com.inovaeg.androidtrainingpharos2020.examples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class UserInputsAndInteractionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inputs_and_interactions);

        final EditText nameEditText = findViewById(R.id.et_user_inputs_and_interactions_name);
        Button clickMeButton = findViewById(R.id.btn_user_inputs_and_interactions);
        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserInputsAndInteractionsActivity.this,
                        "Welcome " + nameEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}