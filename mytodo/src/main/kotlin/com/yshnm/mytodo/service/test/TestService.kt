package com.yshnm.mytodo.service.test

import com.yshnm.mytodo.entity.Test

interface TestService {

    // テストデータ登録
    fun insertTestData(test: Test)

    // 全件取得
    fun findAllTestData(): List<Test>
}