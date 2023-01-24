package com.example.apptarefas.banco

import com.example.apptarefas.model.BancoObject
import com.example.apptarefas.model.Task

object Banco {
    private var bancoObject = BancoObject()

    fun saveUser(task: Task) {
        bancoObject.list.add(task)
    }

    fun deleteTask(task: Task) {
        bancoObject.list.remove(task)
    }

    fun alterTask(task: Task, taskAtualizada: Task){
        deleteTask(task)
        saveUser(taskAtualizada)
    }

    fun getUsers() = bancoObject.list
}