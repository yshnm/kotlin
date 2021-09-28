package com.yshnm.mytodo.utility

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.yshnm.mytodo.const.Const
import com.yshnm.mytodo.entity.SubTask
import com.yshnm.mytodo.entity.SubTaskKey
import com.yshnm.mytodo.entity.Task
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

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
            sb.append("\"executionDate\":\"${task.executionDate?.toLocalDateTime()?.format(format)}\",")
            sb.append("\"createDatetime\":\"${task.createDatetime?.toLocalDateTime()?.format(format)}\",")
            sb.append("\"updateDatetime\":\"${task.updateDatetime?.toLocalDateTime()?.format(format)}\",")
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
            sb.append("\"executionDate\":\"${subTask.executionDate?.toLocalDateTime()?.format(format)}\",")
            sb.append("\"createDatetime\":\"${subTask.createDatetime?.toLocalDateTime()?.format(format)}\",")
            sb.append("\"updateDatetime\":\"${subTask.updateDatetime?.toLocalDateTime()?.format(format)}\"")
            sb.append("}")
            sb.toString()
        }
    }

    fun toTaskObject(taskObj: Map<String, Object>, insertTaskId: Int): Task {

        val jsonSubTaskList = taskObj["subTaskList"] as List<Map<String, String>>

        val subTaskList = jsonSubTaskList.mapIndexed { insertSubTaskId, subTaskMap ->

            val subTask =SubTask(
                primaryKey = SubTaskKey(
                    taskId = insertTaskId,
                    subTaskId = insertSubTaskId
                )
            )

            subTask.title = subTaskMap["title"].toString()
            subTask.detail = subTaskMap["detail"].toString()
            subTask.completeFlg = Const.FLG_OFF
            subTask.createDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
            subTask.updateDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
            subTask
        }

        val task = Task(
            taskId = insertTaskId,
            subTaskList = subTaskList
        )
        task.title = taskObj["title"].toString()
        task.detail = taskObj["detail"].toString()
        task.completeFlg = Const.FLG_OFF
        task.createDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
        task.updateDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()

        return task

    }
}