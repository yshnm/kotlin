package com.yshnm.mytodo.enum

enum class DisplayTemplate(
    private val template: String
) {
    TODO_LIST("todo_list"),
    UPDATE("update");

    fun getTemplate() = this.template
}