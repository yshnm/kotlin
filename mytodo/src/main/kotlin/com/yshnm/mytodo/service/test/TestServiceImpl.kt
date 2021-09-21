package com.yshnm.mytodo.service.test

import com.yshnm.mytodo.entity.TEST
import com.yshnm.mytodo.repository.test.TestRepository
import org.springframework.stereotype.Service

@Service
class TestServiceImpl(
    private val testRepository: TestRepository
): TestService {

    // テストデータ登録
    override fun insertTestData(test: TEST) {
        testRepository.save(test)
    }

    // 全件取得
    override fun findAllTestData(): List<TEST> {
        return testRepository.findAll()
    }

}