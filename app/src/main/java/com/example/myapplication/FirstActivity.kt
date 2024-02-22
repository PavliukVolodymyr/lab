package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class FirstActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        val myImageView: ImageView = findViewById(R.id.apple)
        val textV: TextView = findViewById((R.id.text))
        val intent = Intent(this,SecondActivity::class.java)
        val message = "Спочатку нажміть на яблуко"
        val duration = Toast.LENGTH_SHORT // або Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, message, duration)
        myImageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                textV.setText("Apple")
            }
        })
        textV.setOnClickListener {
            if (textV.text == "Apple")
                startActivity(intent)
            else
                toast.show()
        }
    }

}