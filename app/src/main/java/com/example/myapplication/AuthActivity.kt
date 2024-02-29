package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AuthActivity : AppCompatActivity() {
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val eLogin=findViewById<EditText>(R.id.eLogin)
        val ePass=findViewById<EditText>(R.id.ePassword)
        val intentReg = Intent(this,RegistrationActivity::class.java)
        val intentMenu = Intent(this,MenuActivity::class.java)
        val bReg= findViewById<Button>(R.id.bReg)
        val bLog= findViewById<Button>(R.id.bAuth)
        bReg.setOnClickListener {
            startActivity(intentReg)
        }
        bLog.setOnClickListener {
            val login = eLogin.text.toString()
            val pass = ePass.text.toString()

            val savedLogin = sharedPreferences.getString("login", "")
            val savedPass = sharedPreferences.getString("pass", "")
            if (login==savedLogin && pass==savedPass && savedPass!="" && savedLogin!=""){
                startActivity(intentMenu)
            }
        }

    }
}