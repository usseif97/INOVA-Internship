package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_storage_examples.*

class StorageExamplesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_examples)
        bt_sharedPref.setOnClickListener { v: View? ->
            val intent = Intent(this, SharedPrefActivity::class.java)
            intent.putExtra(BaseStorageActivity.Companion.TYPE_KEY, BaseStorageActivity.Companion.TYPE_SHARED_PREF)
            startActivity(intent)
        }
        bt_dataBase.setOnClickListener { v: View? ->
            val intent = Intent(this, DataBaseActivity::class.java)
            intent.putExtra(BaseStorageActivity.Companion.TYPE_KEY, BaseStorageActivity.Companion.TYPE_DATABASE)
            startActivity(intent)
        }
        bt_file.setOnClickListener { v: View? ->
            val intent = Intent(this, FileEampleActivity::class.java)
            intent.putExtra(BaseStorageActivity.Companion.TYPE_KEY, BaseStorageActivity.Companion.TYPE_FILE_SAVE)
            startActivity(intent)
        }
    }
}