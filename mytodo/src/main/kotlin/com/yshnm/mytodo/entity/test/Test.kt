package com.yshnm.mytodo.entity.test

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * spring jpaテスト用Entity (システム内では使用しない)
 */
@Entity
data class Test(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String
)
