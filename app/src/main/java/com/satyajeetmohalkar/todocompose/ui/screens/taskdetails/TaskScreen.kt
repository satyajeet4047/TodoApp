package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.AddTaskAppBar


@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
    onNavigateUp: () -> Unit
) {
    val taskUiState by taskViewModel.taskUiState.collectAsState()
    TaskContent(
        title = taskUiState.title,
        onNavigateUp = onNavigateUp
    )
}

@Composable
fun TaskContent(
    title: String?,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            AddTaskAppBar(
                title = title ?: "Add Task",
                onNavigateUp = onNavigateUp
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {

        }

    }
}