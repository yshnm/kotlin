package com.yshnm.mytodo.entity

import com.yshnm.mytodo.const.CommonConst
import java.sql.Timestamp
import javax.persistence.*

/**
 * タスク・サブタスクの共通項目を定義した親クラスEntity
 */
@MappedSuperclass
open class CommonEntity {

    // タイトル
    @Column(name = "title")
    var title: String = ""

    // 内容
    @Column(name = "detail")
    var detail: String = ""

    // 完了フラグ
    @Column(name = "complete_flg")
    var completeFlg: String = CommonConst.FLG_OFF

    // 実行期限
    @Column(name = "execution_date")
    var executionDate: Timestamp? = null

    // 作成日時
    @Column(name = "create_datetime")
    var createDatetime: Timestamp? = null

    // 更新日時
    @Column(name = "update_datetime")
    var updateDatetime: Timestamp? = null
}