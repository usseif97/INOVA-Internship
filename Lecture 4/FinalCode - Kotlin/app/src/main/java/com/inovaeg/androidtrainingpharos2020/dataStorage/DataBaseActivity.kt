package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.os.Bundle
import com.inovaeg.androidtrainingpharos2020.dataStorage.sqlLite.DBHelper

class DataBaseActivity : BaseStorageActivity() {
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DBHelper(this)
    }

    override fun saveNamesInDatabase(names: List<String>) {
//        TODO save names in the database
        dbHelper.deleteAll()
        dbHelper.insertAll(names)
    }

    override fun restoreFromDatabase(): MutableList<String> {
//        TODO restore names from the database
        return dbHelper.allNames
    }
}