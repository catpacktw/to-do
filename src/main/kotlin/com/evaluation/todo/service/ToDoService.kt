package com.evaluation.todo.service

import com.evaluation.todo.dao.TaskDao
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.Priority
import com.evaluation.todo.model.ToDoException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

/**
 * Handling to-do service
 *
 * @author HY Lin
 **/
@Service
class ToDoService(@Autowired private val taskDao: TaskDao) {

    /**
     * 查詢
     */
    fun getTasksByStatus(status: Int?): List<Task>? {
        return status?.takeIf { true }?.let { taskDao.findByStatusOrderByWeightDesc(status) } ?: return taskDao.findAllByOrderByWeightDesc()
    }

    /**
     * 新增
     */
    fun addTask(task: Task): Task {
        //給予最高權重
        task.weight = getMaxWeight()
        val result = taskDao.save(task)
        result.takeIf { true }?.let {
            return result
        } ?: throw ToDoException.of("Adding task failed")
    }

    /**
     * 修改狀態
     */
    fun updateTaskStatus(id: Long): Task {
        val target = taskDao.findById(id).get()
        return taskDao.save(target.apply { this.status = this.status xor 1 })
    }

    /**
     * 修改
     */
    fun updateTask(task: Task): Task {
        val target = taskDao.findById(task.id).get()
        target.title = task.title
        target.content = task.content
        target.status = task.status
        return taskDao.save(target)
    }

    /**
     * 更新排序
     */
    fun updateWeight(id: Long, priority: Priority): Task {
        val task = taskDao.findById(id).get()
        task.takeIf { Priority.INCREASE == priority }?.let {
            //return if there is no more weight task
            val nextOne = taskDao.findByWeight(task.weight + 1) ?: return task
            nextOne.weight -= 1
            task.weight += 1
            taskDao.saveAll(listOf(task, nextOne))
        } ?: run {
            //return if there is no less weight task
            val nextOne = taskDao.findByWeight(task.weight - 1) ?: return task
            nextOne.weight += 1
            task.weight -= 1
            taskDao.saveAll(listOf(task, nextOne))
        }
        return task
    }

    fun deleteTask(id: Long) {
        try {
            taskDao.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            throw ToDoException.of("Task id doesn't exist")
        }
    }

    /**
     * 得到當前最高權重
     */
    private fun getMaxWeight(): Int {
        val task = taskDao.findTopByOrderByWeightDesc()
        task?.takeIf { true }?.let {
            return task.weight + 1
        } ?: return 0
    }
}