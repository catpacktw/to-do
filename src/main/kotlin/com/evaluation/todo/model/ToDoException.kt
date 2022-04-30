package com.evaluation.todo.model

/**
 * Handles exception
 *
 * @author HY Lin
 */
class ToDoException : Exception {

    var code : Int = 1

    constructor() : super()
    constructor(msg: String) : super(msg)
    constructor(code: Int, msg: String) : super(msg){
        this.code = code
    }
    constructor(msg: String, cause: Throwable) : super(msg, cause)
    constructor(cause: Throwable) : super(cause)

    companion object{
        fun of(msg: String): ToDoException{
            return ToDoException(msg)
        }
    }
}