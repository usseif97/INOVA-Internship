package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.content.SharedPreferences
import android.os.Bundle
import java.util.*

class SharedPrefActivity : BaseStorageActivity() {
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE)
    }

    override fun saveNamesInSharedPref(names: List<String>?) {
        names?.let {
            sharedPref.edit()
                    .putStringSet(BaseStorageActivity.Companion.TYPE_KEY, HashSet(names))
                    .apply()
        }
    }

    override fun restoreFromSharedPref(): MutableList<String>? {
        val stringSet = sharedPref.getStringSet(BaseStorageActivity.Companion.TYPE_KEY, HashSet())
                ?: HashSet()
        return ArrayList(stringSet)
    }
}