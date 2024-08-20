package com.satyajeetmohalkar.todocompose.utils

import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask

object TasksTestTestData {

    fun getTaskList(searchQuery : String = "") = listOf(
        TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ), TodoTask(
            id = 2,
            title = "Task 2",
            description = "This is a task 2 description",
            priority = Priority.LOW
        ), TodoTask(
            id = 3,
            title = "Task 3",
            description = "This is a task 3 description",
            priority = Priority.LOW
        ), TodoTask(
            id = 4,
            title = "Task 4",
            description = "This is a task 4 description",
            priority = Priority.MEDIUM
        ),

        TodoTask(
            id = 5,
            title = "Task 5",
            description = "This is a task 5 description",
            priority = Priority.MEDIUM
        )
    ).search(searchQuery)

    fun getLowToHighSortedTaskList(searchQuery: String ="") = listOf(
        TodoTask(
            id = 2,
            title = "Task 2",
            description = "This is a task 2 description",
            priority = Priority.LOW
        ), TodoTask(
            id = 3,
            title = "Task 3",
            description = "This is a task 3 description with common search keyword",
            priority = Priority.LOW
        ), TodoTask(
            id = 4,
            title = "Task 4",
            description = "This is a task 4 description",
            priority = Priority.MEDIUM
        ),
        TodoTask(
            id = 5,
            title = "Task 5",
            description = "This is a task 5 description with common search keyword",
            priority = Priority.MEDIUM
        ), TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ),
        TodoTask(
            id = 6,
            title = "Task 1",
            description = "This is a task 1 description similar to Task 2",
            priority = Priority.HIGH
        )
    ).search(searchQuery)

    fun getHighToLowSortedTaskList(searchQuery: String = "") = listOf(
        TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ),
        TodoTask(
            id = 4,
            title = "Task 4",
            description = "This is a task 4 description with common search keyword",
            priority = Priority.MEDIUM
        ),
        TodoTask(
            id = 6,
            title = "Task 6",
            description = "This is a task 6 duplicate to Task 2 description with common search keyword",
            priority = Priority.MEDIUM
        ),
        TodoTask(
            id = 5,
            title = "Task 5",
            description = "This is a task 5 description",
            priority = Priority.MEDIUM
        ),
        TodoTask(
            id = 2,
            title = "Task 2",
            description = "This is a task 2 description with common search keyword",
            priority = Priority.LOW
        ), TodoTask(
            id = 3,
            title = "Task 3",
            description = "This is a task 3 description",
            priority = Priority.LOW
        )
    ).search(searchQuery)

    fun List<TodoTask>.search(searchQuery: String) = filter {
        it.title.contains(searchQuery) || it.description.contains(searchQuery)
    }
}

