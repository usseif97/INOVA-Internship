package com.inovaeg.androidtrainingpharos2020.examples

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_create_views_at_runtime.*

class CreateViewsAtRuntimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_views_at_runtime)
        linear_create_view_at_runtime.addView(TextView(this).apply {
            text = "Hello world"
            setBackgroundColor(ContextCompat.getColor(this@CreateViewsAtRuntimeActivity, R.color.colorAccent))
        }, LinearLayout.LayoutParams(200, 200).apply {
            setMargins(50, 50, 50, 50)
        }
        )
    }
}