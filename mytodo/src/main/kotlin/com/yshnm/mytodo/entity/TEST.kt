package com.yshnm.mytodo.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class TEST(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String
)
