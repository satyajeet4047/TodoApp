package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import com.satyajeetmohalkar.todocompose.ui.state.TaskListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _searchBarState = MutableStateFlow(SearchBarState.CLOSED)
    val searchBarState: StateFlow<SearchBarState> = _searchBarState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _tasks: MutableStateFlow<List<TodoTask>> = MutableStateFlow(emptyList())
    private val tasks: StateFlow<List<TodoTask>> = _tasks

    private val _tasksListUiState =
        MutableStateFlow(TaskListUiState(isLoading = true, tasks = emptyList()))
    val tasksListUiState: StateFlow<TaskListUiState> = _tasksListUiState

    init {
        observeTasks()
        searchTasks()
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
        taskRepository.getAllTasks().distinctUntilChanged().onEach { tasks ->
                _tasks.update {
                    tasks
                }
            }.launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class)
    private fun searchTasks() {
        searchQuery.debounce(300L).combine(tasks) { query, tasks ->
                if (query.isEmpty()) {
                    _tasksListUiState.update {
                        it.copy(
                            isLoading = false, tasks = tasks
                        )
                    }
                } else {
                    _tasksListUiState.update {
                        it.copy(isLoading = false, tasks = tasks.filter { task ->
                            task.title.contains(query) || task.description.contains(query)
                        })
                    }
                }
            }.flowOn(Dispatchers.IO).onStart {
                _tasksListUiState.update {
                    it.copy(
                        isLoading = true, tasks = emptyList()
                    )
                }
            }.launchIn(viewModelScope)
    }

}