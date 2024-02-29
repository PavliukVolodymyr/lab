package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val countDownText: TextView = findViewById(R.id.countDownText)
        val editText: EditText = findViewById(R.id.editText1)
        val buton: Button = findViewById(R.id.bNext)
        val bLogOut= findViewById<Button>(R.id.logOut)
        val intent = Intent(this,CalculatorActivity::class.java)
        val intentAuth = Intent(this,AuthActivity::class.java)
        val countDownTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                countDownText.text = secondsRemaining.toString()
            }
            override fun onFinish() {
                countDownText.text = "Вітаю!"
            }
        }
        countDownTimer.start()
        buton.setOnClickListener {
            val currentText = editText.text.toString()
            if(currentText=="calc")
                startActivity(intent)
        }
        bLogOut.setOnClickListener {
            intentAuth.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentAuth)
            finish()

        }



    }
}