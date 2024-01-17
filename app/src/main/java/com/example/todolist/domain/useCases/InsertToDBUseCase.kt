package com.example.todolist.domain.useCases

import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.models.TodoDomain
import javax.inject.Inject

class InsertToDBUseCase(private val repository: TodoRepository) {
    suspend fun execute(todo: TodoDomain) {
        repository.insert(todo)
    }
}