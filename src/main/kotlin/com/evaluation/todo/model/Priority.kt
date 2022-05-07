package com.evaluation.todo.model

/**
 * 優先度 ENUM
 *
 * @author HY Lin
 * @since 2022/4/30 5:25 PM
 */
enum class Priority(val weight: Int) {
    /**
     * 權重
     * 3-緊急, 2-高, 1-中, 0-低
     */
    URGENT(3),
    HIGH(2),
    MEDIUM(1),
    LOW(0)
}