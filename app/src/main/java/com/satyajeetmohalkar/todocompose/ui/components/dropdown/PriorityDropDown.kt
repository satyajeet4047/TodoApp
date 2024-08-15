package com.satyajeetmohalkar.todocompose.ui.components.dropdown

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.utils.Constants
import com.satyajeetmohalkar.todocompose.utils.rememberComposeImmutableList
import okhttp3.internal.filterList


@Composable
fun PriorityDropDown(
    currentPriority: Priority,
    onPriorityClicked: (Priority) -> Unit
) {

    var isExpanded by remember { mutableStateOf(false) }

    val rotateAngle by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    val rememberDropDownMenuItems by rememberComposeImmutableList{ Constants.PRIORITY_LIST }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable {
                isExpanded = !isExpanded
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = currentPriority.color)
        }
        Text(
            modifier = Modifier
                .weight(0.95f)
                .padding(horizontal = 12.dp),
            text = currentPriority.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(rotateAngle), onClick = { isExpanded != isExpanded }) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Priority DropDown")
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.85f),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            Log.d("satya", "Adding drop down items")
            rememberDropDownMenuItems.forEach { priority ->
                Log.d("satya", priority.name)
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onPriorityClicked(priority)
                    }) {
                    PriorityItem(priority = priority)
                }
            }
        }
    }
}


@Preview
@Composable
fun PriorityDropDownPreview() {
    PriorityDropDown(currentPriority = Priority.MEDIUM, onPriorityClicked = {})
}