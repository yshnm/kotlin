package com.yshnm.mytodo.repository.test

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DataResetRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) {

    fun resetTestData(sql: String) {
        jdbcTemplate.batchUpdate(sql)
    }

}