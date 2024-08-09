package com.satyajeetmohalkar.todocompose.data.local.repository

import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks() : Flow<List<TodoTask>>

    fun getTask(taskId : Int) : Flow<TodoTask>

    fun getSortedTasksByLowToHigh() : Flow<List<TodoTask>>

    fun getSortedTasksByHighToLow() : Flow<List<TodoTask>>

    fun searchTasks(searchQuery : String) : Flow<List<TodoTask>>

    suspend fun addTask(task: TodoTask)

    suspend fun update(task: TodoTask)

    suspend fun deleteAllTasks()

    suspend fun deleteTask(task: TodoTask)
}