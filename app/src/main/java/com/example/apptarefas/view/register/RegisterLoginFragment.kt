package com.example.apptarefas.view.register

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apptarefas.R
import com.example.apptarefas.model.Image
import com.example.apptarefas.model.Login
import com.example.apptarefas.resources.ImageContract
import com.example.apptarefas.view.details.DetailsFragmentDirections
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_register_login.*

class RegisterLoginFragment : Fragment(R.layout.fragment_register_login) {

    private var image: Uri? = null

    private val getImage = registerForActivityResult(
        ImageContract()
    ) {
        setImage(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonClicked()
        callImage()
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
        } else if (textPasswordLogin.text.toString() != textPasswordLogin2.text.toString()) {
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

            //TODO = BUSCAR IMAGEM NA GALERIA E ENVIAR PARA TELA DE LOGIN
            //var img = Image(image)

            val action = RegisterLoginFragmentDirections.actionRegisterLoginToLogin(
                login,

                //TODO = AQUI
                //img
            )
            findNavController().navigate(action)
        }
    }

    fun setButtonClicked() {
        buttonRegisterToLogin.setOnClickListener {
            createLogin()
        }
    }

    private fun callImage() {
        imageGaleryPerfil.setOnClickListener {
            getImage.launch(100)
        }
    }

    private fun setImage(it: Uri?) {
        image = it
        imageGaleryPerfil.setImageURI(it)
    }
}