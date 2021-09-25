package com.yshnm.mytodo.service

import com.yshnm.mytodo.const.Const
import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.repository.SubTaskRepository
import com.yshnm.mytodo.repository.TaskRepository
import com.yshnm.mytodo.utility.SpecificationCreateUtil
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class TodoListServiceImpl(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    private val entityManager: EntityManager
): TodoListService {

    /**
     * 画面表示時 タスク一覧取得
     */
    override fun findAll(): TaskLists {

        // タスクリスト取得
        val taskList: List<Task> = taskRepository.findAll()

        // 未完了タスクリスト取得
        val inCompletedTaskList: List<Task> = taskList
            .filter { Const.FLG_OFF == it.completeFlg }
            .map { task ->
                task.subTaskList = getSubTaskList(task, Const.FLG_OFF)
                task
            }

        // 完了済タスクリスト取得
        val completedTaskList: List<Task> = taskList
            .filter { Const.FLG_ON == it.completeFlg }
            .map { task ->
                task.subTaskList = getSubTaskList(task, Const.FLG_ON)
                task
            }

        return TaskLists(
            completedTaskList = completedTaskList,
            inCompletedTaskList = inCompletedTaskList
        )
    }

    /**
     * 完了化
     */
    override fun complete(taskKind: TaskKind, id: String) {

        // 完了させたタスクの下位のタスクも全て完了化する
        when (taskKind) {
            TaskKind.TASK -> {

                val taskId = id.toInt()
                completeTask(taskId)
                completeSubTask(taskId, null)

            }
            TaskKind.SUB_TASK -> {
                val idList: List<String> = id.split("-")
                completeSubTask(idList[0].toInt(), idList[1].toInt())
            }
        }
    }


    /**
     * TODO タスク削除機能実装予定
     */
    /*
    override fun delete(taskKind: TaskKind, id: String) {

        // 削除したタスクの下位のタスクも全て削除する
        when (taskKind) {
            TaskKind.TASK -> {

                val taskId = id.toInt()
                deleteTask(taskId)
                deleteSubTask(taskId, null)
                deleteNextSubTask(taskId, null, null)

            }
            TaskKind.SUB_TASK -> {
                val idList: List<String> = id.split("-")
                deleteSubTask(idList[0].toInt(), idList[1].toInt())
                deleteNextSubTask(idList[0].toInt(), idList[1].toInt(), null)
            }
        }

    }
    */


    /**
     * サブタスクリスト一覧取得
     *   サブタスクリストの一覧を取得後、ネクストサブタスクリストを取得する
     */
    private fun getSubTaskList(task: Task, completeFlg: String): List<SubTask> {

        // サブタスクリスト取得
        return subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<SubTask>(task.taskId, TaskKind.SUB_TASK)
                    ?.and(SpecificationCreateUtil.equalCompleteFlg(completeFlg))
            )
        )
    }


    /**
     * タスク 完了化
     */
    private fun completeTask(taskId: Int) {

        val taskList = taskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<Task>(taskId, TaskKind.TASK)
            )
        ).map {
            it.completeFlg = Const.FLG_ON
            it
        }

        taskRepository.saveAll(taskList)
    }

    /**
     * サブタスク 完了化
     */
    private fun completeSubTask(taskId: Int?, subTaskId: Int?) {

        val subTaskList = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<SubTask>(taskId, TaskKind.SUB_TASK)?.and(
                    SpecificationCreateUtil.equalSubTaskId<SubTask>(subTaskId)
                )
            )
        ).map {
            it.completeFlg = Const.FLG_ON
            it
        }

        subTaskRepository.saveAll(subTaskList)
    }



    /**
     * タスク 削除
     * TODO タスク削除機能実装予定
     */
    /*private fun deleteTask(taskId: Int) {

        val taskList = taskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<Task>(taskId, TaskKind.TASK)
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        taskRepository.deleteAll(taskList)
    }*/

    /**
     * サブタスク 削除
     * TODO タスク削除機能実装予定
     */
    /*private fun deleteSubTask(taskId: Int?, subTaskId: Int?) {

        val subTaskList = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<SubTask>(taskId, TaskKind.SUB_TASK)?.and(
                    SpecificationCreateUtil.subTaskIdEqual<SubTask>(subTaskId)
                )
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        subTaskRepository.deleteAll(subTaskList)
    }*/

}