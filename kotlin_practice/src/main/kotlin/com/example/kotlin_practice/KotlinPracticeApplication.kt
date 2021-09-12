package com.example.kotlin_practice

import com.example.kotlin_practice.collection.GroupBy
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPracticeApplication

fun main(args: Array<String>) {


	GroupBy.groupBy2()

	runApplication<KotlinPracticeApplication>(*args)
}
