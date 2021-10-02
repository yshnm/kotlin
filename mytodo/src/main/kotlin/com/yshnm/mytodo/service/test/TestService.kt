package com.yshnm.mytodo.service.test

import com.yshnm.mytodo.entity.test.Test

/**
 * spring jpa テスト用サービス
 */
interface TestService {

    // テストデータ登録
    fun insertTestData(test: Test)

    // 全件取得
    fun findAllTestData(): List<Test>

    // テストデータリセット
    fun resetTestData(sql: String)
}