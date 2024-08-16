package com.satyajeetmohalkar.todocompose.ui.screens.taskdetails

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.ui.components.dialog.DeleteTaskConfirmationDialog
import com.satyajeetmohalkar.todocompose.ui.components.dropdown.PriorityDropDown
import com.satyajeetmohalkar.todocompose.ui.components.todoappbar.TaskAppBar
import com.satyajeetmohalkar.todocompose.ui.theme.Purple40


@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
    onNavigateUp: () -> Unit
) {
    val taskUiState by taskViewModel.taskUiState.collectAsState()
    var shouldShowDeleteConfirmationDialog by remember { mutableStateOf(false) }


    val context = LocalContext.current
    TaskContent(
        taskId = taskUiState.taskId,
        title = taskUiState.title,
        description = taskUiState.description,
        priority = taskUiState.priority,
        isLoading = taskUiState.isLoading,
        onNavigateUp = onNavigateUp,
        onTitleChange = taskViewModel::onTitleChange,
        onDescriptionChange = taskViewModel::onDescriptionChange,
        onPriorityChange = taskViewModel::onPriorityChange,
        addActionClicked = {
            if(taskViewModel.isValidTaskData()) {
                taskViewModel.addTask()
                onNavigateUp()
            } else {
                showToast(context,"Please enter valid task details...")
            }

        },
        deleteActionClicked = {  shouldShowDeleteConfirmationDialog = true},
        isValidTitle = taskUiState.isValidTitle,
        isValidDescription = taskUiState.isValidDescription,
        onDeleteConfirm = {
            taskViewModel.deleteTask()
            onNavigateUp()
        },
        onDismiss = {
            shouldShowDeleteConfirmationDialog = false
        },
        shouldShowDeleteConfirmationDialog = shouldShowDeleteConfirmationDialog
    )
}

@Composable
fun TaskContent(
    taskId: Int,
    title: String?,
    description: String?,
    priority: Priority,
    isLoading: Boolean,
    onNavigateUp: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    addActionClicked: () -> Unit,
    deleteActionClicked: () -> Unit,
    isValidTitle: Boolean,
    isValidDescription: Boolean,
    onDeleteConfirm: () -> Unit,
    onDismiss: () -> Unit,
    shouldShowDeleteConfirmationDialog: Boolean
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                taskId = taskId,
                title = title,
                onNavigateUp = onNavigateUp,
                addActionClicked = addActionClicked,
                deleteActionClicked = deleteActionClicked
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(verticalArrangement = Arrangement.Center){

                if (isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 4.dp,
                        color = Purple40,
                        modifier = Modifier.size(36.dp)
                    )
                } else {
                    TaskScreenContent(
                        title = title,
                        description = description,
                        priority = priority,
                        onTitleChange = onTitleChange,
                        onDescriptionChange = onDescriptionChange,
                        onPriorityChange = onPriorityChange,
                        isValidTitle = isValidTitle,
                        isValidDescription = isValidDescription
                    )
                }
            }
        }

    }

    DeleteTaskConfirmationDialog(title ?: "", description ?: "",shouldShowDeleteConfirmationDialog, onDeleteConfirm, onDismiss)
}


@Composable
fun TaskScreenContent(
    title: String?,
    description: String?,
    priority: Priority,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    isValidTitle : Boolean,
    isValidDescription : Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.takeIf { it != "Add Task" } ?: "",
            onValueChange = { newValue ->
                onTitleChange(newValue)
            },
            singleLine = true,
            maxLines = 1,
            label = {
                Text(text = "Title")
            },
            textStyle = MaterialTheme.typography.body1,
            isError = !isValidTitle
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp),
            color = MaterialTheme.colors.surface
        )

        PriorityDropDown(currentPriority = priority, onPriorityClicked = onPriorityChange)

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp),
            color = MaterialTheme.colors.surface
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description ?: "",
            onValueChange = { newValue ->
                onDescriptionChange(newValue)
            },
            label = {
                Text(text = "Description")
            },
            textStyle = MaterialTheme.typography.body1,
            isError = !isValidDescription
        )
    }

}

fun showToast(context : Context, message : String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

