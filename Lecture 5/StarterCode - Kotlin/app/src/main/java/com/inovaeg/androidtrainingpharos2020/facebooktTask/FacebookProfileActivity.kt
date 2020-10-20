package com.inovaeg.androidtrainingpharos2020.facebooktTask

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.inovaeg.androidtrainingpharos2020.R
import com.inovaeg.androidtrainingpharos2020.facebooktTask.models.Post
import kotlinx.android.synthetic.main.task_activity_facebook_profile.*


class FacebookProfileActivity : AppCompatActivity() {

    private val TAG = "RecyclerView"
    private val cameraAndgalleryPermissionCode = 2 // and StoragePermissionCode
    private val capturePhotoCode = 3
    private var imageInserted = false  // false: profile // true: cover

    private var posts  = ArrayList<Post>()
    private var newPostImage : String? = null
    private var newPostCaption : String? = null

    // Gson Converter
    val gson = Gson()
    // SharedPreferences
    private lateinit var sharedPref : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_activity_facebook_profile)
        showToast("Here We Are")

        sharedPref = getSharedPreferences("FacebookProfileActivity", Context.MODE_PRIVATE)

        restoreFromSharedPref()


        Log.d(TAG, "onCreate: ")
        Log.d(TAG, "posts size: " + posts.size)

        newPostImage  = intent.getStringExtra("Image")
        newPostCaption = intent.getStringExtra("Post")

        // Adapter & Recycler View
        val postsAdapter : PostsAdapter = PostsAdapter(posts)
        val linearLayoutManager = LinearLayoutManager(this)
        val recyclerView : RecyclerView = findViewById(R.id.fb_posts)
        if(newPostCaption != null){
            checkNewPosts(postsAdapter)
        }

        recyclerView.apply {
            adapter = postsAdapter
            layoutManager = linearLayoutManager
        }


        // Get The Screen Dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        profile_image_view.getLayoutParams().width = (width * 0.4).toInt()
        profile_image_view.getLayoutParams().height = (width * 0.4).toInt()

        profile_post_image_view.getLayoutParams().width = (width * 0.2).toInt()
        profile_post_image_view.getLayoutParams().height = (width * 0.2).toInt()


        username_text_view.setText(R.string.youssef_ahmed)
        study_text_view.setText(R.string.study)
        address_text_view.setText(R.string.lives_in_alexandria_egypt)

        friend1_image_view.setImageResource(R.drawable.scarlett_johansen)
        friend1_text_view.setText("Scarlett Johansen")
        friend2_image_view.setImageResource(R.drawable.natile_portman)
        friend2_text_view.setText("Natille Portman")
        friend3_image_view.setImageResource(R.drawable.leanordo_dicaprio)
        friend3_text_view.setText("Leanordo Dicaprio")
        friend4_image_view.setImageResource(R.drawable.rasmond_pike)
        friend4_text_view.setText("Rasmond Pike")
        friend5_image_view.setImageResource(R.drawable.tom_hardy)
        friend5_text_view.setText("Tom Hardy")
        friend6_image_view.setImageResource(R.drawable.emily_blunt)
        friend6_text_view.setText("Emily Blunt")

        profile_btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //TODO take camera & galler permission to start it
                // Ask for the Permission
                ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                        cameraAndgalleryPermissionCode)
            } else {
                imageInserted = false
                showToast("you have the permission to access the camera")
                showDialog("Profile Image")
            }
        }

        cover_btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //TODO take camera & galler permission to start it
                // Ask for the Permission
                ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                        cameraAndgalleryPermissionCode)
            } else {
                imageInserted = true
                showToast("you have the permission to access the camera")
                showDialog("Cover Image")
            }
        }

        post_profile_edit_text.setOnClickListener {
            if(posts.size != 0){
                val postsJson = gson.toJson(posts)
                savePostsInSharedPref(postsJson)
            }
            startActivity(Intent(this@FacebookProfileActivity, FacebookHomeActivity::class.java))
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        // save the activity state & view but don't save the variables
        super.onSaveInstanceState(outState)
        if(posts.size != 0){
            val postsJson = gson.toJson(posts)
            outState.putString("list", postsJson)
            Log.d(TAG, "onSaveInstanceState: ")
            Log.d(TAG, "postsJson: " + postsJson)
            Log.d(TAG, "posts size: " + posts.size)
        }
    }*/


    /*override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // get the saved activity state
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState: ")
        if(savedInstanceState != null){
            val postsString = savedInstanceState.getString("list", "")
            val founderArray: Array<Post> = gson.fromJson(postsString, Array<Post>::class.java)
            posts = founderArray as ArrayList<Post>
            Log.d(TAG, "posts size: " + posts.size)
        }
    }*/

    // Result of the permissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraAndgalleryPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("You have Camera & Gallery Permission")
            } else {
                showToast("Camera Permission Denied")
            }
        }
    }

    // Result of the implicit intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == capturePhotoCode && resultCode == RESULT_OK && data != null && data.extras != null) {
            val extra = data.extras
            val imageBitmap = extra!!["data"] as Bitmap?
            if(imageInserted)
                cover_image_view.setImageBitmap(imageBitmap)
            else
                profile_image_view.setImageBitmap(imageBitmap)
        }  else if (requestCode == cameraAndgalleryPermissionCode && resultCode == RESULT_OK && data != null) {
            if(imageInserted)
                cover_image_view.setImageURI(data?.data) // ? because it can be null but it's handled in the if condition btw
            else
                profile_image_view.setImageURI(data?.data) // ? because it can be null but it's handled in the if condition btw
        }
        else {
            showToast("Action Cancelled !!")
        }
    }


    private fun showDialog(title: String) {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Do you want to Add Image ?")
                // if the dialog is cancelable
                .setPositiveButton("Camera",
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                            showToast("Camera")
                            cameraListener()
                        })
                // positive button text and action
                .setNegativeButton("Gallery",
                        DialogInterface.OnClickListener { dialog, id ->
                            showToast("Gallery")
                            galleryListener()
                        })

        val alert = dialogBuilder.create()

        alert.setTitle(title)
        alert.show() //<-- See This!
    }

    private fun galleryListener(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), cameraAndgalleryPermissionCode)
        // then handle OnActivityResult function
    }

    private fun cameraListener() {
        //TODO start camera intent to take picture
        // Implicit Intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, capturePhotoCode)
            // then handle OnActivityResult function
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun checkNewPosts(adapter: PostsAdapter){
        Log.d(TAG, "checkNewPosts: ")
        val post : Post
        if(newPostImage == "0"){
            post = Post(newPostCaption, null)
        } else {
            post = Post(newPostCaption, Uri.parse(newPostImage))
        }
        posts.add(post)
        adapter.notifyDataSetChanged()
    }

    private fun savePostsInSharedPref(list: String?) {
        Log.d(TAG, "savePostsInSharedPref: ")
        if(list != null) {
            Log.d(TAG, "savePostsInSharedPref: list != null")
            sharedPref
                    .edit()
                    .putString("postsList", list)
                    .apply()
        }
        // apply used to generate thread and use it
    }

    private fun restoreFromSharedPref() {
        val stringList = sharedPref.getString("postsList", null)
        Log.d(TAG, "restoreFromSharedPref: ")
        Log.d(TAG, "stringList: " + stringList)
        if(stringList != null){
            Log.d(TAG, "restoreFromSharedPref:  != null")
            val founderArray: Array<Post> = gson.fromJson(stringList, Array<Post>::class.java)
            posts = founderArray.toCollection(ArrayList())
        }
    }



}