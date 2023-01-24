package com.example.apptarefas.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Task
import com.example.apptarefas.resources.ListTaskAdapter
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var listAdapter: ListTaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receivedList()
        setClicked()
        clickEditOff()

        titleDetails.isEnabled = false
        descriptionDetails.isEnabled = false
        utensilsDetails.isEnabled = false
    }

    fun receivedList() {
        titleDetails.setText(args.task.title)
        descriptionDetails.setText(args.task.description)
        utensilsDetails.setText(args.task.utensils)
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

        val imageCar = imageCarDetail
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

}