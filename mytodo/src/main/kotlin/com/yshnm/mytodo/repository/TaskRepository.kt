package com.yshnm.mytodo.repository

import com.yshnm.mytodo.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * タスク用リポジトリ
 */
@Repository
interface TaskRepository: JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>