package com.example.todolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.todolist.data.database.Todo
import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.mappers.toDomainModel
import com.example.todolist.domain.mappers.toEntity
import com.example.todolist.domain.models.TodoDao
import com.example.todolist.domain.models.TodoDomain

class TodoRepositoryImpl(private val todoDao: TodoDao): TodoRepository {

    override val allTodos: LiveData<List<TodoDomain>> = todoDao.getAllTodos().map { todoList ->
        todoList.map { it.toDomainModel() }
    }

    override suspend fun insert(todoDomain: TodoDomain) {
        todoDao.insert(todoDomain.toEntity())
    }

    override suspend fun delete(todoDomain: TodoDomain) {
        todoDao.delete(todoDomain.toEntity())
    }

    override suspend fun update(todoDomain: TodoDomain) {
        todoDao.update(todoDomain.id, todoDomain.title, todoDomain.note)
    }
}