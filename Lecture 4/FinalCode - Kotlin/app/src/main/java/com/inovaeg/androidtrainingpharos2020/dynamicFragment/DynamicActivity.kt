package com.inovaeg.androidtrainingpharos2020.dynamicFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_dynamic.*

class DynamicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
        bt_add_fragment
                .setOnClickListener { addFragment() }
    }

    private fun addFragment() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, DynamicFragment())
                .commitNowAllowingStateLoss()
    }
}