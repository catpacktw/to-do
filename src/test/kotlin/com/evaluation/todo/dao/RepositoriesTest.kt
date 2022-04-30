package com.evaluation.todo.dao

import com.evaluation.todo.entity.Task
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 * Test for DAO
 * @author HY Lin
 **/
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTest @Autowired constructor(
    val taskDao: TaskDao
) {

    @Test
    fun testTaskDaoCrud() {
        val task = Task(0L, "dinner", "pasta,fish,coca cola", 0, 0)
        taskDao.save(task)
        println("loaded task $task")
        val found = taskDao.findById(task.id).get()
        println("found task $found")
        taskDao.deleteById(task.id)
        assertThat(found.title).isEqualTo(task.title)
        assertThat(found.content).isEqualTo(task.content)
        assertThat(found.status).isEqualTo(task.status)
        assertThat(found.weight).isEqualTo(task.weight)
    }

}