package com.example.apptarefas.resources

import com.example.apptarefas.model.Task

class DataSourceList {
    companion object {
        fun castToList(task: Task) : List<Task> {
            return listOf(task)
        }
    }
}