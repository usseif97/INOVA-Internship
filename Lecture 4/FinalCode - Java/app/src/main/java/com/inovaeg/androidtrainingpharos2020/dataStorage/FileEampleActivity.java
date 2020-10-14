package com.inovaeg.androidtrainingpharos2020.dataStorage;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileEampleActivity extends BaseStorageActivity {
    private File savingFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savingFile = new File(getCacheDir(), "saveFile");
        if (!savingFile.exists()) {
            try {
                savingFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void saveNamesInFile(List<String> names) {

        try {
            BufferedWriter dataOutputStream = new BufferedWriter(new OutputStreamWriter(openFileOutput("saveFile", MODE_APPEND)));
            dataOutputStream
                    .write(joinInSingleString(names));
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<String> restoreFromFile() {
        try {
            BufferedReader dataInputStream = new BufferedReader(new InputStreamReader(openFileInput("saveFile")));
            String readUTF = dataInputStream
                    .readLine();

            String[] strings = (readUTF != null) ? readUTF.split(" ") : new String[0];
            dataInputStream.close();
            //if you remove the clear method you will see repeated values try it
            clearFile();
            return (strings.length > 0) ? new ArrayList<>(Arrays.asList(strings)) : null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearFile() {
        try {

            PrintWriter pw = new PrintWriter(openFileOutput("saveFile", MODE_PRIVATE));
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}