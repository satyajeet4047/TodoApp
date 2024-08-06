package com.satyajeetmohalkar.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

const val TASKS_ROUTE = "tasks_route"
@Composable
fun TodoTasksNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.TaskListScreen.route, route = TASKS_ROUTE) {

        composable(Screens.TaskListScreen.route) {
                //TODO add tasks list screen
        }

        composable(Screens.TaskDetailsScreen.route, arguments = listOf(
            navArgument(Screens.TaskDetailsScreen.ARG_TASK_ID) {
                type = NavType.IntType
            }
        )) {
            val taskId = requireNotNull(it.arguments?.getInt(Screens.TaskDetailsScreen.ARG_TASK_ID))
            //TODO add tasks details screen
        }

    }

}