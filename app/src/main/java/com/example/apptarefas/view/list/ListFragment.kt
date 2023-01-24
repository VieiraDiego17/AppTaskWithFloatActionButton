package com.example.apptarefas.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptarefas.R
import com.example.apptarefas.model.Task
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicked()
        setRecyclerView()
    }

    fun setRecyclerView() {

        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            listAdapter.setDataSet(tasks as MutableList<Task>)
        }

        listAdapter = ListTaskAdapter {
            var action = ListFragmentDirections.actionListToDetails(
                it
            )
            findNavController().navigate(action)
        }


        args.task?.let {
            DataSourceList.castToList(it)
        }?.let {
            listAdapter.setDataSet(it as MutableList<Task>)
        }

        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    fun setClicked() {
        buttonListToRegister.setOnClickListener {
            findNavController().navigate(R.id.actionListToRegister)
        }

        buttonListToMenu.setOnClickListener {
            findNavController().navigate(R.id.actionListToMenu)
        }
        }
    }
