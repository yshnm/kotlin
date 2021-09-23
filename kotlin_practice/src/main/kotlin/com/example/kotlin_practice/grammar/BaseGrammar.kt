package com.example.kotlin_practice.grammar

import java.lang.RuntimeException

/**
 * 基本文法
 */
object BaseGrammar {

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

        val temp3: String = "私は${age}歳です"


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
         val exception: Nothing = throw RuntimeException("throew RuntimeException!!")

         throw exception
    }

    // Null許容型 Nullの安全な呼び出し
    fun nullToleranceType() {

        /*
          ・null許容型は型の末尾「?」をつける。
          ・全てに型に対してnull許容・非許容を選択できる
          ・null非許容型にnullが代入されると「NullPointerException」が発生する
          ・null許容型で宣言すると
         */

        var v1 : Int = 0 // null非許容型
        // v1 = null // nullを割り当てられない。コンパイルエラーとなる

        var v2: Int? = 0 // null許容型
        v2 = null // nullを割り当てることができる

        /*
          Null安全な呼び出し
          Null許容型から関数やプロパティを直接呼び出せないため、Null許容型に対してNullでないことを事前に検証する必用がある；。
         */

        val v3: String? = "abc"
        val v4: String? = null

        // v3.length     v3がnullかもしれないので、直接プロパティを呼び出せない

        // v3がnullではないことを検証する
        // 方法① 事前にif文で検証
        if ( v3 != null) {
           v3.length
        }

        // 方法➁ ?を連結して呼び出し (nullの場合プロパティ呼び出しを行わない
        v3?.length
        println("v3 = ${v3?.length}") // v3 = 3
        println("v4 = ${v4?.length}") // v4 = null

        /*
          エルビス演算子
          エルビス演算子「?:」はnullであれば特定の処理を呼び出す
         */
        println("v4 = ${v4?.length ?: "0"}") // v4 = 0
    }

    // 列挙型定義
    enum class Color {
        RED, GREEN, BLUE
    }

    // 列挙型定義(引数有)
    enum class Subject(
            // 列挙型に値を持たせる
            private val point: Int, // privateの場合値を取得するメソッドを別に定義する必用有
            public val evaluation: String // publicの場合直接アクセス可能
    ) {
        MATH(93, "大変良い"),
        ENGLISH(77, "良い"),
        SCIENCE(75, "良い"); // ← 列挙型以外にメソッドを定義する場合「;」で終わらせる

        // 点数を取得
        fun getPoint() = this.point
    }


    //　列挙型
    fun enumType() {

        /*
          列挙型
         */

        // enum型宣言
        val red: Color = Color.RED
        val green: Color = Color.GREEN
        val blue: Color = Color.BLUE

        // 比較方法 Javaと違い「 == 」で比較できる
        println(red == Color.RED) // true
        println(red == Color.GREEN) // false


        // ordinal 列挙型の羅列の順番を返す(0始まり)
        println("RED ordinal ${red.ordinal}") //RED ordinal 0"
        println("BLUE ordinal ${blue.ordinal}") //RED ordinal 2"

        // name 列挙型の名前をString型で返す
        println("RED name ${red.name}")
        println("GREEN name ${green.name}")

        // valueOf 列挙型の名前から定数を取得する
        println(" valueOf  ${Color.valueOf("RED")}")
        println(" valueOf  ${Color.valueOf("BLUE")}")
        // 存在しない列挙型名称を渡すとIllegalArgumentExceptionが発生
        // println(" valueOf  ${Color.valueOf("YELLOW")}")

        // values 列挙型の配列を返す(ソートは宣言順)
        Color.values().forEach { println(it) } // RED GREEN BLUE

        /*
          列挙型(引数有)
         */

        // 定義された値にアクセスする
        // Subject.ENGLISH.point   privateで定義されているので直接アクセス不可。別途メソッドを定義して値を呼び出す
        println("ENGLISH point = ${Subject.ENGLISH.getPoint()}") // ENGLISH point = 77
        // publicで定義されている場合は直接アクセス可能
        println("ENGLISH evaluation = ${Subject.ENGLISH.evaluation}") // ENGLISH evaluation = 良い
    }

    // 型推論
    fun typeInference() {

        /*
          変数の宣言時に明示的に型を指定しない場合、代入された値から型推論する
          ため型宣言を省略することができる
         */
        val v1 = 1
        println("v1 =  ${v1.javaClass.kotlin}") // v1 = class kotlin.Int
        val v2 = "2"
        println("v2 =  ${v2.javaClass.kotlin}") // v2 =  class kotlin.String
        val v3 = listOf(1, 2)
        println("v3 =  ${v3.javaClass.kotlin}") // v3 =  class java.util.Arrays$ArrayList
        val v4 = mapOf( "1" to 1, "2" to 2)
        println("v4 =  ${v4.javaClass.kotlin}") // v4 =  class java.util.LinkedHashMap

        // 型推論により型として認識されるため、代入する型がことなる場合はコンパイルエラーが発生する
        var int = 123
        var double = 12.3

        // int = double  ←コンパイルエラー
    }

    data class User(
            val id: Int
    )

    // 演算子
    fun operator() {

        /*
          単項演算子
         */
        var count: Int = 1

        println(count++) // 1 (値を表示された後にインクリメント)
        println(count--) // 2 (値を表示された後にデクリメント)

        println(++count) // 2 (値を表示させる前にインクリメント)
        println(--count) // 1 (値を表示させる前にデクリメント)

        println(!true) // false (true/falseを反転)

        /*
          範囲を指定する演算子 in
         */
        for(i in 1..5) {
            println(i) // 1 2 3 4 5
        }

        for(s in 'a'..'e' ) {
            println(s) // a b c d e
        }

        /*
          代入演算子
         */
        var result = 10

        // result = result + 5と同義
        result += 5
        println(result) // 15

        // result = result - 5と同義
        result -= 5
        println(result) // 10

        // result = result * 5と同義
        result *= 5
        println(result) // 50

        // result = result / 5と同義
        result /= 5
        println(result) // 10

        // result = result %R 5と同義
        result %= 5
        println(result) // 0

        /*
          比較演算子
         */
        println("abc" == "abc") // true
        println("abc" != "abc") // false

        val user1 = User(id = 1)
        val user2 = User(id = 1)

        println("user1 == user2 : ${user1 == user2}") // true
        println("user1 === user2 : ${user1 === user2}") // false 参照先が異なるため
    }

    // 制御構文
    fun controlSyntax() {

        // if式
        if (true) {
            // 判定式がtrueの時に実行
        }  else {
            // 判定式がfalseの時に実行
        }

        if(true) 1 else 2 // 1行で書くこともできる。その場合「 {} 」は省力可能

        // 戻り値を返すことができる
        val age: Int = if(true) 25 else 15

        /*
          when式
         */
        val color: String = "red"

        when(color) {
            "red" -> "赤" // 改行ごとの「,」は不要
            "blue" -> "青"
            "green" -> {
                "緑"
            } // 処理が複数行に及ぶ場合は「 {} 」を記述できる
        }

        // 戻り値を返すことができる
        val choiceColor = when(color) {
            "red" -> "赤" // 改行ごとの「,」は不要
            "blue" -> "青"
            "green" -> 1 // 型推奨にすれば値によって返す型を返ることもできる
            else -> "白" // 戻り値を返す場合は該当なしの場合の「else」は必須
        }

        // enumクラスを条件に指定して全ての条件を網羅した場合、「else」は不要
        val enumColor = when (Subject.MATH) {
            Subject.MATH -> "数学"
            Subject.ENGLISH -> "英語"
            Subject.SCIENCE -> "化学"
        }

        /*
          for文
         */
        for(i in 1..100) {
            println(i) // 1から100まで出力される
        }

        for(i in listOf("あ","い","う")) {
            println(i) // あ　い　う
        }

        /*
          ラベル
          ラベルを付けることにより、forが2つ以上ある場合にラベル付のfor文に対して
          breakやcontinueが指定できる
         */
        loop@ for(a in 1..5) { // ルートのfor文にloop@ラベルを指定する
            for(b in 1..5) {
                if(a == 2 && b == 5) {
                    break@loop
                }
                println("a:$a b:$b")
            }
        }

        /*
          while文
         */
        var i = 10

        while(i > 0) {
            println(i)
            i-- // 10から1まで出力
        }

        i = 10
         do {
             println(i)
             i-- // 10から1まで出力

         } while(i > 0)

    }

    // コレクション
    fun collections() {

        /*
          通常のリスト（イミュータブル）
          要素の追加・削除ができない
          要素の変更はバグの原因となりやすいため、イミュータブルなリストの使用を推奨
         */
        val iMutableList = listOf(1,2,3)
        println("iMutableList : $iMutableList") // 1,2,3

        // iMutableList += 4 イミュータブルなので要素の追加は行えない
        // iMutableList -= 3 イミュータブルなので要素の削除は行えない

        /*
          ミュータブルリスト
          要素の追加・削除が可能
          要素の変更はバグの原因となりやすいため、可能な限り使用しないほうが良い
         */
        val mutableList = mutableListOf(1,2,3,3)
        mutableList += 4 // 要素の追加(最後尾に追加する値を直接指定)
        mutableList -= 3 // 要素の削除(値と一番最初に一致した要素を削除)
        println("mutableList : $mutableList") // 1,2,3,4

        /*
          セット
          ・一意の要素を保持できる
          ・ミュータブルとイミュータブルのセットがそれぞれ用意されている
         */
        val iMutableSet = setOf(1,2,3,3)
        println("iMutableSet : $iMutableSet") // 1 2 3 (一意の要素を保持)

        val mutableSet = mutableSetOf(1,2,3,3)
        mutableSet += 4 // 要素の追加(最後尾に追加する値を直接指定)
        mutableSet -= 3 // 要素の削除(値と一番最初に一致した要素を削除)
        println("mutableSet : $mutableSet") // 1,2,4 (一意の要素を保持)

        /*
          マップ
          キーと値のペアのセットを持つコレクション
         */
        val iMutableMap = mapOf("phone" to "000-111-222", "address" to "123")
        // iMutableMap["address"] = "1234" イミュータブルなので値の変更ができない

        val mutableMap = mutableMapOf("phone" to "000-111-222", "address" to "123")
        mutableMap["address"] = "1234"
        println("mutableMap : $mutableMap")
    }

    // コレクションの操作
    fun ssss(){

        val list = listOf(1,2,3,4,5,6)

        // map



    }





}