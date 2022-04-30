package com.evaluation.todo.dto

import com.evaluation.todo.model.Priority

/**
 * dto for updating task priority
 *
 * @author HY Lin
 * @since 2022/4/30 7:14 PM
 */
class UpdateWeightDTO {
    val id: Long = 0L
    val operation: Priority = Priority.INCREASE
}