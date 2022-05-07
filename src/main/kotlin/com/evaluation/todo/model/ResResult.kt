package com.evaluation.todo.model

/**
 * 通用返回封裝
 *
 * @author HY Lin
 * @since 2022/4/27 10:10 PM
 */
class ResResult<T> {
    /**
     * 返回碼
     */
    var code = 0

    /**
     * 相關訊息
     */
    var msg: String = ""

    /**
     * 時間戳
     */
    var timestamp: Long = 0L

    /**
     * 內容主體
     */
    var data: T? = null

    companion object {
        fun <T> ok(): ResResult<T?> {
            return ok(null)
        }

        fun <T> ok(data: T): ResResult<T> {
            val result = ResResult<T>()
            result.code = 0
            result.msg = "success"
            result.data = data
            result.timestamp = System.currentTimeMillis()
            return result
        }

        fun <T> failed(): ResResult<T> {
            return failed(1, "failed")
        }

        fun <T> failed(error: ErrorCode): ResResult<T> {
            return failed(error.code, error.desc)
        }

        fun <T> failed(code: Int, msg: String): ResResult<T> {
            val result = ResResult<T>()
            result.code = code
            result.msg = msg
            result.timestamp = System.currentTimeMillis()
            return result
        }
    }
}