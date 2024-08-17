package com.satyajeetmohalkar.todocompose.taskdaotest


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.satyajeetmohalkar.todocompose.data.models.Priority
import com.satyajeetmohalkar.todocompose.data.models.TodoTask
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TodoTaskDaoTest : TaskTestDatabase() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun given_task_should_insert_successfully() = runTest {

        taskDao.insertTask(TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ))

       val task =  taskDao.getTodoTask(1).first()

       assertEquals(1, task.id)
       assertEquals("Task 1", task.title)
       assertEquals("This is a task 1 description", task.description)
       assertEquals(Priority.HIGH, task.priority)

    }


    @Test
    fun given_task_with_existing_id_should_update_successfully() = runTest {

        taskDao.insertTask(TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ))

        taskDao.insertTask(TodoTask(
            id = 1,
            title = "Task 1 updated",
            description = "This is a task 1 description updated",
            priority = Priority.MEDIUM
        ))

        val task =  taskDao.getAllTodoTasks().first()
        assertEquals(1, task.size)

        assertEquals(1, task[0].id)
        assertEquals("Task 1 updated", task[0].title)
        assertEquals("This is a task 1 description updated", task[0].description)
        assertEquals(Priority.MEDIUM, task[0].priority)

    }

    @Test
    fun given_task_with_task_id_should_delete_successfully() = runTest {

        taskDao.insertTask(TodoTask(
            id = 1,
            title = "Task 1",
            description = "This is a task 1 description",
            priority = Priority.HIGH
        ))

        taskDao.deleteTask(1)
        val task =  taskDao.getTodoTask(1).firstOrNull()
        assertEquals(null, task)

    }

    @Test
    fun given_all_tasks_should_delete_all_successfully() = runTest {

        taskDao.insertTask(
            TodoTask(
                id = 1,
                title = "Task 1",
                description = "This is a task 1 description",
                priority = Priority.HIGH
            )
        )

        taskDao.insertTask(
            TodoTask(
                id = 2,
                title = "Task 2",
                description = "This is a task 2 description",
                priority = Priority.HIGH
            )
        )

        taskDao.deleteAllTasks()
        val task = taskDao.getAllTodoTasks().firstOrNull()
        assertEquals(0, task?.size)
    }

    @Test
    fun given_search_input_should_return_task_successfully() = runTest {
     taskDao.insertTask(TodoTask(
         id = 1,
         title = "Task 1",
         description = "This is a task 1 description",
         priority = Priority.LOW
     ))
     taskDao.insertTask(TodoTask(
         id = 2,
         title = "Task 2",
         description = "This is a task 2 description",
         priority = Priority.HIGH
     ))
     taskDao.insertTask(TodoTask(
         id = 3,
         title = "Task 3",
         description = "This is a task 3 description",
         priority = Priority.HIGH
     ))
     taskDao.insertTask(TodoTask(
         id = 4,
         title = "Task 4",
         description = "This is a task 4 description",
         priority = Priority.MEDIUM
     ))

     val task =  taskDao.searchTodoTask("task 1").first()

     assertEquals(1, task[0].id)
     assertEquals("Task 1", task[0].title)
     assertEquals("This is a task 1 description", task[0].description)
     assertEquals(Priority.LOW, task[0].priority)
    }


    @Test
    fun given_sort_low_to_high_should_return_sorted_task_list_successfully() = runTest {
         getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val expectedSortedTaskList = getLowToHighSortedTaskList()

        val task =  taskDao.sortTasksByLowToHigh("").first()

        task.forEachIndexed { index, todoTask ->
            assertEquals(expectedSortedTaskList[index].id, todoTask.id)
            assertEquals(expectedSortedTaskList[index].title, todoTask.title)
            assertEquals(expectedSortedTaskList[index].description, todoTask.description)
            assertEquals(expectedSortedTaskList[index].priority, todoTask.priority)
        }
    }

    @Test
    fun given_sort_high_to_low_should_return_sorted_task_list_successfully() = runTest {
        getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val expectedSortedTaskList = getHighToLowSortedTaskList()
        val task =  taskDao.sortTasksByHighToLow("").first()

        task.forEachIndexed { index, todoTask ->
            assertEquals(expectedSortedTaskList[index].id, todoTask.id)
            assertEquals(expectedSortedTaskList[index].title, todoTask.title)
            assertEquals(expectedSortedTaskList[index].description, todoTask.description)
            assertEquals(expectedSortedTaskList[index].priority, todoTask.priority)
        }
    }


    @Test
    fun given_sort_low_to_high_with_search_query_should_return_sorted_task_list_successfully() = runTest {
        getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val expectedSortedTaskList = getLowToHighSortedTaskList().filter {
            it.description.contains("with common search keyword")
        }

        val task =  taskDao.sortTasksByLowToHigh("search keyword").first()

        task.forEachIndexed { index, todoTask ->
            assertEquals(expectedSortedTaskList[index].id, todoTask.id)
            assertEquals(expectedSortedTaskList[index].title, todoTask.title)
            assertEquals(expectedSortedTaskList[index].description, todoTask.description)
            assertEquals(expectedSortedTaskList[index].priority, todoTask.priority)
        }
    }


    @Test
    fun given_sort_low_to_high_with_search_query_should_return_empty_task_list_successfully() = runTest {
        getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val task =  taskDao.sortTasksByLowToHigh("abcd").first()

        assertEquals(0, task.size)
    }

    @Test
    fun given_sort_high_to_low_with_search_query_should_return_sorted_task_list_successfully() = runTest {
        getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val expectedSortedTaskList = getHighToLowSortedTaskList().filter {
            it.description.contains("with common search keyword")
        }

        val task =  taskDao.sortTasksByHighToLow("search keyword").first()

        task.forEachIndexed { index, todoTask ->
            assertEquals(expectedSortedTaskList[index].id, todoTask.id)
            assertEquals(expectedSortedTaskList[index].title, todoTask.title)
            assertEquals(expectedSortedTaskList[index].description, todoTask.description)
            assertEquals(expectedSortedTaskList[index].priority, todoTask.priority)
        }
    }

    @Test
    fun given_sort_high_to_low_with_search_query_should_return_empty_task_list_successfully() = runTest {
        getTaskList().onEach {
            taskDao.insertTask(it)
        }

        val task =  taskDao.sortTasksByLowToHigh("abcd").first()

        assertEquals(0, task.size)
    }

    private fun getTaskList() = listOf(
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

    )

    private fun getLowToHighSortedTaskList() = listOf(
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
        )
    )

    private fun getHighToLowSortedTaskList() = listOf(
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
    )
}