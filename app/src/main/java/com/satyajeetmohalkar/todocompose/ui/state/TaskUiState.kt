package com.satyajeetmohalkar.todocompose.ui.state

import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask

data class TaskUiState(
   val isLoading : Boolean,
   val title : String,
   val description : String,
   val priority: Priority,
   val isValidTitle : Boolean = true,
   val isValidDescription : Boolean = true
) {
   companion object {

      fun initialState() = TaskUiState(
         isLoading = true,
         title = "",
         description = "",
         priority = Priority.LOW
      )
   }
}
