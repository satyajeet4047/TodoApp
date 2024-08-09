package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.di.TaskViewModelFactory
import com.satyajeetmohalkar.todocompose.ui.state.TaskListUiState
import com.satyajeetmohalkar.todocompose.ui.state.TaskUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(assistedFactory = TaskViewModelFactory::class)
class TaskViewModel @AssistedInject constructor(
    @Assisted private val taskId : Int,
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _taskUiState = MutableStateFlow(TaskUiState.initialState())
    val taskUiState : StateFlow<TaskUiState> = _taskUiState

    init {
        getTask()
    }


    private fun getTask() {
        viewModelScope.launch {
           taskRepository
                .getTask(taskId)
                .firstOrNull()?.let { task ->
                    _taskUiState.update {
                        it.copy(
                            isLoading = false,
                            title = task.title,
                            description = task.description,
                            priority = task.priority
                        )
                    }
               } ?: kotlin.run {
               _taskUiState.update {
                   it.copy(
                       isLoading = false
                   )
               }
           }
        }
    }

}