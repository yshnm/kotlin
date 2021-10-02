package com.yshnm.mytodo.service

import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.enum.TaskKind

/**
 * todoList操作用サービス
 */
interface TodoListService {

    /**
     * 一覧表示データ取得
     * @param completeFlg 完了フラグ
     * @return 一覧表示リスト
     */
    fun findList(completeFlg: String): List<Task>

    /**
     * タスク完了
     * @param taskKind タスク種別
     * @param id タスクID or サブタスクID
     */
    fun complete(taskKind: TaskKind, id: String)

    /**
     * 新規タスク登録
     * @param taskObject タスクの情報が含まれたオブジェクト
     */
    fun insert(taskObject: Map<String, Object>)

    /**
     * タスク削除
     * @param taskKind タスク種別
     * @param id タスクID or サブタスクID
     */
    fun delete(taskKind: TaskKind, id: String)
}