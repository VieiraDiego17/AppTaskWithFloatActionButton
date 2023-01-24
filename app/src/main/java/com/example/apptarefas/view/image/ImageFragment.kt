package com.example.apptarefas.view.image

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(R.layout.fragment_image) {

    val args: ImageFragmentArgs by navArgs()
    lateinit var jsonConverted: Uri
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageReceived()
    }

    fun imageReceived() {
         jsonConverted = Gson().fromJson(args.imageReceived, Uri::class.java)
        imageCarReceived.setImageURI(jsonConverted)
    }
}