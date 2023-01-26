package com.example.apptarefas.view.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(R.layout.fragment_image) {

    val args: ImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageReceived()
        buttonClicked()
    }

    fun imageReceived() {
        val imageReceived = args.imageReceived
        imageCarReceived.setImageURI(imageReceived.image)
    }

    fun buttonClicked(){
        imageDelete.setOnClickListener {
            findNavController().navigate(R.id.actionImageToDetails)
        }
    }
}