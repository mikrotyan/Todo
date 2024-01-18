package com.example.todolist.presentation.add_todo_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.domain.models.TodoDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val _todo = MutableLiveData<TodoDomain?>()
    val todo: MutableLiveData<TodoDomain?>
        get() = _todo

    fun setTodoData(todo: TodoDomain) {
        _todo.value = todo
    }

    fun clearTodoData() {
        _todo.value = null
    }
}
