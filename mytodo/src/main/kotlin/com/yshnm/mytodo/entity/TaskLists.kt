package com.yshnm.mytodo.entity

data class TaskLists(
    // 完了済みタスクリスト
    val completedTaskList: List<Task>,

    // 未完了タスクリスト
    val inCompletedTaskList: List<Task>,
)