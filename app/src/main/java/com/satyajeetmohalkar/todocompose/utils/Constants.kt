package com.satyajeetmohalkar.todocompose.utils

import com.satyajeetmohalkar.todocompose.data.models.Priority

object Constants {

    const val TODO_DATABASE_NAME = "todo_database"
    const val TODO_TABLE_NAME = "todo_task_table"
    const val TODO_DATABASE_VERSION = 1

    val PRIORITY_LIST = listOf(Priority.HIGH, Priority.MEDIUM, Priority.LOW)

    const val MAX_TITLE_LENGTH = 20
}