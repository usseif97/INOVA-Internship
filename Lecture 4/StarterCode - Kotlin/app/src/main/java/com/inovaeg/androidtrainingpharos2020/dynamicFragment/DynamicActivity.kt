package com.inovaeg.androidtrainingpharos2020.dynamicFragment

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_dynamic.*

class DynamicActivity : AppCompatActivity() {

    private var activity_dynamic = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        if(savedInstanceState == null) // there's nothing in the activity
            addFragment()

        add_fragment_btn.setOnClickListener {
            //addFragment()
            Toast.makeText(this, "new Count = " + activity_dynamic++, Toast.LENGTH_LONG).show()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        // save the activity state & view but don't save the variables
        super.onSaveInstanceState(outState)
        outState.putInt("count", activity_dynamic)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // get the saved activity state
        super.onRestoreInstanceState(savedInstanceState)
        activity_dynamic = savedInstanceState.getInt("count", 0)
    }

    override fun onBackPressed() {
        if(supportFragmentManager
                        .fragments
                        .get(supportFragmentManager.fragments.size - 1)
                        .childFragmentManager
                        .backStackEntryCount > 0){
            supportFragmentManager
                    .fragments
                    .get(supportFragmentManager.fragments.size - 1)
                    .childFragmentManager
                    .popBackStack()
        } else{
            super.onBackPressed()
        }
    }

    private fun addFragment() {
        // It's the component in the activity that responsible for putting the fragment inside the activity & everything about the Fragments
        // put the fragments in the stack of the activity & handling the back stack
        supportFragmentManager
                .beginTransaction()
                .add(R.id.containeer, DynamicFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

}