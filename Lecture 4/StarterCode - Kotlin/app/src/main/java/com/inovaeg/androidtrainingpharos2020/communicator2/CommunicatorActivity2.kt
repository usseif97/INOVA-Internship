package com.inovaeg.androidtrainingpharos2020.communicator2

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R

class CommunicatorActivity2 : AppCompatActivity(), Communicator2 {

    private var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isTablet = isTabletCheck(this)

        if(isTablet){
            setContentView(R.layout.activity_communicator2_tablet)
            if (savedInstanceState == null)
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container1, FragmentOne())
                        .commitAllowingStateLoss()

        } else {
            setContentView(R.layout.activity_communicator2)
            if (savedInstanceState == null)
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragment_container, FragmentOne())
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
        }

    }

    override fun btnClicked(value : String) {

        val fragmentTwo = FragmentTwo.newInstance(value)

        if(isTablet){
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container2, fragmentTwo)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentTwo)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        }
    }

    private fun isTabletCheck(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

}