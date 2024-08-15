package com.satyajeetmohalkar.todocompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satyajeetmohalkar.todocompose.navigation.TodoTasksNavigation
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import com.satyajeetmohalkar.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoMain()
        }
    }

    @Composable
    private fun TodoMain() {
        val isDarkModeEnabled by rememberDarkMode()
        TodoComposeTheme(
            darkTheme = isDarkModeEnabled
        ) {
            TodoTasksNavigation()
        }
    }

    @Composable
    fun rememberDarkMode() : State<Boolean> {
       return preferenceManager.uiModeFlow.collectAsStateWithLifecycle(
            initialValue = isSystemInDarkTheme()
       )
    }

}
