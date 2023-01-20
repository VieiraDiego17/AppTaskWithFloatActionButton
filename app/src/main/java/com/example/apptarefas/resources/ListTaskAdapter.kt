package com.example.apptarefas.resources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptarefas.R
import com.example.apptarefas.model.Task
import com.example.apptarefas.viewModel.ListViewModel
import kotlinx.android.synthetic.main.container.view.*
import java.util.*

class ListTaskAdapter(private var onClick: (Task) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var list: MutableList<Task>
    private lateinit var viewModel: ListViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListTaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.container,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ListTaskViewHolder -> {
                holder.bind(list[position],onClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataSet(list: MutableList<Task>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ListTaskViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var titleTask: TextView = itemView.textTypeTask
        var descriptionTask: TextView = itemView.textTypeDate
        //var utensilsTask: TextView = itemView.textTypeUtensils
        // LINHA COMENTADA POIS O ITEM NÃO DEVE APARECER NO CARD,
        // SOMENTE NA TELA DE DETAILS

        fun bind(task: Task,onClick: (Task) -> Unit){
            titleTask.text = task.title
            descriptionTask.text = task.description
            //utensilsTask.text = task.utensils
            // LINHA COMENTADA POIS O ITEM NÃO DEVE APARECER NO CARD,
            // SOMENTE NA TELA DE DETAILS


            //AQUI O CLICK FUNCIONA SOMENTE NA IMAGEM DA FERRAMENTA NO CARD
//            itemView.imageFerramenta.setOnClickListener {
//                if (taskAdd != null) {
//                    onClick(taskAdd)
//                }
//            }

            //AQUI O CLICK FUNCIONA NO CARD INTEIRO
            itemView.setOnClickListener {
                onClick(task)
            }

        }
    }
}