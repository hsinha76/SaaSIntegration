package com.hsdroid.saasintegration.data.remote.model

data class TodoResponse(
    val limit: Int = 0,
    val skip: Int = 0,
    val todos: List<TodoListResponse> = emptyList(),
    val total: Int = 0
)

data class TodoListResponse(
    val completed: Boolean = false,
    val id: Int = 0,
    val todo: String = "",
    val userId: Int = 0
)