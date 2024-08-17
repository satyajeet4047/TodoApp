package com.satyajeetmohalkar.todocompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.utils.Constants

@Database(entities = [TodoTask::class], version = Constants.TODO_DATABASE_VERSION, exportSchema = false)
abstract class TodoTaskDatabase() : RoomDatabase() {

    abstract fun todoDao() : TodoTaskDao
}