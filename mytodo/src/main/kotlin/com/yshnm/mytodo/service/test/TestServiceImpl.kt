package com.yshnm.mytodo.service.test

import com.yshnm.mytodo.entity.test.Test
import com.yshnm.mytodo.repository.test.DataResetRepositoryImpl
import com.yshnm.mytodo.repository.test.TestRepository
import org.springframework.stereotype.Service

/**
 * spring jpa テスト用サービス実装
 */
@Service
class TestServiceImpl(
    private val testRepository: TestRepository,
    private val dataResetRepositoryImpl: DataResetRepositoryImpl

): TestService {

    // テストデータ登録
    override fun insertTestData(test: Test) {
        testRepository.save(test)
    }

    // 全件取得
    override fun findAllTestData(): List<Test> {
        val a :List<Test> = testRepository.findAll()
        return a
    }

    // 初期テストデータリセット
    override fun resetTestData(sql: String) {
        dataResetRepositoryImpl.resetTestData(sql)
    }

}