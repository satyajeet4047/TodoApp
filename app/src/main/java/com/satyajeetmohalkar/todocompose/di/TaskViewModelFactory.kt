package com.satyajeetmohalkar.todocompose.di

import com.satyajeetmohalkar.todocompose.ui.screens.taskdetails.TaskViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface TaskViewModelFactory {

    fun create(taskId : Int) : TaskViewModel
}