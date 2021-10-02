package com.yshnm.mytodo.controller.todoList

import com.yshnm.mytodo.const.CommonConst
import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.service.TodoListService
import com.yshnm.mytodo.utility.JsonUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * my todoコントローラークラス
 */
@Controller
class TodoListController(
    private val todoService: TodoListService
) {

    /**
     * 初期表示処理
     *
     * @param model 画面表示用modelオブジェクト（JSON）
     * @return 表示テンプレート文字列
     *
     */
    @GetMapping("/")
    fun index(
        model: Model
    ) : String {

        // 未完了タスク一覧データ取得
        val taskList: List<Task> = todoService.findList(CommonConst.FLG_OFF);

        // modelにセット
        model.addAttribute("inCompletedTaskList",
            JsonUtil.toJsonForTaskList(taskList)
        )

        // 表示テンプレート文字列を返却し、画面表示
        return DisplayTemplate.TODO_LIST.templateName
    }

    /**
     * タスク完了
     *
     * @param taskObject 完了とするタスクの情報が含まれたオブジェクト（JSON）
     * @return 更新後の一覧データ（JSON）
     *
     */
    @ResponseBody
    @PostMapping("/complete")
    fun complete(
        @RequestBody taskObject: Map<String, Object>,
    ): String {

        // タスク種別
        val taskKind: String = taskObject["taskKind"].toString()

        // タスクID
        val id: String = taskObject["id"].toString()

        // タスク完了処理
        todoService.complete(TaskKind.valueOf(taskKind), id)

        // 一覧表示データ取得
        val taskList: List<Task> = todoService.findList(CommonConst.FLG_OFF);

        // 一覧データをJSON形式に変換して返す
        return JsonUtil.toJsonForTaskList(taskList)
    }

    /**
     * タスク登録
     *
     * @param taskObject 登録するタスクの情報が含まれたオブジェクト（JSON）
     * @return 登録後の一覧データをJSON形式（JSON）
     */
    @ResponseBody
    @PostMapping("/insert")
    fun insert(
        @RequestBody taskObject: Map<String, Object>,
    ): String {

        // 登録処理
        todoService.insert(taskObject)

        // 一覧表示データ取得
        val taskList: List<Task> = todoService.findList(CommonConst.FLG_OFF);

        // 一覧データをJSONに変換して返す
        return JsonUtil.toJsonForTaskList(taskList)
    }

    /**
     * 完了・未完了一覧表示切り替え
     *
     * @param completeFlgObject 表示条件の完了フラグが含まれたオブジェクト（JSON）
     * @return 切り替え後の一覧データをJSON形式（JSON）
     */
    @ResponseBody
    @PostMapping("/switch")
    fun switchCompleted(
        @RequestBody completeFlgObject: Map<String, Object>,
    ): String {

        // 完了フラグ
        val completeFlg: String = completeFlgObject["completeFlg"].toString()

        // 一覧表示データ取得
        val taskList: List<Task> = todoService.findList(completeFlg);

        // 一覧データをJSONに変換して返す
        return JsonUtil.toJsonForTaskList(taskList)
    }

    /**
     * タスクを物理削除する
     *
     * @param taskObject 完了とするタスクの情報が含まれたオブジェクト（JSON）
     * @return 削除後の一覧データをJSON形式（JSON）
     */
    @ResponseBody
    @PostMapping("/delete")
    fun delete(
        @RequestBody taskObject: Map<String, Object>,
        model: Model
    ): String {

        // タスク種別
        val taskKind: String = taskObject["taskKind"].toString()

        // タスクID
        val id: String = taskObject["id"].toString()

        // 完了フラグ
        val completeFlg: String = taskObject["completeFlg"].toString()

        // タスク削除
        todoService.delete(TaskKind.valueOf(taskKind), id)

        // 一覧表示データ取得
        val taskList: List<Task> = todoService.findList(completeFlg);

        // 一覧データをJSONに変換して返す
        return JsonUtil.toJsonForTaskList(taskList)
    }
}