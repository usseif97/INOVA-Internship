package com.inovaeg.androidtrainingpharos2020.examples

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_manipulate_views_at_runtime.*

class ManipulateViewsAtRuntimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manipulate_views_at_runtime)

        tv_manipulate_views_at_runtime.setText("Changed at the Runtime")
        tv_manipulate_views_at_runtime.setBackgroundColor(Color.RED)
        tv_manipulate_views_at_runtime.setPadding(50,50,50,50)


        //TODO change text

        //TODO change background color

        //TODO change padding
    }
}