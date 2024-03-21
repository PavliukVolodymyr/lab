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
import com.example.myapplication.model.TeacherModel

class TeacherAdapter:RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    private var teacherList= emptyList<TeacherModel>()
    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        val bDel: Button =itemView.findViewById(R.id.bDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher_layout,parent,false)
        return TeacherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]
        holder.tvFirstName.text = teacher.firstName
        holder.tvLastName.text = teacher.lastName
        holder.bDel.setOnClickListener {
            val productToDelete = teacherList[position]
            teacherList = teacherList.minus(productToDelete)
            notifyDataSetChanged()
        }
        fun showEditTeacherDialog(teacher: TeacherModel, position: Int) {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Редагувати вчителя")

            val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.dialog_add_teacher, null)
            builder.setView(view)

            val etFirstName = view.findViewById<EditText>(R.id.etProductName)
            val etLastName = view.findViewById<EditText>(R.id.etProductPrice)

            etFirstName.setText(teacher.firstName)
            etLastName.setText(teacher.lastName)

            builder.setPositiveButton("Оновити") { dialog, _ ->
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    val updatedTeacher = TeacherModel(firstName, lastName)
                    updateTeacher(position, updatedTeacher)
                } else {
                    Toast.makeText(holder.itemView.context, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            builder.setNegativeButton("Відмінити") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }
        holder.itemView.setOnClickListener {
            showEditTeacherDialog(teacher, position)
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<TeacherModel>){
        teacherList=list
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addTeacher(product: TeacherModel) {
        teacherList += product
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTeacher(position: Int, updatedTeacher: TeacherModel) {
        val updatedList = teacherList.toMutableList()
        updatedList[position] = updatedTeacher
        teacherList = updatedList.toList()
        notifyItemChanged(position)
    }

}