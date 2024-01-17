package com.example.todolist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.database.TodoDatabase
import com.example.todolist.data.TodoRepositoryImpl
import com.example.todolist.domain.models.TodoDomain
import com.example.todolist.domain.useCases.DeleteFromDBUseCase
import com.example.todolist.domain.useCases.GetAllTodosUseCase
import com.example.todolist.domain.useCases.InsertToDBUseCase
import com.example.todolist.domain.useCases.UpdateDBUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepositoryImpl
    val allTodo : LiveData<List<TodoDomain>>
    private val deleteFromDBUseCase: DeleteFromDBUseCase
    private val getAllTodosUseCase: GetAllTodosUseCase
    private val insertToDBUseCase: InsertToDBUseCase
    private val updateDBUseCase: UpdateDBUseCase

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
        repository = TodoRepositoryImpl(dao)
        deleteFromDBUseCase = DeleteFromDBUseCase(repository)
        getAllTodosUseCase = GetAllTodosUseCase(repository)
        insertToDBUseCase = InsertToDBUseCase(repository)
        updateDBUseCase = UpdateDBUseCase(repository)
        allTodo = getAllTodosUseCase.execute()

    }

    fun insertTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        insertToDBUseCase.execute(todo)
    }

    fun updateTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        updateDBUseCase.execute(todo)
    }

    fun deleteTodo(todo: TodoDomain) = viewModelScope.launch(Dispatchers.IO){
        deleteFromDBUseCase.execute(todo)
    }
}