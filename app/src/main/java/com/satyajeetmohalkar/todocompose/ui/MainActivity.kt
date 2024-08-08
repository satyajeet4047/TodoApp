package com.satyajeetmohalkar.todocompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.satyajeetmohalkar.todocompose.navigation.TodoTasksNavigation
import com.satyajeetmohalkar.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoComposeTheme {
                TodoTasksNavigation()
            }
        }
    }
}
