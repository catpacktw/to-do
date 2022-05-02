package com.evaluation.todo.controller

import com.evaluation.todo.dto.UpdateWeightDTO
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.EditGroup
import com.evaluation.todo.model.ResResult
import com.evaluation.todo.service.ToDoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


/**
 * TO-DO LIST API
 *
 * @author HY Lin
 **/
@RestController
@RequestMapping("/todo")
class ToDoController(@Autowired private val toDoService: ToDoService) {

    /**
     * Get task by status
     */
    @GetMapping("/index")
    fun getTasksByStatus(@RequestParam(required = false) status: Int?): ResResult<List<Task>?> {
        val result = toDoService.getTasksByStatus(status)
        return ResResult.ok(result)
    }

    /**
     * Add task
     */
    @PostMapping("/add")
    fun addTask(@RequestBody @Validated(EditGroup::class) task: Task): ResResult<Task> {
        return ResResult.ok(toDoService.addTask(task))
    }

    /**
     * Update task
     */
    @PutMapping("/update")
    fun updateTask(@RequestBody @Validated(EditGroup::class) task: Task): ResResult<Task> {
        return ResResult.ok(toDoService.updateTask(task))
    }

    /**
     * Update task status
     */
    @PutMapping("/status/update")
    fun updateTaskStatus(@RequestParam id: Long): ResResult<Task> {
        return ResResult.ok(toDoService.updateTaskStatus(id))
    }

    /**
     * Update task priority
     */
    @PutMapping("/weight")
    fun updateTaskWeight(@RequestBody request: UpdateWeightDTO): ResResult<Task> {
        return ResResult.ok(toDoService.updateWeight(request.id, request.operation))
    }

    /**
     * Delete task
     */
    @DeleteMapping("/delete")
    fun deleteTask(@RequestParam id: Long): ResResult<Any> {
        return ResResult.ok(toDoService.deleteTask(id))
    }
}