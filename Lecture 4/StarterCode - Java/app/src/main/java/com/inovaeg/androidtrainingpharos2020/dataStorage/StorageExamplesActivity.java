package com.inovaeg.androidtrainingpharos2020.dataStorage;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

public class StorageExamplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_examples);
        findViewById(R.id.bt_sharedPref).setOnClickListener(v -> {
            Intent intent = new Intent(this, SharedPrefActivity.class);
            intent.putExtra(BaseStorageActivity.TYPE_KEY, BaseStorageActivity.TYPE_SHARED_PREF);
            startActivity(intent);
        });
        findViewById(R.id.bt_dataBase).setOnClickListener(v -> {
            Intent intent = new Intent(this, DataBaseActivity.class);
            intent.putExtra(BaseStorageActivity.TYPE_KEY, BaseStorageActivity.TYPE_DATABASE);
            startActivity(intent);
        });
        findViewById(R.id.bt_file).setOnClickListener(v -> {
            Intent intent = new Intent(this, FileEampleActivity.class);
            intent.putExtra(BaseStorageActivity.TYPE_KEY, BaseStorageActivity.TYPE_FILE_SAVE);
            startActivity(intent);
        });
    }
}