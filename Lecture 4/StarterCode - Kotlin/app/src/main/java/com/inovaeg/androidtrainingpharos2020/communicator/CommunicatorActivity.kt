package com.inovaeg.androidtrainingpharos2020.communicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R

class CommunicatorActivity : AppCompatActivity(), Communicator {
    /*TODO create Communicator interface and make the activity implement it add the need functions */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicator)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container1, FragmentTwoFragment())
                    .add(R.id.container2, FragmentOneFragment())
                    .commitAllowingStateLoss()

    }

    override fun updateCount() {
        val fragment: FragmentTwoFragment? = supportFragmentManager.findFragmentById(R.id.container1) as FragmentTwoFragment?
        fragment?.updateCount()

    }


}

