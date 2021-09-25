package com.yshnm.mytodo.service

import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.entity.TaskLists
import com.yshnm.mytodo.entity.Test
import com.yshnm.mytodo.enum.TaskKind

interface TodoListService {

    /**
     * 全件取得
     */
    fun findAll(): TaskLists

    /**
     * 完了化
     */
    fun complete(taskKind: TaskKind, id: String)

    /**
     * 削除
     * TODO タスク削除機能実装予定
     */
    // fun delete(taskKind: TaskKind, id: String)


}