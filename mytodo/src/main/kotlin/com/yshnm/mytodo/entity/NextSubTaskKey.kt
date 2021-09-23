package com.yshnm.mytodo.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Id

@Embeddable
data class NextSubTaskKey(

    @Column(name = "task_id")
    val taskId: Int? = null,

    @Column(name = "sub_task_id")
    val subTaskId: Int? = null,

    @Column(name = "next_sub_task_id")
    val nextSubTaskId: Int? = null,
): Serializable
