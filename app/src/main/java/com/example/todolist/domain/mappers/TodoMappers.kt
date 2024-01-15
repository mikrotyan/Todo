package com.example.todolist.domain.mappers

import com.example.todolist.data.database.Todo
import com.example.todolist.domain.models.TodoDomain

fun Todo.toDomainModel(): TodoDomain {
    return TodoDomain(id, title, note, date)
}

fun TodoDomain.toEntity(): Todo {
    return Todo(id,title,note,date)
}