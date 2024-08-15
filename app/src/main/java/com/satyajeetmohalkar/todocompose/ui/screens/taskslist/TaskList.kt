package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.utils.ComposeImmutableList

@Composable
fun TaskList(tasks: ComposeImmutableList<TodoTask>, navigateToTaskDetails: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        modifier = Modifier.testTag("notesList")
    ) {
        items(
            items = tasks,
            itemContent = { taskItem ->
                TaskItem(
                    todoTask = taskItem,
                    navigateToTaskDetails = {
                        navigateToTaskDetails(taskItem.id)
                    }
                )
            },
            key = { Triple(it.id, it.title, it.description) }
        )
    }
}