package com.example.todolist.domain.useCases

import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.models.TodoDomain
import javax.inject.Inject

class UpdateDBUseCase(private val repository: TodoRepository) {
    suspend fun execute(todoDomain: TodoDomain) {
        repository.update(todoDomain)
    }
}