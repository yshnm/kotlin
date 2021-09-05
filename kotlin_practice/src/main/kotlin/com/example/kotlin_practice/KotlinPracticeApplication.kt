package com.example.kotlin_practice

import com.example.kotlin_practice.grammar.Grammar
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPracticeApplication

fun main(args: Array<String>) {


	Grammar().nothingType()

	runApplication<KotlinPracticeApplication>(*args)
}
