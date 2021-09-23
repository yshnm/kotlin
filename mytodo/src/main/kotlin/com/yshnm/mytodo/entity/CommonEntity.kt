package com.yshnm.mytodo.entity

import com.yshnm.mytodo.utility.DateTimeUtil
import java.sql.Timestamp
import javax.persistence.*


@MappedSuperclass
open class CommonEntity {

    // タイトル
    @Column(name = "title")
    lateinit var title: String

    // 内容
    @Column(name = "detail")
    lateinit var detail: String

    // 完了フラグ
    @Column(name = "complete_flg")
    lateinit var completeFlg: String

    // 実行期限
    @Column(name = "execution_date")
    lateinit var executionDate: Timestamp

    // 作成日時
    @Column(name = "create_datetime")
    lateinit var createDatetime: Timestamp

    // 更新日時
    @Column(name = "update_datetime")
    lateinit var updateDatetime: Timestamp

    /**
     *  データ登録前に共通的に実行されるメソッド
     */
    @PrePersist
    fun preInsert(){
        // 現在日時
        val now : SystemDateTime = DateTimeUtil.getNowDateTime()

        // 作成日時
        this.createDatetime = now.toTimeStamp()

        // 更新日時
        this.updateDatetime = now.toTimeStamp()
    }

    /**
     * データ更新前に共通的に実行されるメソッド
     */
    @PreUpdate
    fun preUpdate() {

        // 現在日時
        val now : SystemDateTime = DateTimeUtil.getNowDateTime()

        // 更新日時
        this.updateDatetime = now.toTimeStamp()
    }
}