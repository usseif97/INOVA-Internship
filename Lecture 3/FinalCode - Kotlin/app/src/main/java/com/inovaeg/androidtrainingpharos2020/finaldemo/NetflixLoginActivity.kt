package com.inovaeg.androidtrainingpharos2020.finaldemo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.activity_netflix_login.*

class NetflixLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix_login)
        et_netflix_login_email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                btn_netflix_login.isEnabled = s.toString().isNotEmpty() && et_netflix_login_password.text.toString().isNotEmpty()
            }
        })
        et_netflix_login_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                btn_netflix_login.isEnabled = s.toString().isNotEmpty() && et_netflix_login_email.text.toString().isNotEmpty()
            }
        })
        btn_netflix_login.setOnClickListener {
            startActivity(Intent(this@NetflixLoginActivity, NetflixMovieOverviewActivity::class.java))
            //used to remove this activity from the stack, so when the user clicks on back button in NetflixMovieOverviewActivity the app closes
            finish()
        }
    }
}