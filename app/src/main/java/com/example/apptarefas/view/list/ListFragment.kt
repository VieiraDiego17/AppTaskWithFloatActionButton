package com.example.apptarefas.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptarefas.R
import com.example.apptarefas.resources.DataSourceList
import com.example.apptarefas.resources.ListTaskAdapter
import com.example.apptarefas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val args: ListFragmentArgs by navArgs()
    private lateinit var listAdapter: ListTaskAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ListViewModel.ListViewModelProvider(
                this
            )
        )[ListViewModel::class.java]

        viewModel.loadUsers(requireActivity())

        // TODO: tentativas de deletar e editar o item da lista
        /*

        viewModel.deleteUser(task = task, context = requireActivity())

        listAdapter = ListTaskAdapter { task ->
            viewModel.deleteUser(task = task, context = requireActivity())
        }

        listAdapter = ListTaskAdapter { task ->
            viewModel.editUser(task = task, context = requireActivity())
        }
        */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicked()
        setRecyclerView()
        //deleteTask()
    }

    fun setRecyclerView() {

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            listAdapter.setDataSet(tasks)
        }

        listAdapter = ListTaskAdapter {
            var action = ListFragmentDirections.actionListToDetails(
                it
            )
            findNavController().navigate(action)
        }

        // TODO: tentativas de deletar e editar o item da lista
        /*
        deleteImageView.setOnClickListener {
            viewModel.deleteUser(task = it, context = requireActivity())
        }

        listAdapter = ListTaskAdapter { task ->
            deleteImageView.setOnClickListener {
                viewModel.deleteUser(task = task, context = requireActivity())
            }
        }
        */

        args.task?.let {
            DataSourceList.castToList(it)
        }?.let {
            listAdapter.setDataSet(it)
        }

        // TODO: Preenchimento da lista após editada
        /*
        args.taskEdit?.let {
            DataSourceList.castToList(it)
        }?.let {
            listAdapter.setDataSet(it)
        }
         */

        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    // TODO: tentativas de deletar
//    fun deleteTask(){
//            listAdapter = ListTaskAdapter { task ->
//                deleteImageView.setOnClickListener {
//                    viewModel.deleteUser(task = task, context = requireActivity())
//                }
//            }
//    }

    fun setClicked() {
        buttonListToRegister.setOnClickListener {
            findNavController().navigate(R.id.actionListToRegister)
        }

        buttonListToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionListToMenu)
        }
    }
}