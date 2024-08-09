package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.ui.theme.taskItemBackgroundColor
import com.satyajeetmohalkar.todocompose.ui.theme.taskItemTextColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    todoTask: TodoTask, navigateToTaskDetails: (Int) -> Unit
) {

    Card(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RoundedCornerShape(4.dp),
        onClick = {
            navigateToTaskDetails(todoTask.id)
        }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(end = 8.dp),
                    text = todoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Canvas(
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    drawCircle(todoTask.priority.color)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                text = todoTask.description,
                style = TextStyle(
                    color = MaterialTheme.colors.taskItemTextColor,
                    fontSize = MaterialTheme.typography.subtitle2.fontSize
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(todoTask = TodoTask(
        id = 0,
        title = "My Note",
        description = "This is a dummy description added for a test purpose. Repeating the first line as this is a dummy description added for a test purpose",
        priority = Priority.HIGH
    ), navigateToTaskDetails = {})
}