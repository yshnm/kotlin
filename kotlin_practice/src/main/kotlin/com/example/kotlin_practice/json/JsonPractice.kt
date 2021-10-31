package com.example.kotlin_practice.json

class JsonPractice {

    fun create() {

        val s0 = mapOf("text" to "key0", "value" to "val0")
        val s1 = mapOf("text" to "key1", "value" to "val1")
        val s2 = mapOf("text" to "key2", "value" to "val2")
        val s3 = mapOf("text" to "key3", "value" to "val3")

        val ss1 = arrayOf(s0, s1, s2)
        val ss2 = arrayOf(s3)

        val r1 = jacksonObjectMapper().writeValueAsString(ss1)
        val r2 = jacksonObjectMapper().writeValueAsString(ss2)

        val o = listOf(
            EE(
                name = "ss1",
                json = r1
            ),
            EE(
                name = "ss2",
                json = r2
            )
        )

    }
}