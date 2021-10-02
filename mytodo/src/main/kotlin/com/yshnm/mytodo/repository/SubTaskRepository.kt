package com.yshnm.mytodo.repository

import com.yshnm.mytodo.entity.SubTask
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * サブタスク用リポジトリ
 */
@Repository
interface SubTaskRepository: JpaRepository<SubTask, Long>, JpaSpecificationExecutor<SubTask>