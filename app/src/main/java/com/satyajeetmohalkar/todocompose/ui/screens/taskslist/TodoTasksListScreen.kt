package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.TodoAppBar
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.theme.Purple40
import com.satyajeetmohalkar.todocompose.ui.theme.Purple80
import com.satyajeetmohalkar.todocompose.ui.theme.PurpleGrey40
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor


@Composable
fun TodoTasksListScreen(
    taskListViewModel: TaskListViewModel,
    navigateToTaskDetails: (Int) -> Unit
) {
    val searchQuery by taskListViewModel.searchQuery.collectAsState()
    val searchBarState by taskListViewModel.searchBarState.collectAsState()
    val tasksListUiState by taskListViewModel.tasksListUiState.collectAsState()

    TaskListContent(
        isLoading = tasksListUiState.isLoading,
        taskList = tasksListUiState.tasks ,
        searchBarState = searchBarState,
        searchQuery = searchQuery,
        onSearchQueryChange = taskListViewModel::onSearchQueryChange,
        onSearchCloseClicked = taskListViewModel::onSearchCloseClicked,
        onSearchIconClicked = taskListViewModel::onSearchIconClicked,
        navigateToTaskDetails = navigateToTaskDetails
    )

}

@Composable
fun TaskListContent(
    isLoading : Boolean,
    taskList : List<TodoTask>,
    searchBarState : SearchBarState,
    searchQuery : String,
    onSearchQueryChange: (String) -> Unit,
    onSearchCloseClicked: () -> Unit,
    onSearchIconClicked : () -> Unit,
    navigateToTaskDetails: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TodoAppBar(
                title = "Tasks",
                searchBarState = searchBarState,
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onSearchCloseClicked = onSearchCloseClicked,
                onSearchIconClicked = onSearchIconClicked
            )
        },
        floatingActionButton = {
            FabButton(navigateToTaskDetails)
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            if(isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    color = Purple40,
                    backgroundColor = PurpleGrey40
                )
            } else if(taskList.isEmpty()) {
                EmptyContent()
            }
            else {
                TaskList(
                    tasks = taskList,
                    navigateToTaskDetails = navigateToTaskDetails
                )
            }
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
