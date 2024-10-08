package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.ui.components.dialog.DeleteTaskConfirmationDialog
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.TodoAppBar
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.theme.Purple40
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor
import com.satyajeetmohalkar.todocompose.utils.ComposeImmutableList
import com.satyajeetmohalkar.todocompose.utils.rememberComposeImmutableList
import com.satyajeetmohalkar.todocompose.utils.toComposeImmutableList


@Composable
fun TodoTasksListScreen(
    taskListViewModel: TaskListViewModel,
    navigateToTaskDetails: (Int) -> Unit
) {
    val searchQuery by taskListViewModel.searchQuery.collectAsStateWithLifecycle()
    val searchBarState by taskListViewModel.searchBarState.collectAsStateWithLifecycle()
    val tasksListUiState by taskListViewModel.tasksListUiState.collectAsStateWithLifecycle()

    val tasks by rememberComposeImmutableList { tasksListUiState.tasks }

    LaunchedEffect(tasksListUiState) {
        Log.d("Satya", "Received new UI state: $tasksListUiState")
    }

    var shouldShowDeleteConfirmationDialog by remember { mutableStateOf(false) }

    val isDarkModeEnabled by taskListViewModel.darkMode.collectAsStateWithLifecycle()

    TaskListContent(
        isLoading = tasksListUiState.isLoading,
        taskList = tasks,
        searchBarState = searchBarState,
        searchQuery = searchQuery,
        isDarkModeEnabled = isDarkModeEnabled,
        onSearchQueryChange = taskListViewModel::onSearchQueryChange,
        onSearchCloseClicked = taskListViewModel::onSearchCloseClicked,
        onSearchIconClicked = taskListViewModel::onSearchIconClicked,
        navigateToTaskDetails = navigateToTaskDetails,
        onSortClicked = taskListViewModel::onSortTasks,
        onDeleteAllClicked = {
            shouldShowDeleteConfirmationDialog = true
        },
        onThemeChangeClick = {
            taskListViewModel.onThemeChange(it)
        }
    )

    DeleteTaskConfirmationDialog("Remove Everything??", "Are you sure you want to delete all todo tasks permanently ?",shouldShowDeleteConfirmationDialog, taskListViewModel::deleteAllTasks, {})

}

@Composable
fun TaskListContent(
    isLoading: Boolean,
    taskList: ComposeImmutableList<TodoTask>,
    searchBarState: SearchBarState,
    searchQuery: String,
    isDarkModeEnabled: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSearchCloseClicked: () -> Unit,
    onSearchIconClicked: () -> Unit,
    navigateToTaskDetails: (Int) -> Unit,
    onDeleteAllClicked: () -> Unit,
    onThemeChangeClick : (Boolean) -> Unit,
    onSortClicked : (Priority) -> Unit
) {
    Scaffold(
        topBar = {
            TodoAppBar(
                title = "Tasks",
                searchBarState = searchBarState,
                searchQuery = searchQuery,
                isDarkModeEnabled = isDarkModeEnabled,
                shouldShowTopBarActions = taskList.isNotEmpty(),
                onSearchQueryChange = onSearchQueryChange,
                onSearchCloseClicked = onSearchCloseClicked,
                onSearchIconClicked = onSearchIconClicked,
                onDeleteAllClicked = onDeleteAllClicked,
                onThemeChangeClick = onThemeChangeClick,
                onSortClicked = onSortClicked
            )
        },
        floatingActionButton = {
            FabButton(navigateToTaskDetails)
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            if(isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    color = Purple40,
                    modifier = Modifier.size(36.dp)
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
