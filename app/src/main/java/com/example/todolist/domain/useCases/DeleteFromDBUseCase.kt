package com.example.todolist.domain.useCases

import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.models.TodoDomain
import javax.inject.Inject

class DeleteFromDBUseCase(private val repository: TodoRepository) {
    suspend fun execute(todoDomain: TodoDomain) {
        repository.delete(todoDomain)
    }
}