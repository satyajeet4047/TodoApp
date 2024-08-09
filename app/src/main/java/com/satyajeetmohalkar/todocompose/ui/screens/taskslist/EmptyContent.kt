package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.R
import com.satyajeetmohalkar.todocompose.ui.theme.topAppBarContentColor


@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.ic_empty_task),
            contentDescription = "Empty Tasks")
        Text(
            text = "No Tasks Found",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold)
    }

}



@Preview
@Composable
fun EmptyContentPreview() {
    EmptyContent()
}