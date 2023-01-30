package com.example.apptarefas.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptarefas.R
import com.example.apptarefas.banco.Banco
import com.example.apptarefas.model.Task
import com.example.apptarefas.resources.DataSourceList
import com.example.apptarefas.resources.ListTaskAdapter
import com.example.apptarefas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.container.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private val args: ListFragmentArgs by navArgs()
    private lateinit var listAdapter: ListTaskAdapter
    private lateinit var viewModel: ListViewModel
    private var floatActionButtonVisible = false

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
        floatActionButton()
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
        addFloatButtonRegister.setOnClickListener {
            findNavController().navigate(R.id.actionListToRegister)
        }

        goToHome.setOnClickListener {
            findNavController().navigate(R.id.actionListToMenu)
        }

    }

    fun floatActionButton() {
        floatActionButtonVisible = false

        addFloat.openFloatButton.setOnClickListener {
            if (!floatActionButtonVisible) {
                addFloatButtonRegister.show()
                goToHome.show()

                addFloatButtonRegister.visibility = View.VISIBLE
                goToHome.visibility = View.VISIBLE

                addFloat.openFloatButton.setImageResource(R.drawable.ic_close)


                floatActionButtonVisible = true
            } else {
                addFloatButtonRegister.hide()
                goToHome.hide()

                addFloatButtonRegister.visibility = View.GONE
                goToHome.visibility = View.GONE

                addFloat.openFloatButton.setImageResource(R.drawable.ic_add)

                floatActionButtonVisible = false
            }
        }
    }
}
