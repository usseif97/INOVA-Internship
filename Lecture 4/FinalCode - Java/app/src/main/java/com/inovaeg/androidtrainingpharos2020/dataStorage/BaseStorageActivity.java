package com.inovaeg.androidtrainingpharos2020.dataStorage;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.inovaeg.androidtrainingpharos2020.R;

import java.util.ArrayList;
import java.util.List;

public class BaseStorageActivity extends AppCompatActivity {
    public static final String TYPE_SHARED_PREF = "TYPE_SHARED_PREF";
    public static final String TYPE_DATABASE = "TYPE_DATABASE";
    public static final String TYPE_FILE_SAVE = "TYPE_FILE_SAVE";
    public static final String TYPE_KEY = "TYPE_KEY";
    private List<String> names = new ArrayList<>();
    private EditText editText;
    private TextView tvNames;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null && getIntent().getExtras().getString(TYPE_KEY) != null) {
            type = getIntent().getExtras().getString(TYPE_KEY);
        }
        setContentView(R.layout.activity_shared_pref);
        editText = findViewById(R.id.ed_name);
        tvNames = findViewById(R.id.tv_names);
        findViewById(R.id.bt_add).setOnClickListener(v -> {
            if (editText.getText() != null && editText.getText().length() > 0) {
                names.add(editText.getText().toString());
            }
            tvNames.setText(joinInSingleString(names));
            editText.setText("");
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        switch (type) {
            case TYPE_SHARED_PREF: {
                saveNamesInSharedPref(names);
                break;
            }
            case TYPE_DATABASE: {
                saveNamesInDatabase(names);
                break;
            }
            case TYPE_FILE_SAVE: {
                saveNamesInFile(names);
                break;
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        switch (type) {
            case TYPE_SHARED_PREF: {
                setNames(restoreFromSharedPref());
                break;
            }
            case TYPE_DATABASE: {
                setNames(restoreFromDatabase());
                break;
            }
            case TYPE_FILE_SAVE: {
                setNames(restoreFromFile());
                break;
            }
        }
        findViewById(R.id.bt_add).callOnClick();
    }

    private void setNames(List<String> names) {
        if (names != null) {
            this.names = names;
        }
    }

    //    sharedPref methods
    protected void saveNamesInSharedPref(List<String> names) {

    }

    protected List<String> restoreFromSharedPref() {

        return null;
    }

    ///////////////
    //////////file
    protected void saveNamesInFile(List<String> names) {

    }

    protected List<String> restoreFromFile() {

        return null;
    }
    //////////////

    //DataBase
    protected void saveNamesInDatabase(List<String> names) {
    }

    protected List<String> restoreFromDatabase() {
        return null;
    }
    ////////////

    final protected String joinInSingleString(List<String> names) {
        StringBuilder joinedNames = new StringBuilder();
        for (String name : names
        ) {
            joinedNames.append(name);
            joinedNames.append(" ");
        }
        return joinedNames.toString();
    }
}
