package com.yshnm.mytodo.entity

import javax.persistence.*

/**
 * サブタスクEntity
 */
@Entity
@Table(name = "SUB_TASK")
data class SubTask(

    @EmbeddedId
    var primaryKey: SubTaskKey,

): CommonEntity()