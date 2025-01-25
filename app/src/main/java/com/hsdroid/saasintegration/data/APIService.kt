package com.hsdroid.saasintegration.data

import com.hsdroid.saasintegration.data.remote.model.TodoResponse
import retrofit2.http.GET

interface APIService {

    @GET("todos")
    suspend fun fetchTodoFromServer(): TodoResponse
}