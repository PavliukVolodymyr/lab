package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.LessonModel

class LessonAdapter:RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var lessonList= emptyList<LessonModel>()
    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        val bDel: Button =itemView.findViewById(R.id.bDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_layout,parent,false)
        return LessonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessonList[position]
        holder.tvFirstName.text = lesson.firstName
        holder.tvLastName.text = lesson.lastName
        holder.bDel.setOnClickListener {
            val lessonToDelete = lessonList[position]
            lessonList = lessonList.minus(lessonToDelete)
            notifyDataSetChanged()
        }
        fun showEditLessonDialog(lesson: LessonModel, position: Int) {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle(holder.itemView.context.getString(R.string.editLesson))

            val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_add_lesson, null)
            builder.setView(view)

            val etFirstName = view.findViewById<EditText>(R.id.etProductName)
            val etLastName = view.findViewById<EditText>(R.id.etProductPrice)

            etFirstName.setText(lesson.firstName)
            etLastName.setText(lesson.lastName)

            builder.setPositiveButton(holder.itemView.context.getString(R.string.update)) { dialog, _ ->
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    val updatedTeacher = LessonModel(firstName, lastName)
                    updateLesson(position, updatedTeacher)
                } else {
                    Toast.makeText(holder.itemView.context, holder.itemView.context.getString(R.string.fill_fields), Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            builder.setNegativeButton(holder.itemView.context.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
        holder.itemView.setOnClickListener {
            showEditLessonDialog(lesson, position)
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<LessonModel>){
        lessonList=list
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addLesson(product: LessonModel) {
        lessonList += product
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateLesson(position: Int, updatedLesson: LessonModel) {
        val updatedList = lessonList.toMutableList()
        updatedList[position] = updatedLesson
        lessonList = updatedList.toList()
        notifyItemChanged(position)
    }

}