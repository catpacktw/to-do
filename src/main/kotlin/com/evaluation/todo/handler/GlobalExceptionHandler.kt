package com.evaluation.todo.handler

import com.evaluation.todo.model.ResResult
import com.evaluation.todo.model.ToDoException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.WebRequest

/**
 * Handling exception
 *
 * @author HY Lin
 * @since 2022/4/30 2:58 PM
 */
@ControllerAdvice
class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = [(ToDoException::class)])
    fun <T> handleToDoException(ex: ToDoException, request: WebRequest): ResResult<T> {
        return ResResult.failed(ex.code, ex.message ?: "")
    }

}