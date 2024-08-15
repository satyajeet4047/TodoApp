package com.satyajeetmohalkar.todocompose.ui.components.todoappbar

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor

@Composable
fun TodoAppBar(
    title: String,
    searchBarState: SearchBarState,
    searchQuery: String,
    isDarkModeEnabled  : Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSearchCloseClicked: () -> Unit,
    onSearchIconClicked : () -> Unit,
    onDeleteAllClicked : () -> Unit,
    onThemeChangeClick : (Boolean) -> Unit
) {
    TopAppBar(
        title = {
            TopAppBarTitle(title)
        },
        actions = {
            TasksListActions(
                searchBarState = searchBarState,
                searchQuery = searchQuery,
                isDarkModeEnabled = isDarkModeEnabled,
                onSearchQueryChange = onSearchQueryChange,
                onSearchCloseClicked = onSearchCloseClicked,
                onSearchIconClicked = onSearchIconClicked,
                onSortClicked = {},
                onDeleteAllClicked = onDeleteAllClicked,
                onThemeChangeClick = onThemeChangeClick

            )
        },
        contentColor = MaterialTheme.colors.topAppBarContentColor,
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TodoAppBarPreview() {
    TodoAppBar(
        "Title",
        searchBarState = SearchBarState.CLOSED,
        searchQuery = "",
        isDarkModeEnabled = true,
        onSearchCloseClicked = {},
        onSearchQueryChange = {},
        onSearchIconClicked = {},
        onDeleteAllClicked = {},
        onThemeChangeClick = {})
}