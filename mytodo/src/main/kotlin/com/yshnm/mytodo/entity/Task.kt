package com.yshnm.mytodo.entity

import java.sql.Timestamp
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "task")
data class Task(

    // タスクID
    @Id
    @Column(name = "task_id")
    var taskId: Int,

    @Transient
    // サブタスクリスト
    var subTaskList: List<SubTask>

): CommonEntity()