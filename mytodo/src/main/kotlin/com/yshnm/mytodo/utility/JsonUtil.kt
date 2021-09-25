package com.yshnm.mytodo.utility

import com.yshnm.mytodo.const.Const
import com.yshnm.mytodo.entity.SubTask
import com.yshnm.mytodo.entity.Task
import java.time.format.DateTimeFormatter

object JsonUtil {

    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern(Const.DATETIME_FORMAT)

    fun toJsonForTaskList(taskList: List<Task>): String {

        val json: String =taskList.joinToString(separator = ",") { task ->

            val sb = StringBuilder()

            sb.append("{")
            sb.append("\"key\":\"${task.taskId}\",")
            sb.append("\"taskId\":${task.taskId},")
            sb.append("\"title\":\"${task.title}\",")
            sb.append("\"detail\":\"${task.detail}\",")
            sb.append("\"completeFlg\":\"${task.completeFlg}\",")
            sb.append("\"executionDate\":\"${task.executionDate.toLocalDateTime().format(format)}\",")
            sb.append("\"createDatetime\":\"${task.createDatetime.toLocalDateTime().format(format)}\",")
            sb.append("\"updateDatetime\":\"${task.updateDatetime.toLocalDateTime().format(format)}\",")
            // サブタスク JSON化
            sb.append("\"subTaskList\":[${toJsonForSubTaskList(task.subTaskList)}]")
            sb.append("}")
            sb.toString()
        }

        return "[$json]"
    }

    private fun toJsonForSubTaskList(subTaskList: List<SubTask>): String {

        return subTaskList.joinToString(separator = ",") { subTask ->
            val sb = StringBuilder()
            sb.append("{")
            sb.append("\"key\":\"${subTask.primaryKey.taskId}-${subTask.primaryKey.subTaskId}\",")
            sb.append("\"taskId\":${subTask.primaryKey.taskId},")
            sb.append("\"subTaskId\":${subTask.primaryKey.subTaskId},")
            sb.append("\"title\":\"${subTask.title}\",")
            sb.append("\"detail\":\"${subTask.detail}\",")
            sb.append("\"completeFlg\":\"${subTask.completeFlg}\",")
            sb.append("\"executionDate\":\"${subTask.executionDate.toLocalDateTime().format(format)}\",")
            sb.append("\"createDatetime\":\"${subTask.createDatetime.toLocalDateTime().format(format)}\",")
            sb.append("\"updateDatetime\":\"${subTask.updateDatetime.toLocalDateTime().format(format)}\"")
            sb.append("}")
            sb.toString()
        }
    }
}