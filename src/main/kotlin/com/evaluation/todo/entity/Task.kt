package com.evaluation.todo.entity

import com.evaluation.todo.model.EditGroup
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import javax.persistence.*
import javax.validation.constraints.Min

/**
 *
 * @author HY Lin
 */
@Table
@Entity
class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
    var title: @Length(max = 50, groups = [EditGroup::class]) String = "",
    var content: @Length(max = 200, groups = [EditGroup::class]) String = "",
    var status: @Range(min = 0, max = 1) Int = 0,
    var weight: @Min(0) Int = 0
) {
    override fun toString(): String {
        return "Task(id=$id, title='$title', content='$content', status=$status, weight=$weight)"
    }
}