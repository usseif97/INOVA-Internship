package com.inovaeg.androidtrainingpharos2020.finaldemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_netflix_movie_overview.*

class NetflixMovieOverviewActivity : AppCompatActivity() {
    private val isAddedToMyList = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_movie_overview)

        info_btn.setOnClickListener {
            startActivity(Intent(this@NetflixMovieOverviewActivity, NetflixMoviePlayOrDownloadActivity::class.java))
        }

    }
}