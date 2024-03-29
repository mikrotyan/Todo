package com.example.todolist.presentation.first_activity

import com.example.todolist.presentation.add_todo_activity.AddTodoActivity
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.presentation.adapters.TodoAdapter
import com.example.todolist.data.database.TodoDatabase
import com.example.todolist.databinding.ActivityFirstBinding
import com.example.todolist.domain.models.TodoDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstActivity : AppCompatActivity(), TodoAdapter.TodoClickListener {

    private lateinit var binding: ActivityFirstBinding
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        viewModel.allTodo.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }
        }
    }

    private fun initUI() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TodoAdapter(this, this)
        binding.recyclerView.adapter = adapter

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val todo = result.data?.getSerializableExtra("todo") as? TodoDomain
                    if (todo != null) {
                        viewModel.insertTodo(todo)
                    }
                }
            }

        binding.fabAddTodo.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            getContent.launch(intent)
        }

    }

    private val updateOrDeleteTodo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todo = result.data?.getSerializableExtra("todo") as TodoDomain
                val isDelete = result.data?.getBooleanExtra("delete_todo", false) as Boolean
                if (todo != null && !isDelete) {
                    viewModel.updateTodo(todo)
                }else if(todo != null && isDelete){
                    viewModel.deleteTodo(todo)
                }
            }
        }



    override fun onItemClicked(todo: TodoDomain) {
        val intent = Intent(this@FirstActivity, AddTodoActivity::class.java)
        intent.putExtra("current_todo", todo)
        updateOrDeleteTodo.launch(intent)
    }
}