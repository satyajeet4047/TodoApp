package com.satyajeetmohalkar.todocompose.ui.components.dropdown

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.Priority

@Composable
fun PriorityItem(modifier: Modifier= Modifier, priority: Priority) {
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier =  Modifier
            .size(16.dp)) {
            drawCircle(priority.color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = priority.name,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface)
    }
    
}

@Preview
@Composable
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.HIGH)
}