package com.example.todolist.presentation.add_todo_activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.todolist.databinding.ActivityAddTodoBinding
import com.example.todolist.domain.models.TodoDomain
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding
    private val viewModel: AddTodoViewModel by viewModels()
    private var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this).get(com.example.todolist.presentation.add_todo_activity.AddTodoViewModel::class.java)

        try {
            val oldTodo = intent.getSerializableExtra("current_todo") as TodoDomain
            viewModel.setTodoData(oldTodo)
            binding.etTitle.setText(oldTodo.title)
            binding.etNote.setText(oldTodo.note)
            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (isUpdate) {
            binding.imgDelete.visibility = View.VISIBLE
        } else {
            binding.imgDelete.visibility = View.INVISIBLE
        }

        binding.imgCheck.setOnClickListener {
            saveOrUpdateTodo()
        }

        binding.imgDelete.setOnClickListener {
            deleteTodo()
        }

        binding.imgBackArrow.setOnClickListener {
            goBack()
        }
    }

    private fun saveOrUpdateTodo() {
        val title = binding.etTitle.text.toString()
        val todoDescription = binding.etNote.text.toString()

        if (title.isNotEmpty() && todoDescription.isNotEmpty()) {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

            if (isUpdate) {
                val updatedTodo = TodoDomain(viewModel.todo.value?.id, title, todoDescription, formatter.format(Date()))
                viewModel.setTodoData(updatedTodo)
            } else {
                val newTodo = TodoDomain(null, title, todoDescription, formatter.format(Date()))
                viewModel.setTodoData(newTodo)
            }

            val intent = Intent()
            intent.putExtra("todo", viewModel.todo.value)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(this@AddTodoActivity, "Please enter some data", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteTodo() {
        val oldTodo = viewModel.todo.value
        val intent = Intent()
        intent.putExtra("todo", oldTodo)
        intent.putExtra("delete_todo", true)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun goBack() {
        onBackPressed()
    }
}
