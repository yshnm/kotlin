package com.yshnm.mytodo.repository.test

import com.yshnm.mytodo.entity.test.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * spring jpa テスト用リポジトリ
 */
@Repository
interface TestRepository: JpaRepository<Test, Long> {
}