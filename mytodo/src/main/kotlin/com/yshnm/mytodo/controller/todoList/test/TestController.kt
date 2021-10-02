package com.yshnm.mytodo.controller.todoList.test

import com.yshnm.mytodo.const.CommonConst
import com.yshnm.mytodo.entity.Task
import com.yshnm.mytodo.enum.DisplayTemplate
import com.yshnm.mytodo.entity.test.Test
import com.yshnm.mytodo.service.TodoListService
import com.yshnm.mytodo.service.test.TestService
import com.yshnm.mytodo.utility.JsonUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.io.File

/**
 * spring jpa テスト用コントローラ (システム内では使用しない)
 */
@Controller
class TestController(
    private val testService: TestService,
    private val todoService: TodoListService
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

        // 表示テンプレート文字列を返却し、画面表示
        return DisplayTemplate.TODO_LIST.templateName
    }

    /**
     * 登録・取得テスト用メソッド
     */
    @GetMapping("/testData/reset")
    fun resetTestData(model: Model) : String {

        val sql = """
            |delete from TASK;
            |delete from SUB_TASK;
            |INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(2, '外部設計', '内容はサブタスクに記入', '2021-09-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, '詳細設計', '内容はサブタスクに記入', '2021-10-25 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, '要件定義', '内容はサブタスクに記入', '2021-05-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
            |INSERT INTO TASK(TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(4, '製造', '内容はサブタスクに記入', '2021-11-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 1,  '機能洗い出し', '必用な機能の洗い出しを行う', '2021-06-15 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(1, 2,  '要件ヒアリング', '顧客の担当者に要件を確認', '2021-08-30 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  1);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(2, 1,  '更新画面', 'html作成', '2021-09-15 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(2, 2,  '登録画面', 'html作成', '2021-09-23 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 1,  '更新画面', 'アクション定義', '2021-10-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 2,  '更新画面', '画面項目定義', '2021-10-03 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 3,  '更新画面', 'アクション定義', '2021-10-15 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(3, 4,  '更新画面', '画面項目定義', '2021-10-20 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(4, 1,  '登録画面', '画面表示まで', '2021-11-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(4, 2,  '登録画面', '登録処理完了まで', '2021-11-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0);
            |INSERT INTO SUB_TASK(TASK_ID, SUB_TASK_ID, TITLE, DETAIL, EXECUTION_DATE, CREATE_DATETIME, UPDATE_DATETIME, COMPLETE_FLG) VALUES(4, 3,  '登録画面', 'トランサクションまわり', '2021-11-01 00:00:00.0000000', '2021-09-23 05:26:25.4365986', '2021-09-23 05:26:25.4365986',  0); 
        """.trimMargin()

        // テストデータリセット
        testService.resetTestData(sql)

        // 表示テンプレート文字列を返却し、画面表示
        return "reset_complete"
    }

}