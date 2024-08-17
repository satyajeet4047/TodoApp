package com.satyajeetmohalkar.todocompose.ui.components.todoappbar

import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.ui.components.dropdown.PriorityItem
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor


@Composable
fun TasksListActions(
    searchBarState: SearchBarState,
    searchQuery: String,
    isDarkModeEnabled: Boolean,
    shouldShowTopBarActions: Boolean,
    onSearchCloseClicked: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
    onThemeChangeClick: (Boolean) -> Unit
) {
    when (searchBarState) {
        SearchBarState.CLOSED -> {
            TaskListDefaultActions(
                isDarkModeEnabled,
                shouldShowTopBarActions,
                onSearchIconClicked,
                onSortClicked,
                onDeleteAllClicked,
                onThemeChangeClick
            )
        }

        else -> {
            SearchAppbar(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onSearchCloseClicked = onSearchCloseClicked
            )
        }

    }
}

@Composable
private fun TaskListDefaultActions(
    isDarkModeEnabled: Boolean,
    shouldShowTopBarActions: Boolean,
    onSearchIconClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
    onThemeChangeClick: (Boolean) -> Unit
) {
    if (shouldShowTopBarActions) {
        SearchAction(onSearchIconClicked)
        SortAction(onSortClicked)
        DeleteAllAction(onDeleteAllClicked)
    }
    ThemeAction(isDarkModeEnabled = isDarkModeEnabled, onThemeChangeClick = onThemeChangeClick)

}


@Composable
fun SearchAction(onSearchIconClicked: () -> Unit) {
    IconButton(onClick = onSearchIconClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Tasks Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}


@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {

    var isExpanded by remember { mutableStateOf(false) }

    IconButton(onClick = {
        isExpanded = true
    }) {
        Icon(
            imageVector = Icons.Filled.FilterList,
            contentDescription = "Sort Task Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.LOW)
                isExpanded = false
            }) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.HIGH)
                isExpanded = false
            }) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.NONE)
                isExpanded = false
            }) {
                PriorityItem(priority = Priority.NONE)
            }
        }
    }
}

@Composable
fun DeleteAllAction(onDeleteAllClicked: () -> Unit) {

    IconButton(onClick = {
        onDeleteAllClicked()
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Sort Task Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddTaskAction(addActionClicked: () -> Unit, isEnabled: Boolean) {
    IconButton(onClick = {
        addActionClicked()
    }, enabled = isEnabled) {
        Icon(
            imageVector = Icons.Filled.AddTask,
            contentDescription = "Add Task Button",
            tint = if (isEnabled) MaterialTheme.colors.topAppBarContentColor
            else MaterialTheme.colors.topAppBarContentColor.copy(alpha = ContentAlpha.disabled)
        )
    }
}

@Composable
fun DeleteTaskAction(deleteActionClicked: () -> Unit) {
    IconButton(onClick = {
        deleteActionClicked()
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Task Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ThemeAction(isDarkModeEnabled: Boolean, onThemeChangeClick: (Boolean) -> Unit) {
    IconButton(onClick = {
        onThemeChangeClick(!isDarkModeEnabled)
    }) {
        Icon(
            imageVector = if (isDarkModeEnabled) Icons.Filled.ModeNight else Icons.Filled.LightMode,
            contentDescription = "Themechange Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}