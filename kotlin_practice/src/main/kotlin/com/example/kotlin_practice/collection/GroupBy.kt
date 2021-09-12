package com.example.kotlin_practice.collection

object GroupBy {

    fun groupBy1() {

        // お友達の名簿をGroupBy
        val friends: Map<Int, List<Friend>> = listOf(
                Friend("takashi", 19),
                Friend("satoshi", 20),
                Friend("takao", 21),
                Friend("sadao", 20),
                Friend("hideki", 20),
                Friend("rikio", 21),
        ).groupBy { it.age }

        println("型：${friends.javaClass.kotlin}")
        println("-------------------------------")
        println("結果:${friends}")

    }

    fun groupBy2() {

        // お友達の名簿をGroupBy
        val friends: Map<Int, List<String>> = listOf(
                Friend("takashi", 19),
                Friend("satoshi", 20),
                Friend("takao", 21),
                Friend("sadao", 20),
                Friend("hideki", 20),
                Friend("rikio", 21),
        ).groupBy(
                { it.age},
                { "Mr." + it.name } // ValueをFriend型からString型に変化
        )

        println("型：${friends.javaClass.kotlin}")
        println("-------------------------------")
        println("結果:${friends}")


    }

    data class Friend(
            val name: String,
            val age: Int,
    )
}