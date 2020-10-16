package com.inovaeg.androidtrainingpharos2020.dataStorage

import android.content.Context
import android.os.Bundle
import java.io.*

class FileEampleActivity : BaseStorageActivity() {

    private var savingFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create file in the cache
        savingFile = File(cacheDir, "saveFile")
        if(!savingFile!!.exists()){
            try {
                savingFile!!.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    override fun saveNamesInFile(names: List<String?>?) {
        if(names != null){
            try {
                val dataOutputStream = BufferedWriter(OutputStreamWriter(openFileOutput("saveFile", MODE_APPEND)))
                dataOutputStream
                        .write(joinInSingleString(names))
                dataOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

    override fun restoreFromFile(): MutableList<String?>? {

        try {
            val dataInputStream = BufferedReader(InputStreamReader(openFileInput("saveFile")))
            val readUTF = dataInputStream
                    .readLine()
            val strings: List<String>? = readUTF?.split(" | ")
            dataInputStream.close()
            //if you remove the clear method you will see repeated values try it
            clearFile("saveFile", Context.MODE_PRIVATE)

            return strings?.takeIf { it.isNotEmpty() }?.toMutableList() ?: ArrayList()
            // return (strings.length > 0) ? new ArrayList<>(Arrays.asList(strings)) : null;
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}