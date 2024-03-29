package com.example.apptarefas.view.register

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.apptarefas.R
import com.example.apptarefas.model.Task
import com.example.apptarefas.resources.ImageContract
import com.example.apptarefas.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.textDescription
import kotlinx.android.synthetic.main.fragment_register.textTitle


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var viewModel: RegisterViewModel
    private var imagemCar: Uri? = null

    private val getImage = registerForActivityResult(
        ImageContract()
    ){
        setImage(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            RegisterViewModel.RegisterViewModelProvider(
                this))[RegisterViewModel::class.java]

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backToMenu()
        setClick()
    }

    private fun setClick(){
        createTask()
        callImage()
    }

    private fun createTask() {
        buttonRegisterToList.setOnClickListener {

            if (textTitle.text.toString().equals("") || textDescription.text.toString()
                    .equals("") || textUtelsils.text.toString().equals("")
            ) {
                Toast.makeText(
                    this.activity,
                    "Todos os campos precisam ser preenchidos",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                viewModel.saveUser(
                    title = textTitle.text.toString(),
                    description = textDescription.text.toString(),
                    utensils = textUtelsils.text.toString(),
                    image = imagemCar,
                    context = requireActivity()
                )

                    val action = RegisterFragmentDirections.actionRegisterToList(
                        Task(
                            textTitle.text.toString(),
                            textDescription.text.toString(),
                            textUtelsils.text.toString(),
                            imagemCar
                        )
                    )
                    findNavController().navigate(action)

            }
        }
    }

    private fun callImage(){
        imageCar1?.setOnClickListener {
            getImage.launch(100)
        }
    }

    private fun setImage(it: Uri?) {
        imagemCar = it
        imageCar1.setImageURI(it)
    }

    fun backToMenu(){
        buttonRegisterToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionRegisterToMenu)
        }
    }
    }
