package com.inovaeg.androidtrainingpharos2020.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_manipulate_views_at_runtime.*

class ManipulateViewsAtRuntimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manipulate_views_at_runtime)
        tv_manipulate_views_at_runtime.apply {
            text = "Manipulated!"
            setBackgroundColor(ContextCompat.getColor(this@ManipulateViewsAtRuntimeActivity, R.color.colorAccent))
            setPadding(50, 50, 50, 50)
        }

    }
}