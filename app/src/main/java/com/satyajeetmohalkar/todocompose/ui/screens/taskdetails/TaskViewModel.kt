package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.di.TaskViewModelFactory
import com.satyajeetmohalkar.todocompose.ui.state.TaskUiState
import com.satyajeetmohalkar.todocompose.utils.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                       isLoading = false,
                       title = "Add Task",
                       description = "")
               }
           }
        }
    }

    fun onTitleChange(title : String) {
        if(title.length <=  Constants.MAX_TITLE_LENGTH) {
            _taskUiState.update {
                it.copy(
                    title = title,
                    isValidTitle = title.isNotEmpty()
                )
            }
        }

    }

    fun onDescriptionChange(description : String) {
            _taskUiState.update {
                it.copy(
                    description = description,
                    isValidDescription = description.isNotEmpty()
                )
            }
    }

    fun onPriorityChange(priority: Priority) {
        _taskUiState.update {
            it.copy(
                priority = priority
            )
        }
    }

     fun addTask() {
        viewModelScope.launch {
            taskUiState.value.let {
                if (taskId == -1) {
                    taskRepository.addTask(
                        TodoTask(title = it.title, description = it.description, priority = it.priority)
                    )
                } else {
                    taskRepository.update(TodoTask(id = taskId,title = it.title, description = it.description, priority = it.priority))
                }
            }
        }
    }

    fun isValidTaskData() : Boolean {
        return taskUiState.value.isValidTitle && taskUiState.value.isValidDescription
    }

}