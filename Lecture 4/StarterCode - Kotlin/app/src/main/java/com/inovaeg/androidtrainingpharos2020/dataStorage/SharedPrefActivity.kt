package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

class SharedPrefActivity : BaseStorageActivity() {

    // lateinit used when it assure that the variable cannot be null
    // but wait till it initialized
    // and here must be initialized in onCreate() method
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("SharedPrefActivity", Context.MODE_PRIVATE)
    }

    override fun saveNamesInSharedPref(names: List<String?>?) {
        if(names != null)
            sharedPref
                    .edit()
                    .putStringSet("SharedPrefActivity", HashSet(names))
                    .apply()
            // apply used to generate thread and use it
    }

    override fun restoreFromSharedPref(): MutableList<String?>? {
        val stringSet = sharedPref.getStringSet("SharedPrefActivity", null)
        if(stringSet != null)
            return ArrayList(stringSet)
        return null
    }
}