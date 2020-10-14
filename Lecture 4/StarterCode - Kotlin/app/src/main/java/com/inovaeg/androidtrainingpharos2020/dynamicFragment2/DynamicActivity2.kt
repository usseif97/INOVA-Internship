package com.inovaeg.androidtrainingpharos2020.dynamicFragment2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.dynamicFragment.DynamicFragment
import kotlinx.android.synthetic.main.activity_dynamic2.*

class DynamicActivity2 : AppCompatActivity() {

    var fragmentsCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic2)

        add_fragment_btn.setOnClickListener {
            fragmentsCount++
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.containerr, DynamicFragment2())
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        // save the activity state & view but don't save the variables
        super.onSaveInstanceState(outState)
        outState.putInt("count", fragmentsCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // get the saved activity state
        super.onRestoreInstanceState(savedInstanceState)
        fragmentsCount = savedInstanceState.getInt("count", 0)
    }

}


