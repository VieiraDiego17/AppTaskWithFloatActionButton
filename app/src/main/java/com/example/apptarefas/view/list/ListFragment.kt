package com.example.apptarefas.view.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptarefas.R
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Task
import com.example.apptarefas.resources.DataSourceList
import com.example.apptarefas.resources.ListTaskAdapter
import com.example.apptarefas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.container.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val args: ListFragmentArgs by navArgs()
    private lateinit var listAdapter: ListTaskAdapter
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListTaskAdapter

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
