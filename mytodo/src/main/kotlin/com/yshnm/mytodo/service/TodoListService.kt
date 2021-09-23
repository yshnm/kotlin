package com.yshnm.mytodo.service

import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.entity.TaskLists
import com.yshnm.mytodo.entity.Test

interface TodoListService {

    // 全件取得
    fun findAllTaskData(): TaskLists

}