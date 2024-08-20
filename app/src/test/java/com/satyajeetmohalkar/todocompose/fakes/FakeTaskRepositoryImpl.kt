package com.satyajeetmohalkar.todocompose.fakes

import com.satyajeetmohalkar.todocompose.data.local.repository.TaskRepository
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import com.satyajeetmohalkar.todocompose.utils.TasksTestTestData.getHighToLowSortedTaskList
import com.satyajeetmohalkar.todocompose.utils.TasksTestTestData.getLowToHighSortedTaskList
import com.satyajeetmohalkar.todocompose.utils.TasksTestTestData.getTaskList
import com.satyajeetmohalkar.todocompose.utils.TasksTestTestData.search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeTaskRepositoryImpl : TaskRepository {
    private val tasksFlow = MutableSharedFlow<List<TodoTask>>(replay = 0)

    override fun getAllTasks(): Flow<List<TodoTask>> = tasksFlow

    override fun getTask(taskId: Int): Flow<TodoTask> {
        return flow {
            emit(
                TodoTask(
                    id = 2,
                    title = "Task 2",
                    description = "This is a task 2 description",
                    priority = Priority.LOW
                )
            )
        }
    }

    override fun getSortedTasksByLowToHigh(searchQuery: String) = tasksFlow

    override fun getSortedTasksByHighToLow(searchQuery: String) = tasksFlow

    override fun searchTasks(searchQuery: String) = tasksFlow


    override suspend fun addTask(task: TodoTask) {
    }

    override suspend fun update(task: TodoTask) {
    }

    override suspend fun deleteAllTasks() {
    }

    override suspend fun deleteTask(taskId: Int) {
    }


    suspend fun emitFlow(emitFLowType: EmitFLowType, searchQuery: String = "") {
        val taskList = when (emitFLowType) {
            EmitFLowType.SEARCH -> getTaskList().search(searchQuery)
            EmitFLowType.LOW_TO_HIGH -> getLowToHighSortedTaskList().search(searchQuery)
            EmitFLowType.HIGH_TO_LOW -> getHighToLowSortedTaskList().search(searchQuery)
        }
        tasksFlow.emit(taskList)
    }

}

enum class EmitFLowType {
    SEARCH, LOW_TO_HIGH, HIGH_TO_LOW
}

