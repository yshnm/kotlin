package com.yshnm.mytodo.controller.todoList

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.yshnm.mytodo.entity.SubTask
import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.entity.TaskLists
import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.service.TodoListService
import com.yshnm.mytodo.utility.JsonUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@Controller
class TodoListController(
    private val todoService: TodoListService
) {

    /**
     * 初期遷移処理
     * todoリスト表示
     */
    @GetMapping("/")
    fun index(
        model: Model
    ) : String {

        // 一覧表示データ取得
        val taskLists: TaskLists = todoService.findAll();

        // modelにセット
        model.addAttribute("inCompletedTaskList",
            JsonUtil.toJsonForTaskList(taskLists.inCompletedTaskList)
        )

        // TODO 完了済みリストの実装
        // model.addAttribute("completedTaskList", taskLists.completedTaskList)


        return DisplayTemplate.TODO_LIST.getName()
    }

    @ResponseBody
    @GetMapping("/complete/{id}/{taskKind}")
    fun complete(
        @PathVariable("id") id: String,
        @PathVariable("taskKind") taskKind: String,
        model: Model
    ): String {

        // タスク完了化
        todoService.complete(TaskKind.valueOf(taskKind), id)

        return id
    }

    @ResponseBody
    @PostMapping("/insert")
    fun insert(
        @RequestBody taskObject: Map<String, Object>,
        model: Model
    ): String {

        // Taskオブジェクトに変換

        // 登録処理
        todoService.insert(taskObject)

        // 一覧表示データ取得
        val taskLists: TaskLists = todoService.findAll();

        return JsonUtil.toJsonForTaskList(taskLists.inCompletedTaskList)
    }


    /*
      TODO タスク削除機能実装予定
    @ResponseBody
    @GetMapping("/delete/{id}/{taskKind}")
    fun delete(
        @PathVariable("id") id: String,
        @PathVariable("taskKind") taskKind: String,
        model: Model
    ): String {

        // タスク削除
        todoService.delete(TaskKind.valueOf(taskKind), id)

        return id
    }
     */


}