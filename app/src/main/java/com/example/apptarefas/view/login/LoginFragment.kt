package com.example.apptarefas.view.login

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.model.Image
import com.example.apptarefas.model.Login
import com.example.apptarefas.resources.ImageContract
import com.example.apptarefas.view.details.DetailsFragmentDirections
import com.example.apptarefas.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val args: LoginFragmentArgs by navArgs()
    private lateinit var viewModel: LoginViewModel
    private var imagemCar: Uri? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            LoginViewModel.LoginViewModelProvider(
                this))[LoginViewModel::class.java]

        setButtonClicked()
    }

    fun callValidateLogin(login: Login){
        viewModel.validateLogin(login){
            if (it){
                findNavController().navigate(R.id.actionLoginToHome)
            }else{
                Toast.makeText(
                    requireContext(),
                    "Login inválido! Verifique seus dados e tente novamente.",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setButtonClicked() {
        buttonLoginToHome.setOnClickListener {
            args.login?.let { it -> callValidateLogin(it) }

            //TODO = ENVIAR IMAGEM PARA TELA MENU
            //args.imageUser.let { it }
        }

        buttonLoginToRegister.setOnClickListener {
            findNavController().navigate(R.id.actionLoginToRegisterLogin)
        }
    }

}