package com.inovaeg.androidtrainingpharos2020.dynamicFragment2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.communicator.Communicator
import com.inovaeg.androidtrainingpharos2020.fragmentLifeCycle.LifeCycleFragment
import kotlinx.android.synthetic.main.fragment_dynamic2.*
import java.util.*

class DynamicFragment2 : Fragment() {

    private var dynamicActivity: DynamicActivity2? = null
    private var  number = 0
    private var color = 0
    private val TAG = "LifeCycle "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dynamic2, container, false)
        Log.d(TAG, "onCreateView: ")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dynamicActivity = context as DynamicActivity2
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        number = dynamicActivity!!.fragmentsCount
        val rnd = Random()
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        Log.d(TAG, "onCreate: ")
        Log.d(TAG, "number: " + number)
        Log.d(TAG, "color: " + color)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("num", number)
        outState.putInt("col", color)

        Log.d(TAG, "onSaveInstanceState: ")
        Log.d(TAG, "number: " + number)
        Log.d(TAG, "color: " + color)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("num", 0)
            color = savedInstanceState.getInt("col", 0)
        }

        Log.d(TAG, "onViewStateRestored: ")
        Log.d(TAG, "number: " + number)
        Log.d(TAG, "color: " + color)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("num", 0)
            color = savedInstanceState.getInt("col", 0)
        }

        textView.text = "Fragment NO." + number.toString()
        view.setBackgroundColor(color)

        Log.d(TAG, "onViewCreated: ")
        Log.d(TAG, "number: " + number)
        Log.d(TAG, "color: " + color)
    }

}