package com.example.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.database.TodoDatabase
import com.example.todolist.data.TodoRepositoryImpl
import com.example.todolist.data.database.Todo
import com.example.todolist.domain.models.TodoDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepositoryImpl
    val allTodo : LiveData<List<TodoDomain>>

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepositoryImpl(dao)
        allTodo = repository.allTodos
    }

    fun insertTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(todo)
    }

    fun updateTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        repository.update(todo)
    }

    fun deleteTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todo)
    }
}