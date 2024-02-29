package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class RegistrationActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageView: ImageView
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val eName= findViewById<EditText>(R.id.eName)
        val eLogin= findViewById<EditText>(R.id.eLogin)
        val ePass= findViewById<EditText>(R.id.ePassword)
        val eDate= findViewById<EditText>(R.id.eDate)
        val ePhone= findViewById<EditText>(R.id.ePhone)
        val bReg= findViewById<Button>(R.id.bReg)
        val eConfirm= findViewById<EditText>(R.id.eConfirm)
        val intentAuth= Intent(this,AuthActivity::class.java)
        imageView = findViewById(R.id.img)
        imageView.setOnClickListener {
            // Запускаємо Intent для вибору зображення з галереї
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
        }
        bReg.setOnClickListener {
            val name= eName.text.toString()
            val login= eLogin.text.toString()
            val phone= ePhone.text.toString()
            val date= eDate.text.toString()
            val pass= ePass.text.toString()
            val conf= eConfirm.text.toString()
            if(pass==conf && pass!=""){
                sharedPreferences.edit().apply {
                    putString("name", name)
                    putString("login", login)
                    putString("phone", phone)
                    putString("date", date)
                    putString("pass", pass)
                    apply()
                }
                startActivity(intentAuth)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
            imageView.setImageBitmap(bitmap)
            sharedPreferences.edit().apply {
                putString("url", selectedImageUri.toString())
                apply()
            }
        }
    }
}