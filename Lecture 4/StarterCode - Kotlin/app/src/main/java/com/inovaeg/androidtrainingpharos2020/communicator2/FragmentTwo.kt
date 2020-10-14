package com.inovaeg.androidtrainingpharos2020.communicator2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.communicator.FragmentOneFragment
import kotlinx.android.synthetic.main.fragment_one2.*
import kotlinx.android.synthetic.main.fragment_two2.*

class FragmentTwo : Fragment() {


    private var value: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            value = arguments!!.getString(VALUE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number_text_view.text = value
    }

    companion object {
        private const val VALUE = "value"
        fun newInstance(paramValue: String?) : FragmentTwo {
            val fragment = FragmentTwo()
            val args = Bundle()
            args.putString(VALUE, paramValue)
            fragment.arguments = args
            return fragment
        }
    }

}