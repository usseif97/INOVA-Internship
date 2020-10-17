package com.inovaeg.androidtrainingpharos2020.examples

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_create_views_at_runtime.*

class CreateViewsAtRuntimeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_views_at_runtime)

        linear_create_view_at_runtime.addView(TextView(this).apply {
            text = "Hello World, this is done at Runtime"
            //R.color.colorPrimary
            setBackgroundColor(Color.GREEN)
        }, LinearLayout.LayoutParams(300, 300).apply {
            setMargins(50, 50, 50, 50)
            }
        )


        //TODO 1-create textView object and set it's background color and text


        //TODO 2- add the textview to your linear layout with width 200 and height 200 and margins 50
    }
}