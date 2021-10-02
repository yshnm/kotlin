package com.yshnm.mytodo.utility

import com.yshnm.mytodo.const.CommonConst
import com.yshnm.mytodo.entity.SubTask
import com.yshnm.mytodo.entity.SubTaskKey
import com.yshnm.mytodo.entity.Task
import java.time.format.DateTimeFormatter

/**
 * JSON変換用 utilityクラス
 */
object JsonUtil {

    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern(CommonConst.DATETIME_FORMAT)

    /**
     * タスクリスト -> JSON文字列へ変換
     * @param taskList タスクリスト
     * @return JSON文字列
     */
    fun toJsonForTaskList(taskList: List<Task>): String {

        val json: String =taskList.joinToString(separator = ",") { task ->

            val sb = StringBuilder()

            sb.append("{")
            sb.append("\"key\":\"${task.taskId}\",")
            sb.append("\"taskId\":${task.taskId},")
            sb.append("\"title\":\"${task.title}\",")
            sb.append("\"detail\":\"${task.detail}\",")
            sb.append("\"completeFlg\":\"${task.completeFlg}\",")
            sb.append("\"executionDate\":\"${task.executionDate?.toLocalDateTime()?.format(format) ?: ""}\",")
            sb.append("\"createDatetime\":\"${task.createDatetime?.toLocalDateTime()?.format(format) ?: ""}\",")
            sb.append("\"updateDatetime\":\"${task.updateDatetime?.toLocalDateTime()?.format(format) ?: ""}\",")
            // サブタスク JSON化
            sb.append("\"subTaskList\":[${toJsonForSubTaskList(task.subTaskList)}]")
            sb.append("}")
            sb.toString()
        }

        return "[$json]"
    }

    /**
     * サブタスクリスト -> JSON文字列へ変換
     * @param subTaskList サブタスクリスト
     * @return JSON文字列
     */
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
            sb.append("\"executionDate\":\"${subTask.executionDate?.toLocalDateTime()?.format(format) ?: ""}\",")
            sb.append("\"createDatetime\":\"${subTask.createDatetime?.toLocalDateTime()?.format(format) ?: ""}\",")
            sb.append("\"updateDatetime\":\"${subTask.updateDatetime?.toLocalDateTime()?.format(format) ?: ""}\"")
            sb.append("}")
            sb.toString()
        }
    }

    /**
     * JSON -> タスクオブジェクトへ変換
     * @param taskObject 画面側から渡されたJSON形式のデータが含まれたオブジェクト
     * @param insertTaskId 登録タスクID
     */
    fun toTaskObjectForJson(taskObject: Map<String, Object>, insertTaskId: Int): Task {

        val jsonSubTaskList = taskObject["subTaskList"] as List<Map<String, String>>

        val subTaskList = jsonSubTaskList.mapIndexed { insertSubTaskId, subTaskMap ->

            val subTask =SubTask(
                primaryKey = SubTaskKey(
                    taskId = insertTaskId,
                    subTaskId = insertSubTaskId
                )
            )

            subTask.title = subTaskMap["title"].toString()
            subTask.detail = subTaskMap["detail"].toString()
            subTask.completeFlg = CommonConst.FLG_OFF
            subTask.createDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
            subTask.updateDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
            subTask
        }

        val task = Task(
            taskId = insertTaskId,
            subTaskList = subTaskList
        )
        task.title = taskObject["title"].toString()
        task.detail = taskObject["detail"].toString()
        task.completeFlg = CommonConst.FLG_OFF
        task.createDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()
        task.updateDatetime = DateTimeUtil.getNowDateTime().toTimeStamp()

        return task
    }
}