package com.satyajeetmohalkar.todocompose.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {

    //Inset a task
    @Upsert
    suspend fun insertTask(todoTask: TodoTask)


    //Delete a task
    @Delete
    suspend fun deleteTask(todoTask: TodoTask)

    //Delete all tasks
    @Query("DELETE FROM ${Constants.TODO_TABLE_NAME}")
    suspend fun deleteAllTasks()

    //Get list of all tasks
    @Query("SELECT * FROM ${Constants.TODO_TABLE_NAME} ORDER BY id ASC")
    fun getAllTodoTasks() : Flow<List<TodoTask>>

    //Get a specific task from a task id
    @Query("SELECT * FROM ${Constants.TODO_TABLE_NAME} where id=:taskId")
    fun getTodoTask(taskId : Int) : Flow<TodoTask>

    //Search a task based on query which matches title or description
    @Query("SELECT * FROM ${Constants.TODO_TABLE_NAME} where title LIKE :searchQuery or description LIKE :searchQuery")
    fun searchTodoTask(searchQuery : String) : Flow<List<TodoTask>>

    //Sort tasks based on priority low to high
    @Query("SELECT * FROM ${Constants.TODO_TABLE_NAME} ORDER BY " +
            "CASE WHEN priority LIKE 'L%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'H%' THEN 3 END")
    fun sortTasksByLowToHigh() : Flow<List<TodoTask>>

    //Sort tasks based on priority high to low
    @Query("SELECT * FROM ${Constants.TODO_TABLE_NAME} ORDER BY " +
            "CASE WHEN priority LIKE 'H%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'L%' THEN 3 END")
    fun sortTasksByHighToLow() : Flow<List<TodoTask>>
}