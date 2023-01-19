package com.example.apptarefas.dataSource

import android.app.Activity
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Task

class DataSource {
    fun saveUser(context: Activity, task: Task) {
        Banco.saveUser(task)
    }

    fun deleteUser(context: Activity, task: Task) {
        Banco.deleteUser(task)
    }

    fun getUsers(context: Activity): List<Task> {
        return Banco.getUsers()
    }

    fun editUser(context: Activity, task: Task){
        Banco.editUser(task)
    }
}