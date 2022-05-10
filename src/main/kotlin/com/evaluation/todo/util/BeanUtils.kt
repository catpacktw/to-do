package com.evaluation.todo.util

import org.springframework.beans.BeanUtils
import org.springframework.util.CollectionUtils
import java.util.function.Supplier

/**
 * Description
 *
 * @author HY Lin
 * @since 2022/5/10 9:10 AM
 */
object BeanUtils {
    fun <S : Any, T : Any> copyListProperties(sources: List<S>?, target: Supplier<T>): List<T> {
        if (CollectionUtils.isEmpty(sources)) {
            return ArrayList()
        }
        val list: MutableList<T> = ArrayList(sources!!.size)
        for (source in sources) {
            val t = target.get()
            BeanUtils.copyProperties(source, t)
            list.add(t)
        }
        return list
    }
}