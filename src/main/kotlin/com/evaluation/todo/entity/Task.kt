package com.evaluation.todo.entity

import com.evaluation.todo.model.EditGroup
import io.swagger.v3.oas.annotations.media.Schema
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
@Schema(description = "Task data entity")
class Task (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Schema(description = "Title, 0-50 length") @Length(max = 50, groups = [EditGroup::class])
    var title: String = "",
    @Schema(description = "Content, 0-200 length") @Length(max = 200, groups = [EditGroup::class])
    var content: String = "",
    @Schema(description = "0-Pending, 1-Done") @Range(min = 0, max = 1)
    var status: Int = 0,
    @Schema(description = "Priority") @Min(0)
    var weight: Int = 0
) {
    override fun toString(): String {
        return "Task(id=$id, title='$title', content='$content', status=$status, weight=$weight)"
    }
}