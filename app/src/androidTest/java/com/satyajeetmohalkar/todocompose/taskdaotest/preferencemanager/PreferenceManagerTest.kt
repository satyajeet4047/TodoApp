package com.satyajeetmohalkar.todocompose.taskdaotest.preferencemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManagerImpl
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PreferenceManagerTest {


    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

    //This allow us to delete the pref file after test is executed
    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testDataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = testCoroutineScope,
            produceFile = { tmpFolder.newFile("uiMode.preferences_pb") }
        )

    private val preferenceManager = PreferenceManagerImpl(testDataStore)


    @Test
    fun test_initial_ui_mode_value() = runTest {
        val currentDarkMode = preferenceManager.uiModeFlow.first()
        TestCase.assertEquals(false, currentDarkMode)
    }

    @Test
    fun given_ui_mode_as_true_should_return_true() = runTest {
        preferenceManager.setDarkMode(true)
        TestCase.assertEquals(true, preferenceManager.uiModeFlow.first())
    }

    @Test
    fun given_ui_mode_as_false_should_return_false() = runTest {
        preferenceManager.setDarkMode(false)
        TestCase.assertEquals(false, preferenceManager.uiModeFlow.first())
    }

}