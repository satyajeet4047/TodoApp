package com.satyajeetmohalkar.todocompose.ui.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirmedYes: () -> Unit,
    onDismissed: () -> Unit
) {
    var isDismissed by remember { mutableStateOf(false) }

    if (!isDismissed) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth(),
            onDismissRequest = onDismissed,
            title = {
                Text(text = title,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    ))
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            isDismissed = true
                        },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                    ) {
                        Text(
                            text = "No",
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = {
                            onConfirmedYes()
                            isDismissed = true
                        },
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Text(
                            text = "Yes",
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun DeleteTaskConfirmationDialog(title : String, message: String, showDialog : Boolean, onDeleteConfirm : () -> Unit, onDismissed: () -> Unit) {
    if(showDialog){
        ConfirmationDialog(
            title =title,
            message = message,
            onConfirmedYes = onDeleteConfirm,
            onDismissed = onDismissed)
    }
}