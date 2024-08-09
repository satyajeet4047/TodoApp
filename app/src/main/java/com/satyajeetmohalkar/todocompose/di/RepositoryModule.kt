package com.satyajeetmohalkar.todocompose.di

import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl) : TaskRepository
}