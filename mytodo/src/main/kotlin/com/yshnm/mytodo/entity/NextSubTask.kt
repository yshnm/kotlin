package com.yshnm.mytodo.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "NEXT_SUB_TASK")
data class NextSubTask(

    @EmbeddedId
    var primaryKey: NextSubTaskKey

): CommonEntity()