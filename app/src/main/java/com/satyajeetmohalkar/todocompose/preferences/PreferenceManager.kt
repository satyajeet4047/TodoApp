package com.satyajeetmohalkar.todocompose.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {

    val uiModeFlow : Flow<Boolean>

    suspend fun setDarkMode(isDarkModeEnabled : Boolean)
}