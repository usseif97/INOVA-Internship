package com.inovaeg.androidtrainingpharos2020.dynamicFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.staticFragment.StaticFragment

class DynamicFragment : Fragment() {

    private var fragment_dynamic = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dynamic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //addFragment()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", fragment_dynamic)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            fragment_dynamic = savedInstanceState.getInt("count", 0)
        }

    }

    private fun addFragment(){
        // It's the component in the Fragment that responsible for putting fragments inside the Fragment & everything about the Fragments
        // put the fragments in the stack of the activity & handling the back stack
        childFragmentManager
                .beginTransaction()
                .add(R.id.textView, StaticFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()

        // It's the component in the Fragment that responsible for the parent of the fragment itself
        //fragmentManager
    }

}