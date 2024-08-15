package com.satyajeetmohalkar.todocompose.navigation

import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.satyajeetmohalkar.todocompose.di.TaskViewModelFactory
import com.satyajeetmohalkar.todocompose.ui.screens.taskdetails.TaskScreen
import com.satyajeetmohalkar.todocompose.ui.screens.taskslist.TodoTasksListScreen

const val TASKS_ROUTE = "tasks_route"

@Composable
fun TodoTasksNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.TaskListScreen.route,
        route = TASKS_ROUTE,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(200)
            )
        }
    ) {

        composable(Screens.TaskListScreen.route) {
            TodoTasksListScreen(
                taskListViewModel = hiltViewModel(),
                navigateToTaskDetails = { taskId ->
                    navController.navigateToTaskDetails(taskId)
                }
            )
        }

        composable(Screens.TaskDetailsScreen.route, arguments = listOf(
            navArgument(Screens.TaskDetailsScreen.ARG_TASK_ID) {
                type = NavType.IntType
            }
        )) {
            val taskId = requireNotNull(it.arguments?.getInt(Screens.TaskDetailsScreen.ARG_TASK_ID))
            // Create an assisted hilt view model
            val taskViewModel = hiltViewModel(
                creationCallback = { factory: TaskViewModelFactory ->
                    factory.create(taskId)
                }
            )
            TaskScreen(
                taskViewModel = taskViewModel,
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }

    }

}

fun NavController.navigateToTaskDetails(taskId: Int) =
    navigate(Screens.TaskDetailsScreen.getTaskDetailsRoute(taskId))