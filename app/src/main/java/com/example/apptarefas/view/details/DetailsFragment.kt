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
import kotlinx.android.synthetic.main.fragment_details.openFloatButton
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_register.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    private var floatActionButtonVisible = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatButtonCLicked()
        actionOnFlotButton()
        receivedList()
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

        var imageCar = imageCarImg
        args.task.imagem?.let { image ->
            imageCar.setImageURI(image)
        }
    }

    fun floatButtonCLicked(){
            floatActionButtonVisible = false

            openFloatButton.setOnClickListener {
                if (!floatActionButtonVisible) {
                    saveFloatButton.show()
                    deleteFloatButton.show()
                    detaislToList.show()
                    detailsToMenu.show()

                    saveFloatButton.visibility = View.VISIBLE
                    deleteFloatButton.visibility = View.VISIBLE
                    detaislToList.visibility = View.VISIBLE
                    detailsToMenu.visibility = View.VISIBLE

                    openFloatButton.setImageResource(R.drawable.ic_close)

                    floatActionButtonVisible = true
                } else {
                    saveFloatButton.hide()
                    deleteFloatButton.hide()
                    detailsToMenu.hide()
                    detaislToList.hide()

                    saveFloatButton.visibility = View.GONE
                    deleteFloatButton.visibility = View.GONE
                    detaislToList.visibility = View.GONE
                    detailsToMenu.visibility = View.GONE

                    openFloatButton.setImageResource(R.drawable.ic_add)

                    floatActionButtonVisible = false
            }
        }
    }

    fun actionOnFlotButton(){

        deleteFloatButton.setOnClickListener {
            Banco.deleteTask(args.task)
            findNavController().navigate(R.id.actionDetailsToList)
        }

        saveFloatButton.setOnClickListener {
            Banco.alterTask(args.task, Task(
                title = titleDetails.text.toString(),
                description = descriptionDetails.text.toString(),
                utensils = utensilsDetails.text.toString(),
                imagem = args.task.imagem
            ))
            findNavController().navigate(R.id.actionDetailsToList)
        }

        detailsToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionDetailsToMenu)
        }

        detaislToList.setOnClickListener {
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