package com.hsdroid.saasintegration.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskList")
data class TaskList(
    var completed: Boolean = false,
    var task: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)