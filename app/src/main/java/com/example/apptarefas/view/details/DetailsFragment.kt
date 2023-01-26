package com.example.apptarefas.view.details


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Image
import com.example.apptarefas.model.Task
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_register.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receivedList()
        setClicked()
        clickEditOff()
        sendImage()

        titleDetails.isEnabled = false
        descriptionDetails.isEnabled = false
        utensilsDetails.isEnabled = false
    }

    fun receivedList() {
        titleDetails.setText(args.task.title)
        descriptionDetails.setText(args.task.description)
        utensilsDetails.setText(args.task.utensils)
        imageCar1
    }

    fun clickEditOff(){
        imageEdit1.setOnClickListener {
            titleDetails.isEnabled = true
        }

        imageEdit2.setOnClickListener {
            descriptionDetails.isEnabled = true
        }

        imageEdit3.setOnClickListener {
            utensilsDetails.isEnabled = true
        }
    }

    fun setClicked(){
        buttonDetailsToList.setOnClickListener {
            findNavController().navigate(R.id.actionDetailsToList)
        }

        buttonDetailsToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionDetailsToMenu)
        }

        buttonDelete.setOnClickListener {
            Banco.deleteTask(args.task)
            findNavController().navigate(R.id.actionDetailsToList)
        }

        var imageCar = imageCarImg
        args.task.imagem?.let { image ->
            imageCar.setImageURI(image)
        }

        buttonSaveEdit.setOnClickListener {
            Banco.alterTask(args.task, Task(
                title = titleDetails.text.toString(),
                description = descriptionDetails.text.toString(),
                utensils = utensilsDetails.text.toString(),
                imagem = args.task.imagem
            ))
            findNavController().navigate(R.id.actionDetailsToList)
        }
    }

    fun sendImage(){
        val image = Image(args.task.imagem)

        imageCarImg.setOnClickListener {
            var action = DetailsFragmentDirections.actionDetailsToImage(
                image
            )
            findNavController().navigate(action)
        }
    }
}