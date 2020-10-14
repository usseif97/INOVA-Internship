package com.inovaeg.androidtrainingpharos2020.dataStorage;

import android.os.Bundle;

import com.inovaeg.androidtrainingpharos2020.dataStorage.sqlLite.DBHelper;

import java.util.List;

public class DataBaseActivity extends BaseStorageActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
    }

    @Override
    protected void saveNamesInDatabase(List<String> names) {
//        TODO save names in the database
        dbHelper.deleteAll();
        dbHelper.insertAll(names);
    }

    @Override
    protected List<String> restoreFromDatabase() {
//        TODO restore names from the database
        return dbHelper.getAllNames();

    }
}