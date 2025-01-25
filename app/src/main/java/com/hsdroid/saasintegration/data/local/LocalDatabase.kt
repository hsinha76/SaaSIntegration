package com.hsdroid.saasintegration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsdroid.saasintegration.data.local.dao.TaskDao
import com.hsdroid.saasintegration.data.local.model.TaskList

@Database(entities = [TaskList::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}