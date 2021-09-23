package com.yshnm.mytodo.utility

import com.yshnm.mytodo.const.FLG
import com.yshnm.mytodo.entity.*
import com.yshnm.mytodo.enum.TaskKind
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Root

object SpecificationCreateUtil {

    /**
     * JPA 検索条件
     * 完了済フラグ = "1"（完了済み）
     */
    fun <T: CommonEntity> equalCompleteFlg(completeFlg: String): Specification<T> {
        return Specification { root: Root<T>, criteriaQuery, criteriaBuilder ->
            criteriaBuilder.equal(root.get<String>("completeFlg"), completeFlg)
        }
    }

    /**
     * JPA 検索条件
     * タスクID = 引数．タスクID
     */
    fun <T: CommonEntity> taskIdEqual(taskId: Int?, taskKind: TaskKind): Specification<T>? {

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
     * タスクID = 引数．タスクID
     * and サブタスクID = 引数．サブタスクID
     */
    fun <T: CommonEntity>subTaskIdEqual(subTaskId: Int?): Specification<T>? {

        if(subTaskId == null) { return null }

        return Specification { root: Root<T>, criteriaQuery, criteriaBuilder ->
            criteriaBuilder.equal(root.get<T>("primaryKey").get<Int>("subTaskId"), subTaskId)
        }
    }

    /**
     * JPA 検索条件
     * タスクID = 引数．タスクID
     * and サブタスクID = 引数．サブタスクID
     */
    fun nextSubTaskIdEqual(nextSubTaskId: Int?): Specification<NextSubTask>? {

        if(nextSubTaskId == null) { return null }

        return Specification { root: Root<NextSubTask>, criteriaQuery, criteriaBuilder ->
            criteriaBuilder.equal(root.get<NextSubTaskKey>("primaryKey").get<Int>("nextSubTaskId"), nextSubTaskId)
        }
    }


}