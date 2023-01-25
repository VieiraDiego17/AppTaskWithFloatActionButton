package com.example.apptarefas.view.image

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.model.Image
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_image.*
import java.net.URI

class ImageFragment : Fragment(R.layout.fragment_image) {

    val args: ImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageReceived()
    }

    fun imageReceived() {
        val jsonConverted = args.imageReceived
        imageCarReceived.setImageURI(jsonConverted.image)
    }
}