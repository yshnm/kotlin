package com.yshnm.mytodo.entity

import com.yshnm.mytodo.const.Const
import com.yshnm.mytodo.utility.DateTimeUtil
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*


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
    var completeFlg: String = Const.FLG_OFF

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