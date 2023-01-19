package com.example.apptarefas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptarefas.model.Login
import com.example.apptarefas.view.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_register_login.*

class LoginViewModel(val fragmentLogin: LoginFragment): ViewModel() {

    fun validateLogin(login: Login, callback: (result:(Boolean)) -> Unit){
        if(login.email != fragmentLogin.textEmailLogin.text.toString() ||
                login.password != fragmentLogin.textPasswordLogin.text.toString()){
            callback.invoke(false)
        }else{
            callback.invoke(true)
        }
    }

    class LoginViewModelProvider(
        val fragmentLogin: LoginFragment
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(fragmentLogin) as T
        }
    }
}