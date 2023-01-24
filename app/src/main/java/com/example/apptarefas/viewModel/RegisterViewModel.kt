package com.example.apptarefas.viewModel

import android.app.Activity
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptarefas.dataSource.DataSource
import com.example.apptarefas.model.Task
import com.example.apptarefas.view.register.RegisterFragment

class RegisterViewModel(val fragment: RegisterFragment): ViewModel() {

    private val userDataSource = DataSource()

    val userSaved = MutableLiveData<Unit>()

    fun saveUser(
        title: String,
        description: String,
        utensils: String,
        image: Uri?,
        context: Activity
    ) {
        userDataSource.saveUser(
            context = context,
            task = Task(
                title = title,
                description = description,
                utensils = utensils,
                imagem = image
            )
        )
        userSaved.value = Unit
    }

    class RegisterViewModelProvider(
        val fragment: RegisterFragment
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(fragment) as T
        }
    }
}