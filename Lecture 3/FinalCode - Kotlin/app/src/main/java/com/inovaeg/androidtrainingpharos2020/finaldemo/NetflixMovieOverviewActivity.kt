package com.inovaeg.androidtrainingpharos2020.finaldemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.finaldemo.NetflixMovieOverviewActivity
import kotlinx.android.synthetic.main.activity_netflix_movie_overview.*

class NetflixMovieOverviewActivity : AppCompatActivity() {
    private var isAddedToMyList = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_movie_overview)

        btn_movie_overview_my_list.setOnClickListener {
            isAddedToMyList = !isAddedToMyList
            val drawableId = if (isAddedToMyList) R.drawable.ic_check else R.drawable.ic_add
            btn_movie_overview_my_list.setCompoundDrawablesWithIntrinsicBounds(null,
                    ContextCompat.getDrawable(this@NetflixMovieOverviewActivity,
                            drawableId), null, null)
        }
    }
}