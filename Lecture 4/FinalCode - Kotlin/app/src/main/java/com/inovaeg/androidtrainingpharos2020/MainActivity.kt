package com.inovaeg.androidtrainingpharos2020

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.communicator.CommunicatorActivity
import com.inovaeg.androidtrainingpharos2020.dataStorage.StorageExamplesActivity
import com.inovaeg.androidtrainingpharos2020.dynamicFragment.DynamicActivity
import com.inovaeg.androidtrainingpharos2020.fragmentLifeCycle.TestFragmentLifeCycleActivity
import com.inovaeg.androidtrainingpharos2020.staticFragment.StaticFragmentActiviyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        static_activity.setOnClickListener { v: View? ->
            val staticActivity = Intent(this, StaticFragmentActiviyActivity::class.java)
            startActivity(staticActivity)
        }
        dynamic_activity.setOnClickListener { v: View? ->
            val staticActivity = Intent(this, DynamicActivity::class.java)
            startActivity(staticActivity)
        }
        test_life_cycle_activity.setOnClickListener { v: View? ->
            val staticActivity = Intent(this, TestFragmentLifeCycleActivity::class.java)
            startActivity(staticActivity)
        }
        bt_communicator_activity.setOnClickListener { v: View? ->
            val staticActivity = Intent(this, CommunicatorActivity::class.java)
            startActivity(staticActivity)
        }
        bt_storage.setOnClickListener { v: View? ->
            val staticActivity = Intent(this, StorageExamplesActivity::class.java)
            startActivity(staticActivity)
        }
    }
}