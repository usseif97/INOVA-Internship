package com.inovaeg.androidtrainingpharos2020.facebookTask

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.inovaeg.androidtrainingpharos2020.R
import kotlinx.android.synthetic.main.task_activity_facebook_home.*
import kotlinx.android.synthetic.main.task_activity_facebook_profile.username_text_view


class FacebookHomeActivity : AppCompatActivity() {

    private val galleryPermissionCode = 2 // and StoragePermissionCode
    private var postImage : Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_activity_facebook_home)

        username_text_view.setText(R.string.youssef_ahmed)

        post_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                post_btn.isEnabled = p0.toString().isNotEmpty()
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


                //val options_layout : LinearLayout = posts_layout
                //val options: Array<String> = getActivity().getResources().getStringArray(R.array.options)
                //val to_add: View = layoutInflater.inflate(R.layout.task_activity_facebook_post1, posts_layout, false)

                //val text = to_add.findViewById<View>(R.id.username_post_text_view) as TextView
                //username_post_text_view.text = post_edit_text.text
                //posts_layout.addView(to_add);

                /*val layoutLinear = findViewById<LinearLayout>(R.id.posts_layout)
                val iv = ImageView(this)
                iv.setImageResource(R.drawable.youssef)
                iv.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutLinear.addView(iv)*/


                /*val lparams = LinearLayoutCompat.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                val layout = R.layout.task_activity_facebook_post1
                posts_layout.addView(layout)

                val tv = TextView(this)
                tv.layoutParams = lparams
                tv.text = "test"
                this.m_vwJokeLayout.addView(tv)*/
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}