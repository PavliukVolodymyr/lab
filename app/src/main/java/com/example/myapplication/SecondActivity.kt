package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val countDownText: TextView = findViewById(R.id.countDownText)
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
    }
}