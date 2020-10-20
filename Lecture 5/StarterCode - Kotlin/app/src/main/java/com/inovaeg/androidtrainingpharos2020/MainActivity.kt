package com.inovaeg.androidtrainingpharos2020

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.facebooktTask.FacebookProfileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Here We Are Begin", Toast.LENGTH_LONG).show();


        facebook_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, FacebookProfileActivity::class.java))
        }


        start_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, MoviesListActivity::class.java))
        }

    }

}