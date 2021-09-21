package com.yshnm.mytodo.controller.todoList

import com.yshnm.mytodo.enum.DisplayTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TodoListController {

    /**
     * 初期遷移処理
     * todoリスト表示
     */
    @GetMapping("/")
    fun index() : String {
        return DisplayTemplate.UPDATE.getTemplate()
    }
}