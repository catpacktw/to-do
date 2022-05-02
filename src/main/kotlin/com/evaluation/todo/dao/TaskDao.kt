package com.evaluation.todo.dao

import com.evaluation.todo.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskDao : JpaRepository<Task, Long> {

    fun findAllByOrderByWeightDesc(): List<Task>?

    fun findByStatusOrderByWeightDesc(status: Int?): List<Task>?

    fun findTopByOrderByWeightDesc(): Task?

    fun findByWeight(weight: Int): Task?
}