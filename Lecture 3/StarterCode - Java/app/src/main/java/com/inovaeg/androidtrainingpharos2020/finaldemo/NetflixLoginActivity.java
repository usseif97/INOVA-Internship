package com.inovaeg.androidtrainingpharos2020.finaldemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class NetflixLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix_login);

        //TODO add text watcher to emailEditText to make button enabled when it's not empty

        //TODO add text watcher to passwordEditText to make button enabled when it's not empty

        //TODO make sure that both watchers check on the other edittext that it's not empty too

        //TODO add clickListener to login button to go to NetflixMovieOverviewActivity

    }
}