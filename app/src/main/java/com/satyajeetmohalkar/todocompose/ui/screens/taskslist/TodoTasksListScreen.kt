package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.TodoAppBar
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor


@Composable
fun TodoTasksListScreen(
    taskListViewModel: TaskListViewModel,
    navigateToTaskDetails: (Int) -> Unit
) {
    val searchQuery by taskListViewModel.searchQuery.collectAsState()
    val searchBarState by taskListViewModel.searchBarState.collectAsState()

    Scaffold(
        topBar = {
            TodoAppBar(
                title = "Tasks",
                searchBarState = searchBarState,
                searchQuery = searchQuery,
                onSearchQueryChange = taskListViewModel::onSearchQueryChange,
                onSearchCloseClicked = taskListViewModel::onSearchCloseClicked,
                onSearchIconClicked = taskListViewModel::onSearchIconClicked

            )
        },
        floatingActionButton = {
            FabButton(navigateToTaskDetails)
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {

        }
    }
}

@Composable
fun FabButton(navigateToTaskDetails: (Int) -> Unit) {
    FloatingActionButton(
        onClick = { navigateToTaskDetails(-1) },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Notes Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}
