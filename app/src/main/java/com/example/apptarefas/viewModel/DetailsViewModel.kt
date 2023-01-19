package com.example.apptarefas.viewModel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptarefas.dataSource.DataSource
import com.example.apptarefas.model.Task
import com.example.apptarefas.view.details.DetailsFragment

class DetailsViewModel(val fragment: DetailsFragment) : ViewModel(){

    private val taskDataSource = DataSource()
    val tasks = MutableLiveData<List<Task>>()
    val userSaved = MutableLiveData<Unit>()

    fun loadUsers(context: Activity) {
        tasks.value = taskDataSource.getUsers(context)
    }

    fun saveUser(
        title: String,
        description: String,
        utensils: String,
        context: Activity
    ) {
        taskDataSource.saveUser(
            context = context,
            task = Task(
                title = title,
                description = description,
                utensils = utensils
            )
        )
        userSaved.value = Unit
    }

    fun editUser(
        task: Task,
        context: Activity
    ){
        taskDataSource.editUser(
            context = context,
            task = task
        )
        loadUsers(context)
    }

    class DetailsViewModelProvider(
        val fragment: DetailsFragment
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(fragment) as T
        }
    }
}