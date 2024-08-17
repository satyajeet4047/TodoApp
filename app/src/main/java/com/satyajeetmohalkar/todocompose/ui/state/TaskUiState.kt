package com.satyajeetmohalkar.todocompose.ui.state

import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask

data class TaskUiState(
   val taskId : Int = -1,
   val isLoading : Boolean,
   val title : String,
   val description : String,
   val priority: Priority,
   val isValidTitle : Boolean,
   val isValidDescription : Boolean,
   val shouldEnableAddButton : Boolean

) {
   companion object {

      fun initialState() = TaskUiState(
         isLoading = true,
         title = "",
         description = "",
         priority = Priority.LOW,
         isValidTitle = true,
         isValidDescription = true,
         shouldEnableAddButton = false
      )
   }
}
