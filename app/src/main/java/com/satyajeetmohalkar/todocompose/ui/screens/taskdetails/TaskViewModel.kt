package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import androidx.lifecycle.ViewModel
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.di.TaskViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel(assistedFactory = TaskViewModelFactory::class)
class TaskViewModel @AssistedInject constructor(
    @Assisted private val taskId : Int,
    private val taskRepository: TaskRepository
): ViewModel() {

    
}