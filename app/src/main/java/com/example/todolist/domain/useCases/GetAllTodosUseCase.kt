package com.example.todolist.domain.useCases

import androidx.lifecycle.LiveData
import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.models.TodoDomain
import javax.inject.Inject

class GetAllTodosUseCase(private val repository: TodoRepository) {
    fun execute(): LiveData<List<TodoDomain>> {
        return repository.allTodos
    }
}