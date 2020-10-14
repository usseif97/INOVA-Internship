package com.inovaeg.androidtrainingpharos2020.staticFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inovaeg.androidtrainingpharos2020.R

class StaticFragment : Fragment() { /*TODO
     * extend @androidx.fragment.app.Fragment
     * create layout for the fragment
     * override onCreateView to inflate the xml
     * */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // attachToRoot: false because the fragment & activity can handle each others
        return inflater.inflate(R.layout.fragment_static, container, false)
    }



}