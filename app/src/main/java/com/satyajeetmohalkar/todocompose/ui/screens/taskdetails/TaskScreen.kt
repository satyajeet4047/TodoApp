package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.AddTaskAppBar
import com.satyajeetmohalkar.todocompose.utils.TaskAction


@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
    onNavigateUp: () -> Unit,

) {
    Scaffold(
        topBar = {
            AddTaskAppBar(
                title = "Add Task",
                onNavigateUp = onNavigateUp
            )
        }
    ) { contentPadding ->

        Surface(
            modifier = Modifier.padding(contentPadding)
        ) {

        }
    }

}