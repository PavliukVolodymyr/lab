package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ProductAdapter
import com.example.myapplication.model.ProductModel
import java.util.ArrayList
import android.app.AlertDialog
import android.widget.Toast

lateinit var adapter:ProductAdapter
class MenuActivity :AppCompatActivity() {


    lateinit var recycleView:RecyclerView
    lateinit var rvProduct:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val countDownText: TextView = findViewById(R.id.countDownText)
        val editText: EditText = findViewById(R.id.editText1)
        val buton: Button = findViewById(R.id.bNext)
        val bLogOut= findViewById<Button>(R.id.logOut)
        val bStudentList= findViewById<Button>(R.id.bStudentList)
        val bPairList= findViewById<Button>(R.id.bPairList)
        val bAddProduct= findViewById<Button>(R.id.bAddProduct)
        rvProduct= findViewById<RecyclerView>(R.id.rvProduct)
        val intentStudentList= Intent(this,TeacherList::class.java)
        val intentLessonList= Intent(this,LessonList::class.java)
        val intent = Intent(this,CalculatorActivity::class.java)
        val intentAuth = Intent(this,AuthActivity::class.java)
        val countDownTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                countDownText.text = secondsRemaining.toString()
            }
            override fun onFinish() {
                countDownText.text = getString(R.string.hello)
            }

        }
        initial()
        countDownTimer.start()
        buton.setOnClickListener {
            val currentText = editText.text.toString()
            if(currentText==Const.CALC)
                startActivity(intent)
        }
        bLogOut.setOnClickListener {
            intentAuth.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentAuth)
            finish()
        }
        bStudentList.setOnClickListener{
            startActivity(intentStudentList)
        }
        bPairList.setOnClickListener{
            startActivity(intentLessonList)
        }
        bAddProduct.setOnClickListener {
            showAddProductDialog()
        }

    }

    private fun showAddProductDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.addPr))

        // Підключення макету вспливаючого вікна
        val view = layoutInflater.inflate(R.layout.dialog_add_product, null)
        builder.setView(view)

        val etProductName = view.findViewById<EditText>(R.id.etProductName)
        val etProductPrice = view.findViewById<EditText>(R.id.etProductPrice)

        builder.setPositiveButton(getString(R.string.add)) { dialog, _ ->
            val productName = etProductName.text.toString()
            val productPrice = etProductPrice.text.toString().toDoubleOrNull()
            if (productName.isNotEmpty() && productPrice != null) {
                val newProduct = ProductModel(productName, productPrice)
                adapter.addProduct(newProduct)
            }
            else{
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
    private fun initial() {
        recycleView=rvProduct
        adapter= ProductAdapter()
        recycleView.adapter=adapter
        adapter.setList(Pruducts())
    }

    fun Pruducts(): ArrayList<ProductModel> {
        val studentList= ArrayList<ProductModel>()
        val product= ProductModel("Product1",2.4)
        studentList.add(product)
        studentList.add(product)
        studentList.add(product)
        studentList.add(product)
        studentList.add(product)
        studentList.add(product)
        return studentList
    }
}