package com.satyajeetmohalkar.todocompose.ui.state

import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask

data class TaskUiState(
   val isLoading : Boolean,
   val title : String?,
   val description : String?,
   val priority: Priority?
) {
   companion object {

      fun initialState() = TaskUiState(
         isLoading = true,
         title = null,
         description = null,
         priority = null
      )
   }
}
