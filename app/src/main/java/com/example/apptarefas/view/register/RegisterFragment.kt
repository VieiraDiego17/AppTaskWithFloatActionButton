package com.example.apptarefas.view.register

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.apptarefas.R
import com.example.apptarefas.model.Task
import com.example.apptarefas.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.jar.Manifest

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var dialog: AlertDialog

    companion object{
        private val PERMISSAO_GALERIA = android.Manifest.permission.READ_EXTERNAL_STORAGE
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
                    context = requireActivity()
                )

                viewModel.userSaved.observe(viewLifecycleOwner) {
                    val action = RegisterFragmentDirections.actionRegisterToList(
                        Task(
                            textTitle.text.toString(),
                            textDescription.text.toString(),
                            textUtelsils.text.toString()
                        )
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }



    fun backToMenu(){
        buttonRegisterToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionRegisterToMenu)
        }
    }
    }
