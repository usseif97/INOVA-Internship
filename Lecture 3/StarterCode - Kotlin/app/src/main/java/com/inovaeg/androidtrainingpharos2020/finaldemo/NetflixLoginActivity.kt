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

        email_edit_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                login_btn.isEnabled = p0.toString().isNotEmpty() && password_edit_text.text.toString().isNotEmpty()
            }
        })

        password_edit_text.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                login_btn.isEnabled = p0.toString().isNotEmpty() && login_btn.text.toString().isNotEmpty()
            }
        })

        login_btn.setOnClickListener {
            startActivity(Intent(this@NetflixLoginActivity, NetflixMovieOverviewActivity::class.java))
        }


    }
}