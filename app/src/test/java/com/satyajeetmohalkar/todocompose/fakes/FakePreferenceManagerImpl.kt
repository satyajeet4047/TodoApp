package com.satyajeetmohalkar.todocompose.fakes

import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakePreferenceManagerImpl : PreferenceManager {

    override val uiModeFlow: Flow<Boolean>
        get() = flowOf(true)

    override suspend fun setDarkMode(isDarkModeEnabled: Boolean) {
        println(" Set DarkMode Preference called")
    }
}