package com.inovaeg.androidtrainingpharos2020.examples

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_user_inputs_and_interactions.*

class UserInputsAndInteractionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_inputs_and_interactions)
        btn_user_inputs_and_interactions.setOnClickListener {
            Toast.makeText(this@UserInputsAndInteractionsActivity,
                    "Welcome " + et_user_inputs_and_interactions_name.text, Toast.LENGTH_SHORT).show()
        }
    }
}