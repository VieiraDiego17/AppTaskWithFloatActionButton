package com.example.apptarefas.banco

import com.example.apptarefas.model.BancoObject
import com.example.apptarefas.model.Task

object Banco {
    private var bancoObject = BancoObject()
    private lateinit var items: List<Task>

    fun saveUser(task: Task) {
        bancoObject.list.add(task)
    }

    fun deleteUser(task: Task) {
        bancoObject.list.remove(task)
    }

    fun getUsers() = bancoObject.list

    fun editUser(task: Task){
        bancoObject.list.set(0,task)
    }
}