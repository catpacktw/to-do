package com.evaluation.todo.controller

import arrow.core.Either
import com.evaluation.todo.dto.UpdateWeightDTO
import com.evaluation.todo.entity.Task
import com.evaluation.todo.model.EditGroup
import com.evaluation.todo.model.ResResult
import com.evaluation.todo.service.ToDoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Todo App", description = "Todo App CRUD API")
class ToDoController(@Autowired private val toDoService: ToDoService) {

    /**
     * Get task by status
     */
    @GetMapping("/index")
    @Operation(summary = "Query todo list", description = "Main query API for displaying index page")
    fun getTasksByStatus(@Parameter(description = "Task status: 0-Pending, 1-Done") @RequestParam(required = false) status: Int?)
            : ResResult<List<Task>?> = ResResult.ok(toDoService.getTasksByStatus(status))


    /**
     * Add task
     */
    @PostMapping("/add")
    @Operation(summary = "Add a todo task", description = "Insert a todo task data to database")
    fun addTask(@RequestBody @Validated(EditGroup::class) task: Task): ResResult<Task> =
        when (val result = toDoService.addTask(task)) {
            is Either.Right -> ResResult.ok(result.value)
            is Either.Left -> ResResult.failed(result.value)
        }


    /**
     * Update task
     */
    @PutMapping("/update")
    @Operation(summary = "Update task by ID", description = "Including title, content, status, weight fields...")
    fun updateTask(@RequestBody @Validated(EditGroup::class) task: Task): ResResult<Task> =
        when (val result = toDoService.updateTaskContent(task)) {
            is Either.Right -> ResResult.ok(result.value)
            is Either.Left -> ResResult.failed(result.value)
        }


    /**
     * Update task status
     */
    @PutMapping("/status/update")
    @Operation(summary = "Update task status to opposite", description = "Change task status by ID. Do XOR Math")
    fun updateTaskStatus(@RequestParam id: Long): ResResult<Task> =
        when (val result = toDoService.updateTaskStatus(id)) {
            is Either.Right -> ResResult.ok(result.value)
            is Either.Left -> ResResult.failed(result.value)
        }


    /**
     * Update task priority
     */
    @PutMapping("/weight")
    @Operation(
        summary = "Update task priority",
        description = "Update task priority by ID. Move forward or backward for one position"
    )
    fun updateTaskWeight(@RequestBody request: UpdateWeightDTO): ResResult<Task> =
        when (val result = toDoService.updateWeight(request.id, request.priority)) {
            is Either.Right -> ResResult.ok(result.value)
            is Either.Left -> ResResult.failed(result.value)
        }

    /**
     * Delete task
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete task by ID", description = "Remove a task by ID. Hard delete")
    fun deleteTask(@RequestParam id: Long): ResResult<Any> =
        when (val result = toDoService.deleteTask(id)) {
            is Either.Right -> ResResult.ok(result.value)
            is Either.Left -> ResResult.failed(result.value)
        }

}