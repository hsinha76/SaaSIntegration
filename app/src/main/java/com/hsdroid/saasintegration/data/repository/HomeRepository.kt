package com.hsdroid.saasintegration.data.repository

import com.hsdroid.saasintegration.data.APIService
import com.hsdroid.saasintegration.data.local.dao.TaskDao
import com.hsdroid.saasintegration.data.local.model.TaskList
import com.hsdroid.saasintegration.data.remote.model.TodoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: APIService,
    private val taskDao: TaskDao
) {

    fun fetchTodo(): Flow<TodoResponse> = flow {
        emit(apiService.fetchTodoFromServer())
    }.flowOn(Dispatchers.IO)

    fun getAllTasksFromDb(): Flow<List<TaskList>> {
        return taskDao.getLocalTasks()
    }

    suspend fun addTaskToDb(taskList: TaskList) {
        taskDao.addTask(taskList)
    }

    suspend fun addMultipleTaskToDb(taskList: List<TaskList>) {
        taskDao.addMultipleTasks(taskList)
    }

    suspend fun updateTask(taskList: TaskList) {
        taskDao.updateTask(taskList)
    }

    suspend fun getTasksFromDbById(id: Int): TaskList {
        return taskDao.getLocalTaskById(id)
    }

}