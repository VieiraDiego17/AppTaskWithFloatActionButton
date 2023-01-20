package com.example.apptarefas.viewModel

import android.app.Activity
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.example.apptarefas.dataSource.DataSource
import com.example.apptarefas.model.Task
import com.example.apptarefas.view.list.ListFragment
import com.google.gson.Gson
import java.text.FieldPosition

class ListViewModel(val fragment: ListFragment) : ViewModel() {

    private val taskDataSource = DataSource()

    val tasks = MutableLiveData<List<Task>>()

    fun loadUsers(context: Activity) {
        tasks.value = taskDataSource.getUsers(context)
    }

    class ListViewModelProvider(
        val fragment: ListFragment
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListViewModel(fragment) as T
        }
    }
}