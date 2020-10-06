package com.inovaeg.androidtrainingpharos2020

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
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
    private val cameraPermissionCode = 2 // and StoragePermissionCode
    private val capturePhotoCode = 2
    private val readStoragePermissionCode = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onCreate: ")

        bt_take_permission.setOnClickListener {
            //TODO check for Camera permission
            bt_take_permission.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //TODO take camera permission to start it
                    // Ask for the Permission
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            cameraPermissionCode)
                } else {
                    showToast("you have the permission to access the camera")
                }
            }
        }

        bt_start_camera.setOnClickListener {
            //TODO check for camera permission
            bt_start_camera.setOnClickListener {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //TODO take camera permission to start it
                    // Ask for the Permission
                    showToast("Please, Ask for the Permission first")
                } else {
                    //TODO start camera intent to take picture
                    // Implicit Intent
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (takePictureIntent.resolveActivity(packageManager) != null) {
                        startActivityForResult(takePictureIntent, capturePhotoCode)
                        // then handle OnActivityResult function
                    }
                }
            }
        }

        bt_check_location.setOnClickListener {
            //TODO check for location permission
            bt_check_location.setOnClickListener {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    //TODO take camera permission to start it
                    // Ask for the Permission
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            locationPermissionCode)
                } else {
                    showToast("you have the permission to access the location")
                    //TODO start location listener
                    locationListener()

                }
            }
        }

        bt_start_gallery.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                // Ask for the Permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        readStoragePermissionCode)
            } else {
                showToast("you have the permission to access the gallery")
                galleryListener()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        //TODO add log.d to see the lifecycle in logs
        Log.d(TAG, "onRestart: ")
    }

    // Result of the permissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("You have the Camera Permission")
            } else {
                showToast("Camera Permission Denied")
            }
        } else if(requestCode == locationPermissionCode){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showToast("You have the Location Permission")
                locationListener()
            }else {
                showToast("Location Permission Denied")
            }
        } else if(requestCode == readStoragePermissionCode){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showToast("You have Read Storage Permission")

            }else {
                showToast("Read Storage Permission Denied")
            }
        }
    }

    // Result of the implicit intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == capturePhotoCode && resultCode == RESULT_OK && data != null && data.extras != null) {
            val extra = data.extras
            val imageBitmap = extra!!["data"] as Bitmap?
            imageView.setImageBitmap(imageBitmap)
        }  else if (requestCode == readStoragePermissionCode && resultCode == RESULT_OK && data != null) {
            imageView.setImageURI(data?.data) // ? because it can be null but it's handled in the if condition btw
        }
        else {
            showToast("Action Cancelled !!")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    // Location Listener
    @SuppressLint("MissingPermission")
    private fun locationListener(){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val gmmIntentUri = Uri.parse("geo:" + location.latitude + "," + location.longitude)
                val map = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                map.setPackage("com.google.android.apps.maps")
                if (map.resolveActivity(packageManager) != null) startActivity(map)
                locationManager.removeUpdates(this)
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10f, locationListener);

        /*locationManager
                .requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10f, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        //TODO when you get location cancel the listener and start map intent
                        val gmmIntentUri: Uri = Uri.parse("geo:" + location.getLatitude().toString() + "," + location.getLongitude())
                        val mapIntent: Intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        if (mapIntent.resolveActivity(packageManager) != null) {
                            startActivity(mapIntent)
                        }
                        locationManager.removeUpdates(this)
                    }
                })*/
    }


    private fun galleryListener(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), readStoragePermissionCode)
        // then handle OnActivityResult function
    }


}