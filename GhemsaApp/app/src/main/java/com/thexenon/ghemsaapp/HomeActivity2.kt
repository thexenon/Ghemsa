package com.thexenon.ghemsaapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.thexenon.ghemsaapp.activities.*
import com.thexenon.ghemsaapp.fragments.CourseFragment
import com.thexenon.ghemsaapp.fragments.NewsFragment
import com.thexenon.ghemsaapp.fragments.PernalityFragment
import com.thexenon.ghemsaapp.fragments.ProgramFragment
import com.thexenon.ghemsaapp.fragments.TimeFragment

class HomeActivity2 : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var headerView : View
    private lateinit var welcomeTextView: TextView
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference


        drawerLayout = findViewById(R.id.main_drawer_layout)
        navView = findViewById(R.id.main_navigation_view)
        val header = navView.getHeaderView(0)
        welcomeTextView = header.findViewById<TextView>(R.id.headerWelcomeTextView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        changeFragment(NewsFragment(), "Home")

        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when(it.itemId){
                R.id.idslides -> startActivity(Intent(applicationContext, SlidesActivity::class.java))
                R.id.idpasco -> startActivity(Intent(applicationContext, PastQuesActivity::class.java))
                R.id.idvideo -> startActivity(Intent(applicationContext, VideoActivity::class.java))
                R.id.idlab -> startActivity(Intent(applicationContext, LabWorksActivity::class.java))
                R.id.idabout -> startActivity(Intent(applicationContext, AboutActivity::class.java))
                R.id.idnews -> changeFragment(NewsFragment(), "News")
                R.id.idperson -> changeFragment(PernalityFragment(), "Personality of the Week")
                R.id.idtime -> changeFragment(TimeFragment(), "Time Tables")
                R.id.idprogram -> changeFragment(CourseFragment(), "Programs")
                R.id.idevents -> changeFragment(ProgramFragment(), "Events")
            }
            true
        }

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser!!.uid
        database.child("Users").child(user).child("fullname").get().addOnSuccessListener {
            val name = "${it.value}"
            Toast.makeText(this, "Welcome $name, Hope you enjoy your stay", Toast.LENGTH_LONG).show()
            welcomeTextView.text = "Hello $name, welcome to GHEMSA Handbook"
            return@addOnSuccessListener
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun setTitleBAr(bartitle:String){
        supportActionBar?.title = bartitle
    }


    fun changeFragment(chagFrag: Fragment, bartitle: String){
        supportActionBar?.title = bartitle
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.main_nav_host, chagFrag).commit()
    }
}