package com.yshnm.mytodo.repository

import com.yshnm.mytodo.entity.NextSubTask
import com.yshnm.mytodo.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface NextSubTaskRepository: JpaRepository<NextSubTask, Long>, JpaSpecificationExecutor<NextSubTask> {}