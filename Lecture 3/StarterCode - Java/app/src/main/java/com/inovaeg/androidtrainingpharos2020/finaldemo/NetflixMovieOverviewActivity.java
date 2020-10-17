package com.inovaeg.androidtrainingpharos2020.finaldemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class NetflixMovieOverviewActivity extends AppCompatActivity {

    private Boolean isAddedToMyList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netflix_movie_overview);

        //todo add click listener to change mylistbutton icon to checked when clicked and to add when clicked again
    }
}