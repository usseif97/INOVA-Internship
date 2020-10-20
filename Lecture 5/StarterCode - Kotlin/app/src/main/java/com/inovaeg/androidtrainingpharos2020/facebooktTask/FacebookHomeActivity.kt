package com.inovaeg.androidtrainingpharos2020.facebooktTask

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.facebooktTask.models.Post
import kotlinx.android.synthetic.main.task_activity_facebook_home.*
import java.net.URI


class FacebookHomeActivity  : AppCompatActivity() {

    private val galleryPermissionCode = 2 // and StoragePermissionCode
    private var postImage : Uri? = null
    private var postText = ""

    // SharedPreferences
    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_activity_facebook_home)

        sharedPref = getSharedPreferences("FacebookHomeActivity", Context.MODE_PRIVATE)

        val stringPost = sharedPref.getString("postString", null)
        if(stringPost != null) {
            post_edit_text.setText(stringPost)
            post_btn.isEnabled = true
        }

        username_text_view.setText(R.string.youssef_ahmed)

        post_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                post_btn.isEnabled = p0.toString().isNotEmpty()
                if (p0 != null)
                    postText = p0.trim().toString()
                else
                    postText = ""

            }
        })

        add_image_to_post_image_view.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //TODO take gallery permission to start it
                // Ask for the Permission
                ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                        galleryPermissionCode)
            } else {
                showToast("you have the permission to access the Gallery")
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), galleryPermissionCode)
                // then handle OnActivityResult function
            }
        }

        post_btn.setOnClickListener {
            if(post_btn.isEnabled){

                var postImageString : String

                val intent = Intent(baseContext, FacebookProfileActivity::class.java)
                if(postImage == null){
                    postImageString = "0"
                } else {
                    postImageString = postImage.toString()
                }
                intent.putExtra("Image", postImageString)
                intent.putExtra("Post", post_edit_text.text.toString())
                showToast("The Post is added")
                startActivity(intent);

            }

        }

    }

    // Result of the implicit intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == galleryPermissionCode && resultCode == RESULT_OK && data != null) {
            post_image_view.visibility = View.VISIBLE;
            post_image_view.setImageURI(data?.data) // ? because it can be null but it's handled in the if condition btw
            postImage = data?.data
        }
        else {
            showToast("Action Cancelled !!")
        }
    }

    override fun onBackPressed() {
        sharedPref
                .edit()
                .putString("postString", postText)
                .apply()

        super.onBackPressed()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}