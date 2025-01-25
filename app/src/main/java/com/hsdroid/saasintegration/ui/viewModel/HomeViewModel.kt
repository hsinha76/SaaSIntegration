package com.hsdroid.saasintegration.ui.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.hsdroid.saasintegration.data.local.model.TaskList
import com.hsdroid.saasintegration.data.repository.HomeRepository
import com.hsdroid.saasintegration.data.remote.model.TodoListResponse
import com.hsdroid.saasintegration.utils.APIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _taskResponse: MutableStateFlow<APIState<List<TaskList>>> =
        MutableStateFlow(APIState.EMPTY)
    val taskResponse: StateFlow<APIState<List<TaskList>>> = _taskResponse.asStateFlow()

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    private fun fetchTodoResponseFromServer() = viewModelScope.launch {
        homeRepository.fetchTodo().onStart {
            _taskResponse.value = APIState.LOADING
        }.catch {
            _taskResponse.value = APIState.FAILURE(it)
        }.collect {
            val todoListEntities = it.todos.map { response -> response.toTodoList() }
            homeRepository.addMultipleTaskToDb(todoListEntities)
        }
    }

    fun getAllLocalTasks() = viewModelScope.launch {
        homeRepository.getAllTasksFromDb().onStart {
            _taskResponse.value = APIState.LOADING
        }.catch {
            _taskResponse.value = APIState.FAILURE(it)
        }.collect {
            if (it.isEmpty()) {
                fetchTodoResponseFromServer()
            } else {
                _taskResponse.value = APIState.SUCCESS(it)
            }
        }
    }

    fun addTaskToDb(taskList: TaskList) = viewModelScope.launch {
        homeRepository.addTaskToDb(taskList)
        logAddTask(taskList.task, taskList.completed)
    }

    fun updateTask(taskList: TaskList) = viewModelScope.launch {

        val previousTask = homeRepository.getTasksFromDbById(taskList.id)
        homeRepository.updateTask(taskList)

        if (taskList.completed && !previousTask.completed) {
            logCompletedTask(taskList.task, true)
        } else {
            logEditTask(taskList.task, taskList.completed)
        }
    }

    private fun logAddTask(taskName: String, taskStatus: Boolean) {
        val params = bundleOf(
            "task_name" to taskName, "task_status" to taskStatus
        )
        firebaseAnalytics.logEvent("add_task", params)
    }

    private fun logEditTask(taskName: String, taskStatus: Boolean) {
        val params = bundleOf(
            "task_name" to taskName, "task_status" to taskStatus
        )
        firebaseAnalytics.logEvent("edit_task", params)
    }

    private fun logCompletedTask(taskName: String, taskStatus: Boolean) {
        val params = bundleOf(
            "task_name" to taskName,
            "task_status" to taskStatus,
        )
        firebaseAnalytics.logEvent("completed_task", params)
    }

    private fun TodoListResponse.toTodoList(): TaskList {
        return TaskList(
            completed = this.completed, task = this.todo
        )
    }
}