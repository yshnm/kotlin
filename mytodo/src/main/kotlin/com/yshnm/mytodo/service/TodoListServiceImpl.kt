package com.yshnm.mytodo.service

import com.yshnm.mytodo.const.FLG
import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.repository.NextSubTaskRepository
import com.yshnm.mytodo.repository.SubTaskRepository
import com.yshnm.mytodo.repository.TaskRepository
import com.yshnm.mytodo.repository.test.TestRepository
import com.yshnm.mytodo.utility.SpecificationCreateUtil
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class TodoListServiceImpl(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    private val nextSubTaskRepository: NextSubTaskRepository,
    private val testRepository: TestRepository
): TodoListService {

    /**
     * 画面表示時 タスク一覧取得
     */
    override fun findAllTaskData(): TaskLists {

        val a = testRepository.findAll();
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
     * サブタスクリスト一覧取得
     *   サブタスクリストの一覧を取得後、ネクストサブタスクリストを取得する
     */
    private fun getSubTaskList(task: Task, completeFlg: String) : List<SubTask> {

        // サブタスクリスト取得
        val subTaskList: List<SubTask> = subTaskRepository.findAll(
            Specification.where(
                SpecificationCreateUtil.taskIdEqual<SubTask, SubTaskKey>(task.taskId)
                    .and(SpecificationCreateUtil.equalCompleteFlg(completeFlg))
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
                    SpecificationCreateUtil.taskIdEqual<NextSubTask, NextSubTaskKey>(subTask.primaryKey.taskId)
                    .and(SpecificationCreateUtil.subTaskIdEqual(subTask.primaryKey.subTaskId))
                        .and(SpecificationCreateUtil.equalCompleteFlg(completeFlg))
            )
        )
    }
}
