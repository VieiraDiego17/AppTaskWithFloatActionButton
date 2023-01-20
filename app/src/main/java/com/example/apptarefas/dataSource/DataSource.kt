package com.example.apptarefas.dataSource

import android.app.Activity
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Task
import java.text.FieldPosition

class DataSource {
    fun saveUser(context: Activity, task: Task) {
        Banco.saveUser(task)
    }

//    fun deleteTask(context: Activity, task: Task) {
//        Banco.deleteUser(task)
//    }

    fun getUsers(context: Activity): List<Task> {
        return Banco.getUsers()
    }
}