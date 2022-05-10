package com.evaluation.todo.vo

/**
 * task view for FE
 *
 * @author HY Lin
 */
data class TaskVO(
    val id: Long = 0L,
    var title: String = "",
    var content: String = "",
    var status: Int = 0,
    var weight: Int = 0,
)