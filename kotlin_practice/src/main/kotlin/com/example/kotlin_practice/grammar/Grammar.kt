package com.example.kotlin_practice.grammar

import java.lang.RuntimeException

/**
 * 基本文法
 */
class Grammar {

    // 変数の種類
    fun valiableKind() {

        // val 再代入不可
        val countVal: Int = 0
        // countVar ++; :コンパイルエラー

        // var 再代入が可能
        // 変数の値の再代入はバグの原因となりやすいため、原則使用しない。
        var countVar: Int = 0
        countVar = 5
        print(countVar)

    }

    // 基本型
    fun baseType(){

        // 文字列
        val kata1: String = ""
        // 数値 (32ビットまで)
        val kata2: Int = 0
        // 数値 (64ビットまで)
        val kata3: Long = 1000000000000000000
        // 真偽地
        val kata4: Boolean = true
        // 単一文字 (シングルクォートで囲む)
        val kata5: Char = '1'
        // 小数 (32ビットまで)
        val kata6: Float = 1.5F
        // 小数 (64ビットまで)
        val kata7: Double = 1.5555555555555555555555
        // 要素の操作を行えないリスト
        val kata8: List<String> = emptyList()
        // 要素の操作を行えるリスト。バグの原因となりやすいので原則使用しない
        val kata9: List<String> = mutableListOf()


        // 変数宣言時に型の指定がない場合は類推してくれる。
        val kata10 = ""
    }

    // 文字列型
    fun stringType(){

        // ${}で文字列に変数を埋め込むことができる
        val age: String = "28"
        val temp1: String = "私は${age}歳です"
        // 計算も可能
        val temp2: String = "私は${age + 10 * 5}歳です"

        // 文字列型の比較は "=="でOK
        val name1: String = "taro"
        val name2: String = "taro"

        if(name1 == name2) print(true) else print("false")

        // 改行入り文字列
        // trimMargin()は「|」を取り除き文字列展開する。エスケープ不要
        val a = """
            |Hello \n
            |World
        """.trimMargin()

        // 文字列はコレクションとして扱える
        val helloWorld: String  = "hello world"
        print(helloWorld[4])

    }

    // Any型
    fun anyType() {

        /*
         Any型は全ての親クラス。型が不明なクラスを扱う時、Any型を付与することで
         型チェックを無効にし、コンパイルを通すことができるが、Kotlinの型安全
         の恩恵を受けることができないため可能な限りAny型は使用しないことが望ましい
         */
        var anyType: Any = 1
        anyType = "any"
        anyType = 1.55

        // 実行するとコンパイルエラーにならず、最後に代入したDouble型の値が出力される。
        print(anyType.toString() + " " + anyType.javaClass)
    }

    // Unit型
    fun unitType() {
        /*
          Unit型は型がないことを表す。一般的には値を返さない関数の戻り値として利用する。
          関数宣言「fun unitType() {}」は「fun unitType(): Unit {}」を省略した
          記述。
         */
        return Unit
    }

    // Nothing型
    fun nothingType() {
        /*
         Nothing型は全てのサブクラス。またNothing型は存在しない値を表し、これは決して完了しないことを意味する。
         値が返されないため、例外をスローする際等に利用される。
         */
         // val exception: Nothing = throw RuntimeException("RuntimeException!!")
         // throw exception
    }

}