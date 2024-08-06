package com.satyajeetmohalkar.todocompose.navigation

sealed class Screens(val route : String, val screenTitle : String = "") {
    object TaskListScreen : Screens("taskListScreen", "Tasks")

    object TaskDetailsScreen : Screens("taskDetailsScreen/{taskId}", "Task Detail") {

        fun getTaskDetailsRoute(taskId : Int) = "taskDetailsScreen/taskId"

        const val ARG_TASK_ID: String = "taskId"
    }

}