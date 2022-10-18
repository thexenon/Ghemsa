package com.thexenon.ghemsaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference


        val programsbtn = findViewById<LinearLayout>(R.id.homeprogramotlinebtn)
        val newsbtn = findViewById<LinearLayout>(R.id.homenewsbtn)
        val tutorialsbtn = findViewById<LinearLayout>(R.id.homevideobtn)
        val slidesbtn = findViewById<LinearLayout>(R.id.homeslidesbtn)
        val labworkbtn = findViewById<LinearLayout>(R.id.homelabworkbtn)
        val timetablebtn = findViewById<LinearLayout>(R.id.hometimetablebtn)
        val pascobtn = findViewById<LinearLayout>(R.id.homepascobtn)

//        newsbtn.setOnClickListener {
//            startActivity(Intent(this@HomeActivity, NewsFeedActivity::class.java))
//        }
//        slidesbtn.setOnClickListener {
//            startActivity(Intent(this@HomeActivity, SlidesActivity::class.java))
//        }

    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser!!.uid
        database.child("Users").child(user).child("fullname").get().addOnSuccessListener {
            val name = "${it.value}"
            Toast.makeText(this, "Welcome $name, Hope you enjoy your stay", Toast.LENGTH_LONG).show()
            return@addOnSuccessListener
        }
    }
}