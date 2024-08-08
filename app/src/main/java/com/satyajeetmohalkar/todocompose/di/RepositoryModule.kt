package com.satyajeetmohalkar.todocompose.di

import com.satyajeetmohalkar.todocompose.data.local.repository.TodoRepository
import com.satyajeetmohalkar.todocompose.data.local.repository.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTodoRepository(todoRepositoryImpl: TodoRepositoryImpl) : TodoRepository
}