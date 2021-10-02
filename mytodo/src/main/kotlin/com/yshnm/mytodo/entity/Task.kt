package com.yshnm.mytodo.entity

import javax.persistence.*
import kotlin.jvm.Transient

/**
 * タスクEntity
 */
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