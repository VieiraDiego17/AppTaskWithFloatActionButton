package com.example.apptarefas.view.register

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.apptarefas.R
import com.example.apptarefas.model.Task
import com.example.apptarefas.resources.ImageContract
import com.example.apptarefas.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var viewModel: RegisterViewModel
    private var imageCar: Uri? = null

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
                    imageCar,
                    context = requireActivity()
                )

                viewModel.userSaved.observe(viewLifecycleOwner) {
                    val action = RegisterFragmentDirections.actionRegisterToList(
                        Task(
                            textTitle.text.toString(),
                            textDescription.text.toString(),
                            textUtelsils.text.toString(),
                            imageCar
                        )
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun callImage(){
        buttonSelectImage.setOnClickListener {
            getImage.launch(100)
        }
    }

    private fun setImage(it: Uri?) {
        imageCar = it
        imageGalery.setImageURI(it)
    }

    fun backToMenu(){
        buttonRegisterToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionRegisterToMenu)
        }
    }
    }
