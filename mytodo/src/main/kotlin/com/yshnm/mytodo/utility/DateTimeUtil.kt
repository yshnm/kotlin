package com.yshnm.mytodo.utility

import com.yshnm.mytodo.entity.SystemDateTime
import java.time.LocalDateTime

/**
 * 日付取得用utilityクラス
 */
object DateTimeUtil {

    /**
     * 現在日付をsystemDateTime型で取得する
     */
    fun getNowDateTime(): SystemDateTime {
        return SystemDateTime(LocalDateTime.now())
    }

}