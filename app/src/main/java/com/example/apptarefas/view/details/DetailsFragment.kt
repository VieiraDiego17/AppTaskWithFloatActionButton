package com.example.apptarefas.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apptarefas.R
import com.example.apptarefas.resources.ListTaskAdapter
import com.example.apptarefas.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var listAdapter: ListTaskAdapter
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            DetailsViewModel.DetailsViewModelProvider(
                this
            )
        )[DetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receivedList()
        setClicked()
        clickEditOff()
        //saveChange()

        titleDetails.isEnabled = false
        descriptionDetails.isEnabled = false
        utensilsDetails.isEnabled = false
    }

    fun saveChange(){

        buttonSaveEdit.setOnClickListener {
        listAdapter = ListTaskAdapter {
                var action = DetailsFragmentDirections.actionDetailsToList(
                    it
                )
                findNavController().navigate(action)
            }
            findNavController().navigate(R.id.actionDetailsToList)
        }
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
    }

}