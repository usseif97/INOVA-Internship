package com.inovaeg.androidtrainingpharos2020.examples

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.facebookTask.FacebookHomeActivity
import com.inovaeg.androidtrainingpharos2020.facebookTask.FacebookProfileActivity
import com.inovaeg.androidtrainingpharos2020.finaldemo.NetflixLoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Here We Are Begin", Toast.LENGTH_LONG).show();


        facebook_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, FacebookProfileActivity::class.java))
        }

        netflix_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, NetflixLoginActivity::class.java))
        }

        start_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, ManipulateViewsAtRuntimeActivity::class.java))
        }

    }
}