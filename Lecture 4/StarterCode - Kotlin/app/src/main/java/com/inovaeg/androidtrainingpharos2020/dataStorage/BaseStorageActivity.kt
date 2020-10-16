package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_shared_pref.*
import java.io.IOException
import java.io.PrintWriter
import java.util.*

open class BaseStorageActivity : AppCompatActivity() {

    private var names: MutableList<String?> = ArrayList()
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras != null && intent.extras!!.getString(TYPE_KEY) != null) {
            type = intent.extras!!.getString(TYPE_KEY)
        }
        setContentView(R.layout.activity_shared_pref)

        bt_add.setOnClickListener { v: View? ->
            if (ed_name.getText() != null && ed_name.getText().length > 0) {
                names.add(ed_name.getText().toString())
            }
            tv_names.setText(joinInSingleString(names))
            ed_name.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // (when) like switch in Java
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

    private fun setNames(names: MutableList<String?>?) {
        if (names != null) {
            this.names = names
        }
    }

    // In Kotlin, all the classes are final by default
    // So, to make a class inheritable to the other classes,
    // you must mark it with the open keyword otherwise you will get an error saying “type is final so can’t be inherited”.

    // sharedPref methods
    protected open fun saveNamesInSharedPref(names: List<String?>?) {}
    protected open fun restoreFromSharedPref(): MutableList<String?>? {
        return null
    }

    // file
    protected open fun saveNamesInFile(names: List<String?>?) {}
    protected open fun restoreFromFile(): MutableList<String?>? {
        return null
    }


    //DataBase
    protected open fun saveNamesInDatabase(names: List<String?>?) {}
    protected open fun restoreFromDatabase(): MutableList<String?>? {
        return null
    }

    // used in File
    protected open fun joinInSingleString(names: List<String?>): String {
        return names.joinToString(separator = " | ")
    }

    // used in File
    protected open fun clearFile(fileName : String, mode : Int) {
        try {
            val pw = PrintWriter(openFileOutput(fileName, mode))
            pw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Like (Static Variables) in Java
    companion object {
        const val TYPE_SHARED_PREF = "TYPE_SHARED_PREF"
        const val TYPE_DATABASE = "TYPE_DATABASE"
        const val TYPE_FILE_SAVE = "TYPE_FILE_SAVE"
        const val TYPE_KEY = "TYPE_KEY"
    }
}