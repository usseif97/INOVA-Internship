package com.inovaeg.androidtrainingpharos2020.communicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R

class CommunicatorActivity : AppCompatActivity(), Communicator {
    private var fragmentOneFragment: FragmentOneFragment? = null
    private var fragmentTwoFragment: FragmentTwoFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicator)
        if (savedInstanceState == null) {
            fragmentOneFragment = FragmentOneFragment()
            fragmentTwoFragment = FragmentTwoFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.containor_two, fragmentTwoFragment!!)
                    .add(R.id.containor_one, fragmentOneFragment!!)
                    .commitNowAllowingStateLoss()
        } else {
            fragmentTwoFragment = supportFragmentManager.findFragmentById(R.id.containor_two) as FragmentTwoFragment?
            fragmentOneFragment = supportFragmentManager.findFragmentById(R.id.containor_one) as FragmentOneFragment?
        }
    }

    override fun updateCount(count: Int) {
        fragmentTwoFragment!!.updateCount(count)
    }
}