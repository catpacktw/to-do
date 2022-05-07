package com.evaluation.todo.dao

import com.evaluation.todo.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskDao : JpaRepository<Task, Long> {

    fun findAllByOrderByWeightDescIdDesc(): List<Task>?

    fun findByStatusOrderByWeightDescIdDesc(status: Int?): List<Task>?

    fun findTopByOrderByWeightDesc(): Task?

    fun findByWeight(weight: Int): Task?
}