package com.satyajeetmohalkar.todocompose.ui.screens.taskslist

import androidx.lifecycle.ViewModel
import com.satyajeetmohalkar.todocompose.data.local.repository.TodoRepository
import com.satyajeetmohalkar.todocompose.ui.state.SearchBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _searchBarState = MutableStateFlow(SearchBarState.CLOSED)
    val searchBarState : StateFlow<SearchBarState> = _searchBarState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery


    fun onSearchQueryChange(searchQueryText : String) {
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

}