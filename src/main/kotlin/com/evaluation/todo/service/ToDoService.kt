package com.evaluation.todo.service

import arrow.core.Either
import arrow.core.continuations.either
import com.evaluation.todo.dao.TaskDao
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.ErrorCode
import com.evaluation.todo.model.Priority
import com.evaluation.todo.repository.TaskRepository
import com.evaluation.todo.util.BeanUtils
import com.evaluation.todo.vo.TaskVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Handling to-do service
 *
 * @author HY Lin
 **/
@Service
class ToDoService(
    @Autowired private val taskDao: TaskDao,
    @Autowired private val taskRepository: TaskRepository
) {

    /**
     * 查詢
     */
    fun getTasksByStatus(status: Int?): List<TaskVO>? {
        val taskList = status?.let { taskDao.findByStatusOrderByWeightDescIdDesc(status) }
            ?: taskDao.findAllByOrderByWeightDescIdDesc()
        return BeanUtils.copyListProperties(taskList) { TaskVO() }
    }

    /**
     * 新增
     */
    fun addTask(task: Task): Either<ErrorCode, Task> {
        return taskRepository.saveTask(task)
    }

    /**
     * 修改
     */
    fun updateTaskContent(task: Task): Either<ErrorCode, Task> {
        return either.eager {
            val target = taskRepository.findTaskById(task.id).bind()
            taskRepository.saveTask(target.apply { this.content = task.content }).bind()
        }
    }

    /**
     * 修改狀態
     */
    fun updateTaskStatus(id: Long): Either<ErrorCode, Task> {
        return either.eager {
            val target = taskRepository.findTaskById(id).bind()
            taskRepository.saveTask(target.apply { this.status = this.status xor 1 }).bind()
        }
    }

    /**
     * 更新優先度
     */
    fun updateWeight(id: Long, priority: Priority): Either<ErrorCode, Task> {
        return either.eager {
            val target = taskRepository.findTaskById(id).bind()
            taskRepository.saveTask(target.apply { this.weight = priority.weight }).bind()
        }
    }

    /**
     * 刪除指定任務
     */
    fun deleteTask(id: Long): Either<ErrorCode, Long> {
        return taskRepository.deleteTask(id)
    }
}