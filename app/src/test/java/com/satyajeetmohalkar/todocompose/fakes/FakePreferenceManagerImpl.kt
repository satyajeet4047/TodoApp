package com.satyajeetmohalkar.todocompose.fakes

import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakePreferenceManagerImpl : PreferenceManager {

    private val _uiModeFlow = MutableSharedFlow<Boolean>(replay = 0)

    override val uiModeFlow = _uiModeFlow

    override suspend fun setDarkMode(isDarkModeEnabled: Boolean) {
        _uiModeFlow.emit(isDarkModeEnabled)
    }


}