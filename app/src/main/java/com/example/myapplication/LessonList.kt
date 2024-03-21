package com.example.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.LessonAdapter
import com.example.myapplication.model.LessonModel

import java.util.ArrayList

class LessonList : AppCompatActivity() {

    lateinit var adapter:LessonAdapter
    lateinit var recycleView:RecyclerView
    lateinit var rvLesson:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
        val bAddLesson=findViewById<Button>(R.id.bAddTeacher)
        rvLesson= findViewById<RecyclerView>(R.id.rvLesson)
        initial()
        bAddLesson.setOnClickListener {
            showAddLessonDialog()
        }
    }

    private fun showAddLessonDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Додати пару")

        val view = layoutInflater.inflate(R.layout.dialog_add_lesson, null)
        builder.setView(view)

        val etProductName = view.findViewById<EditText>(R.id.etProductName)
        val etProductPrice = view.findViewById<EditText>(R.id.etProductPrice)

        builder.setPositiveButton("Додати") { dialog, _ ->
            val teacherName = etProductName.text.toString()
            val teacherLast = etProductPrice.text.toString()
            if (teacherName.isNotEmpty() && teacherLast.isNotEmpty()) {
                val newLesson = LessonModel(teacherName, teacherLast)
                adapter.addLesson(newLesson)
            }
            else{
                Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Відмінити") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }



    private fun initial() {
        recycleView=rvLesson
        adapter= LessonAdapter()
        recycleView.adapter=adapter
        adapter.setList(Lessons())
    }

    fun Lessons():ArrayList<LessonModel>{
        val lessonList=ArrayList<LessonModel>()
        val lesson=LessonModel("Name","104")
        lessonList.add(lesson)
        lessonList.add(lesson)
        lessonList.add(lesson)
        lessonList.add(lesson)
        lessonList.add(lesson)
        lessonList.add(lesson)
        return lessonList
    }
}