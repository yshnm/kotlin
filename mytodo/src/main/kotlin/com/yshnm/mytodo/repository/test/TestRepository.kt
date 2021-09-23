package com.yshnm.mytodo.repository.test

import com.yshnm.mytodo.entity.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository: JpaRepository<Test, Long> {
}