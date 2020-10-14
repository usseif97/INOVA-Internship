package com.inovaeg.androidtrainingpharos2020.fragmentLifeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.inovaeg.androidtrainingpharos2020.R;


public class LifeCycleFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//TODO add log statement
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO add log statement
        return inflater.inflate(R.layout.fragment_life_cycle, container, false);
    }
    /*TODO fragment life cycles and add logs to test it*/
}