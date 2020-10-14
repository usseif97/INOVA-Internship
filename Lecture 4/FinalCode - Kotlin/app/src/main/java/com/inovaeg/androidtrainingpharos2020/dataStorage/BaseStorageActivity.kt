package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_shared_pref.*
import java.util.*

open class BaseStorageActivity : AppCompatActivity() {
    private var names: MutableList<String> = ArrayList()
    private var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras != null && intent.extras!!.getString(TYPE_KEY) != null) {
            type = intent.extras!!.getString(TYPE_KEY)
        }
        setContentView(R.layout.activity_shared_pref)

        findViewById<View>(R.id.bt_add).setOnClickListener { v: View? ->
            if (ed_name.getText() != null && ed_name.getText().length > 0) {
                names.add(ed_name.getText().toString())
            }
            tv_names.setText(joinInSingleString(names))
            ed_name.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        when (type) {
            TYPE_SHARED_PREF -> {
                saveNamesInSharedPref(names)
            }
            TYPE_DATABASE -> {
                saveNamesInDatabase(names)
            }
            TYPE_FILE_SAVE -> {
                saveNamesInFile(names)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        when (type) {
            TYPE_SHARED_PREF -> {
                setNames(restoreFromSharedPref())
            }
            TYPE_DATABASE -> {
                setNames(restoreFromDatabase())
            }
            TYPE_FILE_SAVE -> {
                setNames(restoreFromFile())
            }
        }
        findViewById<View>(R.id.bt_add).callOnClick()
    }

    private fun setNames(names: MutableList<String>?) {
        if (names != null) {
            this.names = names
        }
    }

    //    sharedPref methods
    protected open fun saveNamesInSharedPref(names: List<String>?) {}
    protected open fun restoreFromSharedPref(): MutableList<String>? {
        return null
    }

    ///////////////
    //////////file
    protected open fun saveNamesInFile(names: List<String>?) {}
    protected open fun restoreFromFile(): MutableList<String>? {
        return null
    }

    //////////////
    //DataBase
    protected open fun saveNamesInDatabase(names: List<String>) {}
    protected open fun restoreFromDatabase(): MutableList<String>? {
        return null
    }

    ////////////
    protected fun joinInSingleString(names: List<String?>): String {
        val joinedNames = StringBuilder()
        for (name in names) {
            joinedNames.append(name)
            joinedNames.append(" ")
        }
        return joinedNames.toString()
    }

    companion object {
        const val TYPE_SHARED_PREF = "TYPE_SHARED_PREF"
        const val TYPE_DATABASE = "TYPE_DATABASE"
        const val TYPE_FILE_SAVE = "TYPE_FILE_SAVE"
        const val TYPE_KEY = "TYPE_KEY"
    }
}