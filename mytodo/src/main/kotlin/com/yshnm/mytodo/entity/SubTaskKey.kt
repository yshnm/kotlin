package com.yshnm.mytodo.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * サブタスクのPrimaryKeyを定義したEntity
 */
@Embeddable
data class SubTaskKey(
    @Column(name = "task_id")
    val taskId: Int? = null,

    @Column(name = "sub_task_id")
    val subTaskId: Int? = null,
): Serializable