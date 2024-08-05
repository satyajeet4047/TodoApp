package com.satyajeetmohalkar.todocompose.data.local.repository

import com.satyajeetmohalkar.todocompose.data.local.database.TodoTaskDao
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
   private val todoTaskDao: TodoTaskDao
) : TodoRepository {
    override fun getAllTasks(): Flow<List<TodoTask>> {
        return todoTaskDao.getAllTodoTasks()
    }

    override fun getTask(taskId: Int): Flow<TodoTask> {
        return  todoTaskDao.getTodoTask(taskId)
    }

    override fun getSortedTasksByLowToHigh(): Flow<List<TodoTask>> {
        return todoTaskDao.sortTasksByLowToHigh()
    }

    override fun getSortedTasksByHighToLow(): Flow<List<TodoTask>> {
        return todoTaskDao.sortTasksByHighToLow()
    }

    override fun searchTasks(searchQuery: String): Flow<List<TodoTask>> {
        return todoTaskDao.searchTodoTask(searchQuery)
    }

    override suspend fun addTask(task: TodoTask) {
        return todoTaskDao.insertTask(task)
    }

    override suspend fun update(task: TodoTask) {
        return todoTaskDao.insertTask(task)
    }

    override suspend fun deleteAllTasks() {
        return todoTaskDao.deleteAllTasks()
    }

    override suspend fun deleteTask(task: TodoTask) {
        return todoTaskDao.deleteTask(task)
    }
}