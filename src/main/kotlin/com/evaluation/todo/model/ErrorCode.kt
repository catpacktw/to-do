package com.evaluation.todo.model

/**
 * 優先度 ENUM
 *
 * @author HY Lin
 * @since 2022/4/30 5:25 PM
 */
enum class ErrorCode(val code: Int, val desc: String) {
    /**
     * 新增失敗
     */
    TASK_NOT_FOUND(1, "Task not found."),
    INSERT_FAILED(2, "Adding task failed."),

}