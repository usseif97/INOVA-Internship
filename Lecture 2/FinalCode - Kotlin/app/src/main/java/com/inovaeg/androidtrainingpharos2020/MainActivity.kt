package com.inovaeg.androidtrainingpharos2020

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private val locationPermissionCode = 1
    private val cameraPermissionCode = 2
    private val capturePhotoCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        bt_take_permission.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                        cameraPermissionCode)
            } else {
                showToast("you have permission to access the camera")
            }
        }
        bt_start_camera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                showToast("please request permission first")
            } else {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(takePictureIntent, capturePhotoCode)
                }
            }
        }
        bt_check_location.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        locationPermissionCode)
            } else {
                addLocationListener()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun addLocationListener() {
        showToast("adding location Listener")
        (getSystemService(LOCATION_SERVICE) as LocationManager)
                .apply {
                    requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10f, object : LocationListener {
                        override fun onLocationChanged(location: Location) {
                            val gmmIntentUri = Uri.parse("geo:" + location.latitude + "," + location.longitude)
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            mapIntent.setPackage("com.google.android.apps.maps")
                            if (mapIntent.resolveActivity(packageManager) != null) {
                                startActivity(mapIntent)
                            }
                            this@apply.removeUpdates(this)
                        }
                    })
                }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (checkGrantedPermission(grantResults)) {
                showToast("you have taken Camera permission")
            } else showToast("camera permission was denied")
        } else if (requestCode == locationPermissionCode) {
            if (checkGrantedPermission(grantResults)) {
                showToast("you have taken location permission")
                addLocationListener()
            } else showToast("location permission was denied")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == capturePhotoCode && resultCode == RESULT_OK && data != null && data.extras != null) {
            val extras = data.extras
            val imageBitmap = extras!!["data"] as Bitmap?
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun checkGrantedPermission(grantedPermission: IntArray): Boolean {
        return grantedPermission.isNotEmpty() &&
                grantedPermission[0] == PackageManager.PERMISSION_GRANTED
    }
}