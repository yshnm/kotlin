package com.yshnm.mytodo.service.test

import com.yshnm.mytodo.entity.TEST

interface TestService {

    // テストデータ登録
    fun insertTestData(test: TEST)

    // 全件取得
    fun findAllTestData(): List<TEST>
}