package com.evaluation.todo.repository

import arrow.core.Either
import com.evaluation.todo.dao.TaskDao
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.ErrorCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

/**
 * Description
 *
 * @author HY Lin
 * @since 2022/5/7 11:08 AM
 */
@Service
class TaskRepository(@Autowired private val taskDao: TaskDao) {

    fun findTaskById(id: Long): Either<ErrorCode, Task> {
        val task = taskDao.findById(id)
        return if (task.isEmpty) Either.Left(ErrorCode.TASK_NOT_FOUND) else Either.Right(task.get())
    }

    fun saveTask(task: Task): Either<ErrorCode, Task> {
        return try {
            Either.Right(taskDao.save(task))
        } catch (e: Exception) {
            Either.Left(ErrorCode.INSERT_FAILED)
        }
    }

    fun deleteTask(id: Long): Either<ErrorCode, Long> {
        return try {
            taskDao.deleteById(id)
            return Either.Right(id)
        } catch (e: EmptyResultDataAccessException) {
            Either.Left(ErrorCode.TASK_NOT_FOUND)
        }
    }

}