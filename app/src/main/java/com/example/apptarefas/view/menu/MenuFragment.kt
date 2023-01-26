package com.example.apptarefas.view.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.model.Image
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val args: MenuFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClick()

        //TODO = AQUI
        //setImage()
    }

    //TODO = SETAR IMAGEM NO CAMPO -imageViewPerfil-
//    fun setImage(){
//        imageViewPerfil = args.imagePerfil
//    }

    fun setClick() {
        buttonMenuToRegister.setOnClickListener {
            findNavController().navigate(R.id.actionMenuToRegister)
        }

        buttonMenuToList.setOnClickListener {
            findNavController().navigate(R.id.actionMenuToList)
        }
    }

}