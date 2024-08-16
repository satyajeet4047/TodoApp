package com.satyajeetmohalkar.todocompose.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreenViewProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.satyajeetmohalkar.todocompose.navigation.TodoTasksNavigation
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import com.satyajeetmohalkar.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Check for system dark theme and update the user preference accordingly.
        // Commented this code, as we don't want to explicitly override user preference.
        // This is added just for knowledge purpose.
//        val splashScreen = installSplashScreen()
//        splashScreen.setOnExitAnimationListener {
//            lifecycleScope.launch {
//                preferenceManager.setDarkMode(
//                    this@MainActivity.resources.configuration.uiMode and
//                            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
//                )
//            }
//            it.remove()
//        }
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
    fun rememberDarkMode(): State<Boolean> {
        return preferenceManager.uiModeFlow.collectAsStateWithLifecycle(
            initialValue = isSystemInDarkTheme()
        )
    }

}
