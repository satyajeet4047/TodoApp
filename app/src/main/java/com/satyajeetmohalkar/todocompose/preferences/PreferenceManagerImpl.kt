package com.satyajeetmohalkar.todocompose.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.todoPreferenceStore by preferencesDataStore("todo_preferences")

class PreferenceManagerImpl @Inject constructor(
    private val dataStore : DataStore<Preferences>
) : PreferenceManager {

    override val uiModeFlow: Flow<Boolean> = dataStore.data
        .catch {
            it.printStackTrace()
            emit(emptyPreferences())
        }.map { pref ->
            pref[IS_DARK_MODE] ?: false
        }

    override suspend fun setDarkMode(isDarkModeEnabled: Boolean) {
        dataStore.edit { pref ->
            pref[IS_DARK_MODE] = isDarkModeEnabled
        }
    }


    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
    }
}