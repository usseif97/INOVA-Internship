package com.inovaeg.androidtrainingpharos2020.communicator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class CommunicatorActivity extends AppCompatActivity implements Communicator {
    private FragmentOneFragment fragmentOneFragment;
    private FragmentTwoFragment fragmentTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicator);
        if (savedInstanceState == null) {
            fragmentOneFragment = new FragmentOneFragment();
            fragmentTwoFragment = new FragmentTwoFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.containor_two, fragmentTwoFragment)
                    .add(R.id.containor_one, fragmentOneFragment)
                    .commitNowAllowingStateLoss();
        } else {
            fragmentTwoFragment = (FragmentTwoFragment) getSupportFragmentManager().findFragmentById(R.id.containor_two);
            fragmentOneFragment = (FragmentOneFragment) getSupportFragmentManager().findFragmentById(R.id.containor_one);
        }
    }

    @Override
    public void updateCount(int count) {
        fragmentTwoFragment.updateCount(count);
    }
}