package com.yshnm.mytodo.utility

import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.enum.TaskKind
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Root

object SpecificationCreateUtil {

    /**
     * JPA 検索条件
     * 完了済フラグ = "1"（完了済み）
     *
     * @param completeFlg 完了フラグ
     */
    fun <T: CommonEntity> equalCompleteFlg(completeFlg: String): Specification<T> {
        return Specification { root: Root<T>, criteriaQuery, criteriaBuilder ->
            criteriaBuilder.equal(root.get<String>("completeFlg"), completeFlg)
        }
    }

    /**
     * JPA 検索条件
     * タスクID = 引数．タスクID
     *
     * @param taskId タスクID
     * @param taskKind タスク種別
     */
    fun <T: CommonEntity> equalTaskId(taskId: Int?, taskKind: TaskKind): Specification<T>? {

        if(taskId == null) { return null }

        return Specification { root: Root<T>, criteriaQuery, criteriaBuilder ->

            if(taskKind == TaskKind.TASK) {
                criteriaBuilder.equal(root.get<Int>("taskId"), taskId)
            } else {
                criteriaBuilder.equal(root.get<T>("primaryKey").get<Int>("taskId"), taskId)
            }
        }
    }

    /**
     * JPA 検索条件
     * サブタスクID = 引数．サブタスクID
     *
     * @param subTaskId サブタスクID
     */
    fun <T: CommonEntity> equalSubTaskId(subTaskId: Int?): Specification<T>? {

        if(subTaskId == null) { return null }

        return Specification { root: Root<T>, criteriaQuery, criteriaBuilder ->
            criteriaBuilder.equal(root.get<T>("primaryKey").get<Int>("subTaskId"), subTaskId)
        }
    }
}