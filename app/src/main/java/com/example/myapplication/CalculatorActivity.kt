package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)
        val countDownText: TextView = findViewById(R.id.countDownText)
        val bC = findViewById<Button>(R.id.bC)
        val bB1 = findViewById<Button>(R.id.bB1)
        val bB2 = findViewById<Button>(R.id.bB2)
        val bD = findViewById<Button>(R.id.bD)

        val b7 = findViewById<Button>(R.id.b7)
        val b8 = findViewById<Button>(R.id.b8)
        val b9 = findViewById<Button>(R.id.b9)
        val bMul = findViewById<Button>(R.id.bmul)

        val b4 = findViewById<Button>(R.id.b4)
        val b5 = findViewById<Button>(R.id.b5)
        val b6 = findViewById<Button>(R.id.b6)
        val bMin = findViewById<Button>(R.id.bMin)

        val b1 = findViewById<Button>(R.id.b1)
        val b2 = findViewById<Button>(R.id.b2)
        val b3 = findViewById<Button>(R.id.b3)
        val bP = findViewById<Button>(R.id.bP)

        val b0 = findViewById<Button>(R.id.b0)
        val bPoi = findViewById<Button>(R.id.bPoi)
        val bDel = findViewById<Button>(R.id.bDel)
        val bE = findViewById<Button>(R.id.bE)
        val editText: EditText = findViewById(R.id.editText1)

        var history: Double = 0.0
        var historyTemp: Double = 0.0
        val bHistory = findViewById<Button>(R.id.bHistory)

        b0.setOnClickListener {
            editText.setText("${editText.text.toString()}0")
        }
        b1.setOnClickListener {
            editText.setText("${editText.text.toString()}1")
        }
        b2.setOnClickListener {
            editText.setText("${editText.text.toString()}2")
        }
        b3.setOnClickListener {
            editText.setText("${editText.text.toString()}3")
        }
        b4.setOnClickListener {
            editText.setText("${editText.text.toString()}4")
        }
        b5.setOnClickListener {
            editText.setText("${editText.text.toString()}5")
        }
        b6.setOnClickListener {
            editText.setText("${editText.text.toString()}6")
        }
        b7.setOnClickListener {
            editText.setText("${editText.text.toString()}7")
        }
        b8.setOnClickListener {
            editText.setText("${editText.text.toString()}8")
        }
        b9.setOnClickListener {
            editText.setText("${editText.text.toString()}9")
        }
        bC.setOnClickListener {
            editText.setText("")
            countDownText.setText("")
        }
        bB1.setOnClickListener {
            editText.setText("${editText.text.toString()}(")
        }
        bB2.setOnClickListener {
            editText.setText("${editText.text.toString()})")
        }
        bMul.setOnClickListener {
            editText.setText("${editText.text.toString()}*")
        }
        bD.setOnClickListener {
            editText.setText("${editText.text.toString()}/")
        }
        bP.setOnClickListener {
            editText.setText("${editText.text.toString()}+")
        }
        bMin.setOnClickListener {
            editText.setText("${editText.text.toString()}-")
        }
        bPoi.setOnClickListener {
            editText.setText("${editText.text.toString()}.")
        }
        bDel.setOnClickListener {
            val currentText = editText.text.toString()
            if (currentText.isNotEmpty()) {
                editText.setText(currentText.substring(0, currentText.length - 1))
            }
        }
        bE.setOnClickListener {
            val currentText = editText.text.toString()
            try {
                val expression = ExpressionBuilder(currentText).build()
                val result = expression.evaluate()
                editText.setText(result.toString())
                history=historyTemp
                historyTemp=result
            } catch (e: ArithmeticException) {
                countDownText.text="Помилка: ${e.message}"
            } catch (e: Exception) {
                countDownText.text="Невідома помилка: ${e.message}"
            }
        }
        bHistory.setOnClickListener {
            editText.setText(history.toString())
        }


    }
}