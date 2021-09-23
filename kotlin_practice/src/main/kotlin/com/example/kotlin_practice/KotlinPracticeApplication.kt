package com.example.kotlin_practice

import com.example.kotlin_practice.collection.GroupBy
import com.example.kotlin_practice.grammar.BaseGrammar
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPracticeApplication

fun main(args: Array<String>) {


	BaseGrammar.operator()

	runApplication<KotlinPracticeApplication>(*args)
}
