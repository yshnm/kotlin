package com.yshnm.mytodo.controller.todoList.test

import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.entity.Test
import com.yshnm.mytodo.service.test.TestService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class TestController(
    private val testService: TestService
) {

    /**
     * 登録・取得テスト用メソッド
     */
    @GetMapping("/test/insert/{name}")
    fun testInsert(
        @PathVariable("name") name: String
    ) : String {

        val insertData = Test(
            name = name
        )

        // データ登録
        testService.insertTestData(insertData)

        // データをコンソールに出力
        testService.findAllTestData().forEach(::println)

        return DisplayTemplate.TODO_LIST.getName();
    }
}