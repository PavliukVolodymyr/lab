package com.example.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.TeacherAdapter
import com.example.myapplication.model.TeacherModel

import java.util.ArrayList

class TeacherList : AppCompatActivity() {

    lateinit var adapter:TeacherAdapter
    lateinit var recycleView:RecyclerView
    lateinit var rvTeacher:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_list)
        val bAddTeacher=findViewById<Button>(R.id.bAddTeacher)
        rvTeacher= findViewById<RecyclerView>(R.id.rvTeacher)
        initial()
        bAddTeacher.setOnClickListener {
            showAddTeacherDialog()
        }
    }

    private fun showAddTeacherDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.addTe))

        val view = layoutInflater.inflate(R.layout.dialog_add_teacher, null)
        builder.setView(view)

        val etProductName = view.findViewById<EditText>(R.id.etProductName)
        val etProductPrice = view.findViewById<EditText>(R.id.etProductPrice)

        builder.setPositiveButton(getString(R.string.add)) { dialog, _ ->
            val teacherName = etProductName.text.toString()
            val teacherLast = etProductPrice.text.toString()
            if (teacherName.isNotEmpty() && teacherLast.isNotEmpty()) {
                val newTeacher = TeacherModel(teacherName, teacherLast)
                adapter.addTeacher(newTeacher)
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
        recycleView=rvTeacher
        adapter= TeacherAdapter()
        recycleView.adapter=adapter
        adapter.setList(Teachers())
    }

    fun Teachers():ArrayList<TeacherModel>{
        val teacherList=ArrayList<TeacherModel>()
        val teacher=TeacherModel("FirstName","LastName")
        teacherList.add(teacher)
        teacherList.add(teacher)
        teacherList.add(teacher)
        teacherList.add(teacher)
        teacherList.add(teacher)
        teacherList.add(teacher)
        return teacherList
    }
}