package com.inovaeg.androidtrainingpharos2020.finaldemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class NetflixLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix_login);

        final EditText emailEditText = findViewById(R.id.et_netflix_login_email);
        final EditText passwordEditText = findViewById(R.id.et_netflix_login_password);
        final Button loginButton = findViewById(R.id.btn_netflix_login);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginButton.setEnabled(!s.toString().isEmpty() && !passwordEditText.getText().toString().isEmpty());
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginButton.setEnabled(!s.toString().isEmpty() && !emailEditText.getText().toString().isEmpty());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NetflixLoginActivity.this, NetflixMovieOverviewActivity.class));
                //used to remove this activity from the stack, so when the user clicks on back button in NetflixMovieOverviewActivity the app closes
                finish();
            }
        });
    }
}