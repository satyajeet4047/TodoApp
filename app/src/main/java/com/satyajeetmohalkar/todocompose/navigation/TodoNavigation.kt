package com.satyajeetmohalkar.todocompose.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.satyajeetmohalkar.todocompose.ui.screens.taskslist.TodoTasksListScreen

const val TASKS_ROUTE = "tasks_route"
@Composable
fun TodoTasksNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.TaskListScreen.route, route = TASKS_ROUTE) {

        composable(Screens.TaskListScreen.route) {
            TodoTasksListScreen (
                taskListViewModel = hiltViewModel(),
                navigateToTaskDetails = { taskId -> navController.navigateToTaskDetails(taskId)}
            )
        }

        composable(Screens.TaskDetailsScreen.route, arguments = listOf(
            navArgument(Screens.TaskDetailsScreen.ARG_TASK_ID) {
                type = NavType.IntType
            }
        )) {
            val taskId = requireNotNull(it.arguments?.getInt(Screens.TaskDetailsScreen.ARG_TASK_ID))

            Toast.makeText(LocalContext.current,"Navigating to details", Toast.LENGTH_LONG).show()
        }

    }

}

fun NavController.navigateToTaskDetails(taskId : Int) = navigate(Screens.TaskDetailsScreen.getTaskDetailsRoute(taskId))