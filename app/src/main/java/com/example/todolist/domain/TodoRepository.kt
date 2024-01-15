package com.example.todolist.domain

import androidx.lifecycle.LiveData
import com.example.todolist.domain.models.TodoDomain


interface TodoRepository {

    val allTodos: LiveData<List<TodoDomain>>

    suspend fun insert(todoDomain: TodoDomain)

    suspend fun delete(todoDomain: TodoDomain)

    suspend fun update(todoDomain: TodoDomain)
}