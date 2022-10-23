package com.thexenon.ghemsaapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var signinviewbtn : Button
    lateinit var signupviewbtn : Button
    lateinit var signupbtn : Button
    lateinit var signinbtn : Button
    lateinit var fpassbtn : Button
    lateinit var loginview : LinearLayout
    lateinit var signinview : LinearLayout
    lateinit var signupview : LinearLayout
    lateinit var fpassview : LinearLayout
    lateinit var signupstwView : TextView
    lateinit var signinstwView : TextView
    lateinit var fpassstwView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        signinviewbtn = findViewById<Button>(R.id.signinviewbtn)
        signupviewbtn = findViewById<Button>(R.id.signupviewbtn)
        signupbtn = findViewById<Button>(R.id.signupbtn)
        signinbtn = findViewById<Button>(R.id.signinbtn)
        fpassbtn = findViewById<Button>(R.id.fpassbtn)
        loginview = findViewById<LinearLayout>(R.id.mainloginview)
        signinview = findViewById<LinearLayout>(R.id.mainsigninview)
        signupview = findViewById<LinearLayout>(R.id.mainsignupview)
        fpassview = findViewById<LinearLayout>(R.id.mainfpassview)
        signupstwView = findViewById<TextView>(R.id.signupstwbtn)
        signinstwView = findViewById<TextView>(R.id.signinstwbtn)
        fpassstwView = findViewById<TextView>(R.id.fpassstwbtn)

        signinviewbtn.setOnClickListener {
            showsigninView()
            //startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }
        signupviewbtn.setOnClickListener {
            showsignupView()
        }
        signinstwView.setOnClickListener {
            showsigninView()
        }
        signupstwView.setOnClickListener {
            showsignupView()
        }
        fpassstwView.setOnClickListener {
            showfpassView()
        }
        signupbtn.setOnClickListener {
            dosignup()
        }
        signinbtn.setOnClickListener {
            dosignin()
        }
        fpassbtn.setOnClickListener {
            dofpass()
        }

    }

    override fun onStart() {
        super.onStart()
        updateUI()
    }
    override fun onBackPressed() {
        if (loginview.visibility == View.GONE){
            showloginView()
        } else {
            super.onBackPressed()
        }
    }

    fun showloginView(){
        loginview.visibility = View.VISIBLE
        signinview.visibility = View.GONE
        signupview.visibility = View.GONE
        fpassview.visibility = View.GONE
    }
    fun showsigninView(){
        loginview.visibility = View.GONE
        signinview.visibility = View.VISIBLE
        signupview.visibility = View.GONE
        fpassview.visibility = View.GONE
    }
    fun showsignupView(){
        loginview.visibility = View.GONE
        signinview.visibility = View.GONE
        signupview.visibility = View.VISIBLE
        fpassview.visibility = View.GONE
    }
    fun showfpassView(){
        loginview.visibility = View.GONE
        signinview.visibility = View.GONE
        signupview.visibility = View.GONE
        fpassview.visibility = View.VISIBLE
    }
    fun updateUI(){
        val user = auth.currentUser
        if (user != null) {
            val intent =
            startActivity(Intent(this@LoginActivity, HomeActivity2::class.java))
            finish()
        } else {
            //startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
        }
    }

    fun dosignup(){
        val email = findViewById<TextInputEditText>(R.id.signupemail).text.toString()
        val fullname = findViewById<TextInputEditText>(R.id.signupfullname).text.toString()
        val password = findViewById<TextInputEditText>(R.id.signuppass).text.toString()

        if (!email.isEmpty() && !fullname.isEmpty() && !password.isEmpty()) {
            //Toast.makeText(this@LoginActivity, "Login Success!!!", Toast.LENGTH_LONG).show()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser!!.uid
                        database.child("Users").child(user).child("fullname").setValue(fullname)
                        database.child("Users").child(user).child("emailup").setValue(email)
                        database.child("Users").child(user).child("passwordup").setValue(password)
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI()
                    }
                }
        } else {
            Toast.makeText(this@LoginActivity, "Please fill all fields!!!", Toast.LENGTH_LONG).show()
        }
    }
    fun dosignin(){
        val email = findViewById<TextInputEditText>(R.id.signinemail).text.toString()
        val password = findViewById<TextInputEditText>(R.id.signinpass).text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            //Toast.makeText(this@LoginActivity, "Login Success!!!", Toast.LENGTH_LONG).show()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "Sign In User With Email:failure")
                        val user = auth.currentUser!!.uid
                        database.child("Users").child(user).child("emailin").setValue(email)
                        database.child("Users").child(user).child("passwordin").setValue(password)
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "Sign In User With Email:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI()
                    }
                }
        } else {
            Toast.makeText(this@LoginActivity, "Please fill all fields!!!", Toast.LENGTH_LONG).show()
        }
    }
    fun dofpass(){
        val email = findViewById<TextInputEditText>(R.id.fpassemail).text.toString()

        if (!email.isEmpty()) {
            //Toast.makeText(this@LoginActivity, "Login Success!!!", Toast.LENGTH_LONG).show()
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "Sending email success. Check your mail.")
                        Toast.makeText(baseContext, "Sending email success. Check your mail.",
                            Toast.LENGTH_SHORT).show()
                        showsigninView()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Sending email failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        } else {
            Toast.makeText(this@LoginActivity, "Please fill your email!!!", Toast.LENGTH_LONG).show()
        }
    }
}