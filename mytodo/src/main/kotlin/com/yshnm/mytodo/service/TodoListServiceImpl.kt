package com.yshnm.mytodo.service

import com.yshnm.mytodo.const.FLG
import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.repository.NextSubTaskRepository
import com.yshnm.mytodo.repository.SubTaskRepository
import com.yshnm.mytodo.repository.TaskRepository
import com.yshnm.mytodo.repository.test.TestRepository
import com.yshnm.mytodo.utility.SpecificationCreateUtil
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class TodoListServiceImpl(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    private val nextSubTaskRepository: NextSubTaskRepository,
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
            .filter { FLG.OFF == it.completeFlg }
            .map { task ->
                task.subTaskList = getSubTaskList(task, FLG.OFF)
                task
            }

        // 完了済タスクリスト取得
        val completedTaskList: List<Task> = taskList
            .filter { FLG.ON == it.completeFlg }
            .map { task ->
                task.subTaskList = getSubTaskList(task, FLG.ON)
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
    override fun complete(taskKind: TaskKind, id: String){

        // 完了させたタスクの下位のタスクも全て完了化する
        when (taskKind) {
            TaskKind.TASK -> {

                val taskId = id.toInt()
                completeTask(taskId)
                completeSubTask(taskId, null)
                completeNextSubTask(taskId, null, null)

            }
            TaskKind.SUB_TASK -> {
                val idList: List<String> = id.split("-")
                completeSubTask(idList[0].toInt(), idList[1].toInt())
                completeNextSubTask(idList[0].toInt(), idList[1].toInt(), null)
            }
            TaskKind.NEXT_SUB_TASK -> {
                val idList: List<String> = id.split("-")
                completeNextSubTask(idList[0].toInt(), idList[1].toInt(), idList[2].toInt())
            }
        }
    }


    /**
     * タスク削除
     */
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
            TaskKind.NEXT_SUB_TASK -> {
                val idList: List<String> = id.split("-")
                deleteNextSubTask(idList[0].toInt(), idList[1].toInt(), idList[2].toInt())
            }
        }

    }


    /**
     * サブタスクリスト一覧取得
     *   サブタスクリストの一覧を取得後、ネクストサブタスクリストを取得する
     */
    private fun getSubTaskList(task: Task, completeFlg: String) : List<SubTask> {

        // サブタスクリスト取得
        val subTaskList: List<SubTask> = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<SubTask>(task.taskId, TaskKind.SUB_TASK)
                    ?.and(SpecificationCreateUtil.equalCompleteFlg(completeFlg))
            )
        )

        return subTaskList.map {subTask ->
            subTask.nextSubTaskList = getNextSubTaskList(subTask, completeFlg)
            subTask
        }
    }

    /**
     * ネクストサブタスクリスト一覧取得
     */
    private fun getNextSubTaskList(subTask: SubTask, completeFlg: String) : List<NextSubTask> {
        return nextSubTaskRepository.findAll(
            Specification.where(
                    SpecificationCreateUtil.taskIdEqual<NextSubTask>(subTask.primaryKey.taskId, TaskKind.NEXT_SUB_TASK)
                    ?.and(SpecificationCreateUtil.subTaskIdEqual<NextSubTask>(subTask.primaryKey.subTaskId))
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
                SpecificationCreateUtil.taskIdEqual<Task>(taskId, TaskKind.TASK)
            )
        ).map {
            it.completeFlg = FLG.ON
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
                SpecificationCreateUtil.taskIdEqual<SubTask>(taskId, TaskKind.SUB_TASK)?.and(
                    SpecificationCreateUtil.subTaskIdEqual<SubTask>(subTaskId)
                )
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        subTaskRepository.saveAll(subTaskList)
    }

    /**
     * ネスクトサブタスク 完了化
     */
    private fun completeNextSubTask(taskId: Int?, subTaskId: Int?, nextSubTaskId: Int?) {

        val nextSubTaskList = nextSubTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<NextSubTask>(taskId, TaskKind.NEXT_SUB_TASK)?.and(
                    SpecificationCreateUtil.subTaskIdEqual<NextSubTask>(subTaskId)?.and(
                        SpecificationCreateUtil.nextSubTaskIdEqual(nextSubTaskId)
                    )
                )
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        nextSubTaskRepository.saveAll(nextSubTaskList)
    }

    /**
     * タスク 削除
     */
    private fun deleteTask(taskId: Int) {

        val taskList = taskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<Task>(taskId, TaskKind.TASK)
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        taskRepository.deleteAll(taskList)
    }

    /**
     * サブタスク 削除
     */
    private fun deleteSubTask(taskId: Int?, subTaskId: Int?) {

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
    }

    /**
     * ネスクトサブタスク 削除
     */
    private fun deleteNextSubTask(taskId: Int?, subTaskId: Int?, nextSubTaskId: Int?) {

        val nextSubTaskList = nextSubTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<NextSubTask>(taskId, TaskKind.NEXT_SUB_TASK)?.and(
                    SpecificationCreateUtil.subTaskIdEqual<NextSubTask>(subTaskId)?.and(
                        SpecificationCreateUtil.nextSubTaskIdEqual(nextSubTaskId)
                    )
                )
            )
        ).map {
            it.completeFlg = FLG.ON
            it
        }

        nextSubTaskRepository.deleteAll(nextSubTaskList)
    }


}
