package com.inovaeg.androidtrainingpharos2020.finaldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R

class NetflixMoviePlayOrDownloadActivity : AppCompatActivity() {
    private val isAddedToMyList = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_movie_play_or_download)
    }
}