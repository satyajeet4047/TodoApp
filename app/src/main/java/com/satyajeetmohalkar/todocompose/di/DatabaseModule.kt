package com.satyajeetmohalkar.todocompose.di

import android.content.Context
import androidx.room.Room
import com.satyajeetmohalkar.todocompose.data.local.database.TodoTaskDao
import com.satyajeetmohalkar.todocompose.data.local.database.TodoTaskDatabase
import com.satyajeetmohalkar.todocompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext appContext : Context): TodoTaskDatabase {
       return Room.databaseBuilder(
            appContext,
            TodoTaskDatabase::class.java,
            Constants.TODO_DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun taskDao(todoTaskDatabase: TodoTaskDatabase) : TodoTaskDao {
       return todoTaskDatabase.todoDao()
    }
}