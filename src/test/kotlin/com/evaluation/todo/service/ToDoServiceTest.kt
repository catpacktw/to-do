package com.evaluation.todo.service

import com.evaluation.todo.dao.TaskDao
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.Priority
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * test case
 *
 * @author HY Lin
 * @since 2022/4/30 8:04 PM
 */
@SpringBootTest
internal class ToDoServiceTest @Autowired constructor(val toDoService: ToDoService, val taskDao: TaskDao) {

    val task = Task(0L, "dinner", "pasta,fish,coca cola", 0, 0)

    @BeforeEach
    fun init() {
        taskDao.save(task)
    }

    @AfterEach
    fun end() {
        taskDao.delete(task)
    }

    @Test
    fun getTasksByStatus() {
        val result = toDoService.getTasksByStatus(0)
        assertNotNull(result)
    }

    @Test
    fun updateTask() {
        val before = Task(task.id, "launch", "chicken, fries", 1, 0)
        val after = toDoService.updateTask(before)
        assertEquals(before.id, after.id)
        assertEquals(before.content, after.content)
    }

    @Test
    fun updateWeight() {
        val before = taskDao.findById(task.id).get()
        val after = toDoService.updateWeight(task.id, Priority.REDUCE)
        assertTrue(after.weight <= before.weight)
    }

}