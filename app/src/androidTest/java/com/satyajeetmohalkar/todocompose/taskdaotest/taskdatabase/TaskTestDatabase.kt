package com.satyajeetmohalkar.todocompose.taskdaotest.taskdatabase

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.satyajeetmohalkar.todocompose.data.local.database.TodoTaskDao
import com.satyajeetmohalkar.todocompose.data.local.database.TodoTaskDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class TaskTestDatabase {

    protected lateinit var taskTestDatabase: TodoTaskDatabase
    protected lateinit var taskDao: TodoTaskDao

    @Before
    fun createTestDatabase() {
        taskTestDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoTaskDatabase::class.java
        ).build()
        taskDao = taskTestDatabase.todoDao()
    }

    @After
    fun closeDatabase() {
        taskTestDatabase.close()
    }

}