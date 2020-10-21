package com.example.guessinggame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var enteredCode = ""
    private var targetCode : String?= null

    private val codeList = ArrayList<Code>()
    
    private var mFirebaseDatabase: FirebaseDatabase? = null
    private var mCodesDatabaseReference: DatabaseReference? = null
    private var mChildEventListener: ChildEventListener? = null
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    @SuppressLint("BatteryLife")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun askUserToEnablePermissionInMIUI() {
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            val intent = Intent()
            intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            intent.data = Uri.parse("package:$packageName")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init all firebase instances
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mCodesDatabaseReference = mFirebaseDatabase!!.getReference("codes")

        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                onSignedInInitialize()
            } else {
                // User is signed out
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                            listOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build()
                            )
                        )
                        .setIsSmartLockEnabled(false)
                        .build(),
                    RC_SIGN_IN
                )
            }
        }

        code_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ok_btn.isEnabled = p0.toString().length == 4
                if (ok_btn.isEnabled)
                    enteredCode = p0.toString()
            }
        })

        ok_btn.setOnClickListener {
            code1.text = enteredCode.get(0).toString()
            code2.text = enteredCode.get(1).toString()
            code3.text = enteredCode.get(2).toString()
            code4.text = enteredCode.get(3).toString()

            val text1 = check(code1, 0)
            val text2 = check(code2, 1)
            val text3 = check(code3, 2)
            val text4 = check(code4, 3)

            if(text1 && text2 && text3 && text4) {
                Log.d(TAG, "WINNER: ")
                code_edit_text.setText("")
                again_btn.isEnabled = true
                screen_background.setBackgroundResource(R.drawable.background2)
                generate()
            }
        }

        again_btn.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }

        sign_out_btn.setOnClickListener {
            AuthUI.getInstance().signOut(this)
        }

    }

    override fun onStart() {
        super.onStart()
        mFirebaseAuth!!.addAuthStateListener(mAuthStateListener!!)
    }

    private fun check(textView : TextView, index : Int) : Boolean{
        return when {
            textView.text == targetCode!!.get(index).toString() -> {
                textView.setBackgroundResource(R.drawable.rect_green)
                true
            }
            targetCode!!.contains(textView.text) -> {
                textView.setBackgroundResource(R.drawable.rect_yellow)
                false
            }
            else -> {
                textView.setBackgroundResource(R.drawable.rect_red)
                false
            }
        }
    }

    private fun generate(){
        val source = "0123456789"
        val randomCode= (1..4).map { source.random() }.joinToString("")
        Log.d(TAG, "randomCode: " + randomCode)
        val newCode = Code(randomCode)
        mCodesDatabaseReference!!.push().setValue(newCode)
    }

    private fun onSignedInInitialize() {
        attachDatabaseReadListener()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) askUserToEnablePermissionInMIUI()
    }

    private fun attachDatabaseReadListener() {
        mCodesDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    Log.d(TAG, "snapshot.exists: " + snapshot.children)
                    for(ds in snapshot.children){
                        codeList.add(ds.getValue(Code::class.java)!!)
                    }
                    val rnds = (0 until codeList.size).random()
                    targetCode = codeList.get(rnds).text
                    Log.d(TAG, "targetCode: " + targetCode)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    companion object {
        private const val TAG = "GuessingGame"
        const val RC_SIGN_IN = 1
    }

}