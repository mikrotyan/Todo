package com.example.todolist.domain.models

data class TodoDomain(
    val id: Int?,
    val title: String?,
    val note: String?,
    val date: String
): java.io.Serializable