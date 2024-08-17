package com.satyajeetmohalkar.todocompose.ui.components.todoappbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.satyajeetmohalkar.todocompose.utils.TaskAction


@Composable
fun TaskAppBar(
    taskId : Int,
    title: String?,
    isEnabled : Boolean,
    onNavigateUp: () -> Unit,
    addActionClicked : () -> Unit,
    deleteActionClicked : () -> Unit
) {

    TopAppBar(
        title = {
            TopAppBarTitle(title)
        },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate Back")
            }
        },
        actions = {
            if(taskId != -1) {
                DeleteTaskAction(deleteActionClicked = deleteActionClicked)
            }
            AddTaskAction(addActionClicked = addActionClicked, isEnabled = isEnabled)
        }
    )
}



