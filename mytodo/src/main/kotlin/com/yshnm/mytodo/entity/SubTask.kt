package com.yshnm.mytodo.entity

import javax.persistence.*

@Entity
@Table(name = "SUB_TASK")
data class SubTask(

    @EmbeddedId
    var primaryKey: SubTaskKey,

    // ネクストサブタスクリスト
    @Transient
    var nextSubTaskList: List<NextSubTask> = emptyList()

): CommonEntity()