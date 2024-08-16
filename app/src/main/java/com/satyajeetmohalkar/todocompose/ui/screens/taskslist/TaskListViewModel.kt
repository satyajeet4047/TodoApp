package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.preferences.PreferenceManager
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.state.TaskListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class,ExperimentalCoroutinesApi::class)
@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository, private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _searchBarState = MutableStateFlow(SearchBarState.CLOSED)
    val searchBarState: StateFlow<SearchBarState> = _searchBarState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val debouncedSearchQuery = searchQuery.debounce(300L)

    private val _sortFilter = MutableStateFlow(Priority.NONE)
    private val sortFilter: StateFlow<Priority> = _sortFilter

    private val tasks: Flow<List<TodoTask>> = combine(debouncedSearchQuery, sortFilter) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { params ->
        val tasksFlow = when (params.second) {
            Priority.LOW -> {
                taskRepository.getSortedTasksByLowToHigh("%${params.first}%")
            }
            Priority.HIGH -> {
                taskRepository.getSortedTasksByHighToLow("%${params.first}%")
            }
            else -> {
                taskRepository.searchTasks("%${params.first}%")
            }
        }
        tasksFlow
    }.flowOn(Dispatchers.IO)


    private val _tasksListUiState =
        MutableStateFlow(TaskListUiState(isLoading = true, tasks = emptyList()))
    val tasksListUiState: StateFlow<TaskListUiState> = _tasksListUiState

    private val _darkMode = MutableStateFlow(false)
    val darkMode: StateFlow<Boolean> = _darkMode

    init {
        observeTasks()
        observerUiMode()
    }

    private fun observerUiMode() {
        preferenceManager.uiModeFlow.onEach { isDarkModeEnabled ->
                _darkMode.update {
                    isDarkModeEnabled
                }
            }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun onSearchQueryChange(searchQueryText: String) {
        _searchQuery.update {
            searchQueryText
        }
    }

    fun onSearchCloseClicked() {
        _searchBarState.update {
            SearchBarState.CLOSED
        }
    }

    fun onSearchIconClicked() {
        _searchBarState.update {
            SearchBarState.OPENED
        }
    }

    private fun observeTasks() {
       tasks
           .distinctUntilChanged()
           .onStart {
           _tasksListUiState.update {
               it.copy(
                   isLoading = true, tasks = emptyList()
               )
           }
       }.onEach { tasks ->
           _tasksListUiState.update {
               it.copy(
                   isLoading = false, tasks = tasks
               )
           }
       }
           .flowOn(Dispatchers.IO)
           .launchIn(viewModelScope)
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            taskRepository.deleteAllTasks()
        }
    }

    fun onThemeChange(isDarkModeEnabled: Boolean) {
        viewModelScope.launch {
            preferenceManager.setDarkMode(isDarkModeEnabled)
        }
    }

    fun onSortTasks(priority: Priority) {
        _sortFilter.update {
            priority
        }
    }

}