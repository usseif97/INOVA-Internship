package com.inovaeg.androidtrainingpharos2020.dataStorage;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPrefActivity extends BaseStorageActivity {
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
    }

    @Override
    protected void saveNamesInSharedPref(List<String> names) {
        sharedPref.edit()
                .putStringSet(BaseStorageActivity.TYPE_KEY, new HashSet<>(names))
                .apply();
    }

    @Override
    protected List<String> restoreFromSharedPref() {
        Set<String> stringSet = sharedPref.getStringSet(BaseStorageActivity.TYPE_KEY, new HashSet<>());
        return new ArrayList<>(stringSet);

    }

}