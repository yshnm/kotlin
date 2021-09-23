package com.yshnm.mytodo.entity

import java.sql.Timestamp
import java.time.LocalDateTime

/**
 * 共通日付クラス
 */
class SystemDateTime(
    private val systemDateTime: LocalDateTime
) {
    // システム日時をタイムスタンプ型で返す
    fun toTimeStamp(): Timestamp {
        return Timestamp.valueOf(this.systemDateTime)
    }
}