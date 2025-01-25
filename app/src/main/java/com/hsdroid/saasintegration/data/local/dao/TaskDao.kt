package com.hsdroid.saasintegration.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hsdroid.saasintegration.data.local.model.TaskList
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskList: TaskList)

    @Insert
    suspend fun addMultipleTasks(taskList: List<TaskList>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(taskList: TaskList)

    @Query("SELECT * from tasklist order by id DESC")
    fun getLocalTasks(): Flow<List<TaskList>>

    @Query("SELECT * from tasklist WHERE id = :id")
    suspend fun getLocalTaskById(id: Int): TaskList
}