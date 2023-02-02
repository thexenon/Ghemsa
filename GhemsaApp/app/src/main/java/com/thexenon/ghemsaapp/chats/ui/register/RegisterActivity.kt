package com.thexenon.ghemsaapp.chats.ui.register

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.thexenon.ghemsaapp.R
import com.thexenon.ghemsaapp.chats.messages.LatestMessagesActivity
import com.thexenon.ghemsaapp.chats.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var selectedPhotoUri: Uri? = null

    companion object {
        val TAG = RegisterActivity::class.java.simpleName!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)
        supportActionBar?.elevation = 0.0f

        register_button_register.setOnClickListener {
            uploadImageToFirebaseStorage()
        }

//        already_have_account_text_view.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.enter, R.anim.exit)
//        }

        selectphoto_button_register.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data ?: return
            Log.d(TAG, "Photo was selected")
            // Get and resize profile image
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            contentResolver.query(selectedPhotoUri!!, filePathColumn, null, null, null)?.use {
                it.moveToFirst()
                val columnIndex = it.getColumnIndex(filePathColumn[0])
                val picturePath = it.getString(columnIndex)
                // If picture chosen from camera rotate by 270 degrees else
                if (picturePath.contains("DCIM")) {
                    Picasso.get().load(selectedPhotoUri).rotate(270f).into(selectphoto_imageview_register)
                } else {
                    Picasso.get().load(selectedPhotoUri).into(selectphoto_imageview_register)
                }
            }
            selectphoto_button_register.alpha = 0f
        }
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) {
            // save user without photo
            saveUserToFirebaseDatabase(null)
        } else {
            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
            ref.putFile(selectedPhotoUri!!)
                    .addOnSuccessListener {
                        Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")

                        @Suppress("NestedLambdaShadowedImplicitParameter")
                        ref.downloadUrl.addOnSuccessListener {
                            Log.d(TAG, "File Location: $it")
                            saveUserToFirebaseDatabase(it.toString())
                        }
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Failed to upload image to storage: ${it.message}")
                        loading_view.visibility = View.GONE
                    }
        }

    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String?) {
        val uid = FirebaseAuth.getInstance().uid ?: return
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = if (profileImageUrl == null) {
            User(uid, name_edittext_register.text.toString(), null)
        } else {
            User(uid, name_edittext_register.text.toString(), profileImageUrl)
        }

        ref.setValue(user)
                .addOnSuccessListener {
                    Log.d(TAG, "Finally we saved the user to Firebase Database")

                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    overridePendingTransition(R.anim.enter, R.anim.exit)
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to set value to database: ${it.message}")
                    loading_view.visibility = View.GONE
                }
    }
}

