package com.yshnm.mytodo.controller.todoList

import com.yshnm.mytodo.entity.TaskLists
import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.enum.TaskKind
import com.yshnm.mytodo.service.TodoListService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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
        model.addAttribute("completedTaskList", taskLists.completedTaskList)
        model.addAttribute("inCompletedTaskList", taskLists.inCompletedTaskList)

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


}