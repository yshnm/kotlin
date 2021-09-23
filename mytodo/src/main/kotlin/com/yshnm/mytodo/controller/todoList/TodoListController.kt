package com.yshnm.mytodo.controller.todoList

import com.yshnm.mytodo.entity.TaskLists
import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.service.TodoListService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
        val taskLists: TaskLists = todoService.findAllTaskData();

        // modelにセット
        model.addAttribute("completedTaskList", taskLists.completedTaskList)
        model.addAttribute("inCompletedTaskList", taskLists.inCompletedTaskList)

        return DisplayTemplate.TODO_LIST.getName()
    }
}