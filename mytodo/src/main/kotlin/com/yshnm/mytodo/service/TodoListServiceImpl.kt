package com.yshnm.mytodo.service

import com.yshnm.mytodo.const.CommonConst
import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.repository.SubTaskRepository
import com.yshnm.mytodo.repository.TaskRepository
import com.yshnm.mytodo.utility.JsonUtil
import com.yshnm.mytodo.utility.SpecificationCreateUtil
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

/**
 * todoList操作用サービス実装
 */
@Service
class TodoListServiceImpl(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    private val entityManager: EntityManager
) : TodoListService {

    /**
     * 一覧表示データ取得
     * @param completeFlg 完了フラグ
     * @return 一覧表示リスト
     */
    override fun findList(completeFlg: String): List<Task> {

        // タスクリスト取得
        val taskList: List<Task> = taskRepository.findAll()

        // 未完了タスクリスト取得
        return taskList
            .filter { it.completeFlg == completeFlg }
            .map { task ->
                task.subTaskList = getSubTaskList(task, completeFlg)
                task
            }
    }

    /**
     * タスク完了
     * @param taskKind タスク種別
     * @param id タスクID or サブタスクID
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
     * 新規タスク登録
     * @param taskObject タスクの情報が含まれたオブジェクト
     */
    override fun insert(taskObject: Map<String, Object>) {

        // タスクIDの最大値を取得
        val maxTaskId: Int = (taskRepository.findAll().maxOf { task -> task.taskId }) + 1

        // サブオブジェクト作成
        val task: Task = JsonUtil.toTaskObjectForJson(taskObject, maxTaskId)

        // タスク 登録
        taskRepository.save(task)

        // サブタスク 登録
        subTaskRepository.saveAll(task.subTaskList)
    }

    /**
     * タスク削除
     * @param taskKind タスク種別
     * @param id タスクID or サブタスクID
     */
    override fun delete(taskKind: TaskKind, id: String) {

        // 削除したタスクの下位のタスクも全て削除する
        when (taskKind) {
            TaskKind.TASK -> {

                val taskId = id.toInt()
                deleteTask(taskId)
                deleteSubTask(taskId, null)

            }
            TaskKind.SUB_TASK -> {
                val idList: List<String> = id.split("-")
                deleteSubTask(idList[0].toInt(), idList[1].toInt())
            }
        }

    }

    /**
     * サブタスクリスト一覧取得
     * @param task タスクオブジェクト
     * @param completeFlg 完了フラグ
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
     * タスク完了化
     * @param taskId タスクID
     */
    private fun completeTask(taskId: Int) {

        val task = taskRepository.findOne(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<Task>(taskId, TaskKind.TASK)
            )
        ).map {
            it.completeFlg = CommonConst.FLG_ON
            it
        }

        taskRepository.save(task.get())
    }

    /**
     * サブタスク完了化
     * @param taskId タスクID
     * @param subTaskId サブタスクID
     */
    private fun completeSubTask(taskId: Int?, subTaskId: Int?) {

        val subTaskList = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<SubTask>(taskId, TaskKind.SUB_TASK)?.and(
                    SpecificationCreateUtil.equalSubTaskId<SubTask>(subTaskId)
                )
            )
        ).map {
            it.completeFlg = CommonConst.FLG_ON
            it
        }

        subTaskRepository.saveAll(subTaskList)
    }

    /**
     * タスク削除
     * @param taskId タスクID
     */
    private fun deleteTask(taskId: Int) {

        val task = taskRepository.findOne(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<Task>(taskId, TaskKind.TASK)
            )
        )

        taskRepository.delete(task.get())
    }

    /**
     * サブタスク削除
     * @param taskId タスクID
     * @param subTaskId サブタスクID
     */
    private fun deleteSubTask(taskId: Int?, subTaskId: Int?) {

        val subTaskList = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.equalTaskId<SubTask>(taskId, TaskKind.SUB_TASK)?.and(
                    SpecificationCreateUtil.equalSubTaskId<SubTask>(subTaskId)
                )
            )
        )

        subTaskRepository.deleteAll(subTaskList)
    }
}