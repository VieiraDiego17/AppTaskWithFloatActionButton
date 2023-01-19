package com.example.apptarefas.view.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apptarefas.R
import com.example.apptarefas.model.Login
import kotlinx.android.synthetic.main.fragment_register_login.*

class RegisterLoginFragment : Fragment(R.layout.fragment_register_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonClicked()
    }

    private fun createLogin() {

        if (textEmailLogin.text.toString().equals("") || textPasswordLogin.text.toString()
                .equals("")
            || textPasswordLogin2.text.toString().equals("")
        ) {
            Toast.makeText(
                this.activity,
                "Todos os campos precisam ser preenchidos",
                Toast.LENGTH_LONG
            ).show()
        }else if(textPasswordLogin.text.toString() != textPasswordLogin2.text.toString()){
            Toast.makeText(
                this.activity,
                "As senhas precisam ser iguais",
                Toast.LENGTH_LONG
            ).show()
        } else {
            var login = Login(
                textEmailLogin.text.toString(),
                textPasswordLogin.text.toString()
            )
            val action = RegisterLoginFragmentDirections.actionRegisterLoginToLogin(
                login
            )
            findNavController().navigate(action)
        }
    }


    fun setButtonClicked(){
        buttonRegisterToLogin.setOnClickListener {
            createLogin()
        }
    }
}