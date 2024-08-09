package com.satyajeetmohalkar.todocompose.ui.state

import com.satyajeetmohalkar.todocompose.data.models.TodoTask

data class TaskListUiState(
    val isLoading : Boolean,
    val tasks : List<TodoTask>
)
