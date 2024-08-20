package com.satyajeetmohalkar.todocompose.taskviewmodel

import com.satyajeetmohalkar.todocompose.utils.MainDispatcherTestRule
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.fakes.EmitFLowType
import com.satyajeetmohalkar.todocompose.fakes.FakePreferenceManagerImpl
import com.satyajeetmohalkar.todocompose.fakes.FakeTaskRepositoryImpl
import com.satyajeetmohalkar.todocompose.ui.screens.taskslist.TaskListViewModel
import com.satyajeetmohalkar.todocompose.utils.TasksTestTestData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskListViewModelTest {

    @get:Rule
    val mainDispatcherTestRule = MainDispatcherTestRule()

    private val fakeTaskRepo = FakeTaskRepositoryImpl()
    private val fakePreferenceManager = FakePreferenceManagerImpl()

    lateinit var viewModel : TaskListViewModel


    @Before
    fun setup() {
        viewModel = TaskListViewModel(fakeTaskRepo, fakePreferenceManager)
    }

    @Test
    fun test_initialTaskListViewState() = runTest {

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.SEARCH)
        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(5, tasksListState.tasks.size)
        assertEquals("Task 1", tasksListState.tasks[0].title)
        assertEquals("Task 5", tasksListState.tasks[4].title)
        assertEquals(5, tasksListState.tasks[4].id)

    }


    @Test
    fun test_searchTaskListViewState() = runTest {

        val searchQuery = "Task 2"

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        viewModel.onSearchQueryChange(searchQuery)
        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.SEARCH, searchQuery)

        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(1, tasksListState.tasks.size)
        assertEquals(TasksTestTestData.getTaskList(searchQuery), tasksListState.tasks)


    }


    @Test
    fun test_sortLowToHighTaskListViewState() = runTest {

        val searchQuery = "Task 2"

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        viewModel.onSearchQueryChange(searchQuery)
        viewModel.onSortTasks(Priority.LOW)
        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.LOW_TO_HIGH,searchQuery)

        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(2, tasksListState.tasks.size)
        assertEquals(TasksTestTestData.getLowToHighSortedTaskList(searchQuery), tasksListState.tasks)

    }

    @Test
    fun test_searchLowToHighTaskListViewState() = runTest {

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        viewModel.onSortTasks(Priority.LOW)
        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.LOW_TO_HIGH)

        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(6, tasksListState.tasks.size)
        assertEquals(TasksTestTestData.getLowToHighSortedTaskList(), tasksListState.tasks)

    }

    @Test
    fun test_sortHighToLowTaskListViewState() = runTest {

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        viewModel.onSortTasks(Priority.HIGH)
        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.HIGH_TO_LOW)

        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(6, tasksListState.tasks.size)
        assertEquals(TasksTestTestData.getHighToLowSortedTaskList(), tasksListState.tasks)

    }

    @Test
    fun test_searchAndSortHighToLowTaskListViewState() = runTest {

        val searchQuery = "Task 2"

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.tasksListUiState.collect()
        }

        val initialState =  viewModel.tasksListUiState.value
        assertEquals(true, initialState.isLoading)
        assertEquals(emptyList<TodoTask>(), initialState.tasks)

        viewModel.onSearchQueryChange(searchQuery)
        viewModel.onSortTasks(Priority.HIGH)
        advanceUntilIdle()
        fakeTaskRepo.emitFlow(EmitFLowType.HIGH_TO_LOW,searchQuery)

        val tasksListState = viewModel.tasksListUiState.value
        assertEquals(false, tasksListState.isLoading)
        assertEquals(2, tasksListState.tasks.size)
        assertEquals(TasksTestTestData.getHighToLowSortedTaskList(searchQuery), tasksListState.tasks)

    }


}